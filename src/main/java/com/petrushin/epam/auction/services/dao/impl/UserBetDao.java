package com.petrushin.epam.auction.services.dao.impl;

import com.petrushin.epam.auction.exceptions.CreatorException;
import com.petrushin.epam.auction.exceptions.EntityDAOException;
import com.petrushin.epam.auction.services.creator.Creator;
import com.petrushin.epam.auction.services.dao.AbstractDao;
import com.petrushin.epam.auction.services.dao.ConnectionPool;
import com.petrushin.epam.auction.services.domain.FlowerLot;
import com.petrushin.epam.auction.services.domain.User;
import com.petrushin.epam.auction.services.domain.UserAddress;
import com.petrushin.epam.auction.services.domain.UserBet;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * Dao class witch extends {@link AbstractDao} and implements methods for
 * {@link UserBet} entity
 *
 * @author Andrei Petrushin
 * @version 1.0.0
 */
public class UserBetDao extends AbstractDao<UserBet> {

    private static final Logger LOGGER = LogManager.getLogger(UserBetDao.class);

    public UserBetDao(Creator<UserBet> creator) {
        super(creator);
    }

    public UserBet findById(Long id) throws EntityDAOException {
        return getByPK(id, UserBet.GET_BY_ID);
    }

    public List<UserBet> getAll() throws EntityDAOException {
        return getAll(UserBet.GET_ALL);
    }


    public boolean save(UserBet userBet) throws EntityDAOException {
        return save(userBet, UserBet.ADD_BET);
    }

    public boolean delete(Long id) throws EntityDAOException {
        return delete(id, UserBet.DELETE_BY_ID);
    }


    @Override
    public boolean update(UserBet userBet) throws EntityDAOException {
        return update(userBet, UserBet.UPDATE_BET);
    }


    public List<UserBet> getByLotId(Long id) throws EntityDAOException {
        Connection connection = ConnectionPool.getInstance().getConnection();
        try (PreparedStatement statement = connection.prepareStatement(UserBet.GET_BY_LOT_ID)) {
            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();
            return creator.createEntityList(resultSet);
        } catch (SQLException | CreatorException e) {
            LOGGER.error("Error with getByLotId operation", e);
            throw new EntityDAOException(e.getMessage(), e);
        } finally {
            ConnectionPool.getInstance().returnConnection(connection);
        }

    }

    @Override
    public void prepareStatementForUpdate(UserBet userBet, PreparedStatement statement)
            throws EntityDAOException {
        try {
            FlowerLot lot = userBet.getLot();
            Long lotId = lot.getId();
            statement.setLong(1, lotId);

            User user = userBet.getUser();
            Long userId = user.getId();
            statement.setLong(2, userId);

            BigDecimal bet = userBet.getBet();
            statement.setBigDecimal(3, bet);

            Long id = userBet.getId();
            statement.setLong(4, id);

        } catch (SQLException e) {
            throw new EntityDAOException(
                    "Error with init bet statement" + e.getMessage(), e);
        }
    }


    @Override
    protected void prepareStatementForInsert(UserBet userBet, PreparedStatement statement)
            throws EntityDAOException {
        prepareStatementForUpdate(userBet, statement);
    }
}

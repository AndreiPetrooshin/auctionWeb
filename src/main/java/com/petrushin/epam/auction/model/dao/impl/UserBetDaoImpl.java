package com.petrushin.epam.auction.model.dao.impl;

import com.petrushin.epam.auction.exceptions.EntityDAOException;
import com.petrushin.epam.auction.model.creator.Creator;
import com.petrushin.epam.auction.model.dao.AbstractDao;
import com.petrushin.epam.auction.model.domain.FlowerLot;
import com.petrushin.epam.auction.model.domain.User;
import com.petrushin.epam.auction.model.domain.UserBet;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

/**
 * Dao class witch extends {@link AbstractDao} and implements methods for
 * {@link UserBet} entity
 *
 * @author Andrei Petrushin
 * @version 1.0.0
 */
public class UserBetDaoImpl extends AbstractDao<UserBet> {

    public UserBetDaoImpl(Creator<UserBet> creator) {
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

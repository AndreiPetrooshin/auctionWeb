package com.petrushin.epam.auction.dao.impl;

import com.petrushin.epam.auction.exceptions.CreatorException;
import com.petrushin.epam.auction.exceptions.EntityDAOException;
import com.petrushin.epam.auction.dao.creator.Creator;
import com.petrushin.epam.auction.dao.AbstractDao;
import com.petrushin.epam.auction.dao.ConnectionPool;
import com.petrushin.epam.auction.domain.FlowerLot;
import com.petrushin.epam.auction.domain.User;
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
 * {@link FlowerLot} entity
 *
 * @author Andrei Petrushin
 * @version 1.0.0
 */
public class FlowerLotDao extends AbstractDao<FlowerLot> {

    private static final Logger LOGGER = LogManager.getLogger(FlowerLotDao.class);

    public FlowerLotDao(Creator<FlowerLot> creator) {
        super(creator);
    }

    public FlowerLot findById(Long id) throws EntityDAOException {
        return getByPK(id, FlowerLot.GET_BY_ID);
    }

    public List<FlowerLot> getAll() throws EntityDAOException {
        return getAll(FlowerLot.GET_ALL);
    }

    public boolean save(FlowerLot lot) throws EntityDAOException {
        return save(lot, FlowerLot.ADD_LOT);
    }

    public boolean delete(Long id) throws EntityDAOException {
        return delete(id, FlowerLot.DELETE_BY_ID);
    }

    public boolean update(FlowerLot lot) throws EntityDAOException {
        return update(lot, FlowerLot.UPDATE_FLOWER_LOT);
    }

    public List<FlowerLot> getByState(String state) throws EntityDAOException {
        Connection connection = ConnectionPool.getInstance().getConnection();
        try (PreparedStatement statement = connection.prepareStatement(FlowerLot.GET_BY_STATE)) {
            statement.setString(1, state);
            ResultSet resultSet = statement.executeQuery();
            ConnectionPool.getInstance().returnConnection(connection);
            return creator.createEntityList(resultSet);
        } catch (SQLException | CreatorException e) {
            LOGGER.error("Error with getByState operation", e);
            throw new EntityDAOException(e.getMessage(), e);
        } finally {
            ConnectionPool.getInstance().returnConnection(connection);
        }
    }

    public List<FlowerLot> getByTypeAndState(String type, String state) throws EntityDAOException {
        Connection connection = ConnectionPool.getInstance().getConnection();
        try (PreparedStatement statement = connection.prepareStatement(FlowerLot.GET_BY_TYPE_AND_STATE)) {
            statement.setString(1, type);
            statement.setString(2, state);
            ResultSet resultSet = statement.executeQuery();
            return creator.createEntityList(resultSet);
        } catch (SQLException | CreatorException e) {
            LOGGER.error("Error with getByTypeAndState operation", e);
            throw new EntityDAOException(e.getMessage(), e);
        } finally {
            ConnectionPool.getInstance().returnConnection(connection);
        }
    }


    public List<FlowerLot> getByUserId(Long id) throws EntityDAOException {
        Connection connection = ConnectionPool.getInstance().getConnection();
        try (PreparedStatement statement = connection.prepareStatement(FlowerLot.GET_BY_USER_ID)) {
            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();
            return creator.createEntityList(resultSet);
        } catch (SQLException | CreatorException e) {
            LOGGER.error("Error with getByUserId operation", e);
            throw new EntityDAOException(e.getMessage(), e);
        } finally {
            ConnectionPool.getInstance().returnConnection(connection);
        }
    }

    public void prepareStatementForUpdate(FlowerLot lot, PreparedStatement statement)
            throws EntityDAOException {
        try {
            User user = lot.getUser();
            Long userId = user.getId();
            statement.setLong(1, userId);

            String type = lot.getType();
            statement.setString(2, type);

            String name = lot.getName();
            statement.setString(3, name);

            String description = lot.getDescription();
            statement.setString(4, description);

            BigDecimal startPrice = lot.getStartPrice();
            statement.setBigDecimal(5, startPrice);

            String state = lot.getState();
            statement.setString(6, state);

            Long lotId = lot.getId();
            statement.setLong(7, lotId);
        } catch (SQLException e) {
            throw new EntityDAOException(
                    "Error with init  FlowerLot statement" + e.getMessage(), e);
        }
    }

    @Override
    protected void prepareStatementForInsert(FlowerLot lot, PreparedStatement statement)
            throws EntityDAOException {
        try {
            User user = lot.getUser();
            Long userId = user.getId();
            statement.setLong(1, userId);

            String type = lot.getType();
            statement.setString(2, type);

            String name = lot.getName();
            statement.setString(3, name);

            String description = lot.getDescription();
            statement.setString(4, description);

            BigDecimal startPrice = lot.getStartPrice();
            statement.setBigDecimal(5, startPrice);

            Long lotId = lot.getId();
            statement.setLong(6, lotId);
        } catch (SQLException e) {
            throw new EntityDAOException(
                    "Error with init  FlowerLot statement" + e.getMessage(), e);
        }
    }
}

package com.petrushin.dao.impl;

import com.petrushin.builder.AbstractBuilder;
import com.petrushin.dao.AbstractDAO;
import com.petrushin.dao.ConnectionPool;
import com.petrushin.dao.exception.AbstractDAOException;
import com.petrushin.dao.exception.ConnectionPoolException;
import com.petrushin.dao.exception.UserBetDAOException;
import com.petrushin.domain.UserBet;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class UserBetDAOImpl extends AbstractDAO<UserBet> {

    public UserBetDAOImpl(AbstractBuilder<UserBet> builder) {
        super(builder);
    }

    public UserBet findById(int id)
            throws UserBetDAOException {
        try {
            return findById(id, UserBet.GET_BY_ID);
        } catch (AbstractDAOException e) {
            throw new UserBetDAOException(e.getMessage());
        }
    }

    public List<UserBet> getAll()
            throws UserBetDAOException {
        try {
            return getAll(UserBet.GET_ALL);
        } catch (AbstractDAOException e) {
            throw new UserBetDAOException(e.getMessage());
        }
    }


    public boolean add(UserBet userBet)
            throws UserBetDAOException {
        try {
            return add(userBet, UserBet.ADD_BET);
        } catch (AbstractDAOException e) {
            throw new UserBetDAOException(e.getMessage());
        }
    }

    public boolean deleteAllByUserId(int id)
            throws UserBetDAOException {
        try {
            return delete(id, UserBet.DELETE_BY_USER_ID);
        } catch (AbstractDAOException e) {
            throw new UserBetDAOException(e.getMessage());
        }
    }

    public boolean deleteAllByLotId(int id)
            throws UserBetDAOException {
        try {
            return delete(id, UserBet.DELETE_BY_LOT_ID);
        } catch (AbstractDAOException e) {
            throw new UserBetDAOException(e.getMessage());
        }
    }

    public boolean deleteByLotAndUserId(int lotId, int userId)
            throws UserBetDAOException {
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = ConnectionPool.getConnection();
            connection.setAutoCommit(false);
            statement = connection.prepareStatement(
                    UserBet.DELETE_BY_USER_AND_LOT_ID);
            statement.setInt(1, userId);
            statement.setInt(2, lotId);
            int rowCountChanged = statement.executeUpdate();
            connection.commit();
            connection.setAutoCommit(true);
            return rowCountChanged==1;
        } catch (SQLException | ConnectionPoolException e) {
            throw new UserBetDAOException(
                    "Error with delete by lot and user ids method "
                            + e.getMessage());
        } finally {
            try {
                closeAll(connection, statement);
            } catch (AbstractDAOException e) {
                throw new UserBetDAOException(
                        "Error with delete by lot and user ids method "
                                + e.getMessage());
            }
        }
    }


}

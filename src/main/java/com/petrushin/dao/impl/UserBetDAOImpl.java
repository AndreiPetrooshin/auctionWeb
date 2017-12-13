package com.petrushin.dao.impl;

import com.petrushin.dao.ConnectionPool;
import com.petrushin.dao.EntityDAO;
import com.petrushin.dao.exception.UserBetDAOException;
import com.petrushin.domain.UserBet;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserBetDAOImpl implements EntityDAO<UserBet> {

    @Override
    public UserBet findById(int id) throws UserBetDAOException {
        ResultSet resultSet = null;
        Connection connection = null;
        PreparedStatement statement = null;
        UserBet userBet = null;
        ConnectionPool connectionPool = new ConnectionPool();
        try {
            DataSource dataSource = connectionPool.setUpPool();
            connection = dataSource.getConnection();
            statement = connection.prepareStatement(UserBet.GET_BY_ID);
            statement.setInt(1, id);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                userBet = createUserBet(resultSet);
            }

        } catch (ClassNotFoundException | SQLException e) {
            throw new UserBetDAOException(e.getMessage());
        } finally {
            closeAll(resultSet, connection, statement);
        }
        return userBet;
    }

    @Override
    public List<UserBet> getAll() throws UserBetDAOException {
        ResultSet resultSet = null;
        Connection connection = null;
        PreparedStatement statement = null;
        List<UserBet> listUserBets = new ArrayList<>();
        ConnectionPool connectionPool = new ConnectionPool();
        try {
            DataSource dataSource = connectionPool.setUpPool();
            connection = dataSource.getConnection();
            statement = connection.prepareStatement(UserBet.GET_ALL);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                UserBet userBet = createUserBet(resultSet);
                listUserBets.add(userBet);
            }

        } catch (ClassNotFoundException | SQLException e) {
            throw new UserBetDAOException(e.getMessage());
        } finally {
            closeAll(resultSet, connection, statement);
        }
        return listUserBets;
    }

    @Override
    public void add(UserBet userBet) throws UserBetDAOException {
        Connection connection = null;
        PreparedStatement statement = null;
        ConnectionPool connectionPool = new ConnectionPool();
        try {
            DataSource dataSource = connectionPool.setUpPool();
            connection = dataSource.getConnection();
            statement = connection.prepareStatement(UserBet.ADD_BET);

            initStatement(userBet, statement);

            statement.executeUpdate();
        } catch (ClassNotFoundException | SQLException e) {
            throw new UserBetDAOException(e.getMessage());
        } finally {
            closeAll(null, connection, statement);
        }
    }

    private UserBet createUserBet(ResultSet resultSet) throws SQLException {
        int lotId = resultSet.getInt("fl_id");
        int userId = resultSet.getInt("user_id");
        double bet = resultSet.getDouble("user_bet");

        return new UserBet(lotId, userId, bet);
    }

    private void initStatement(UserBet userBet, PreparedStatement statement) throws SQLException {
        int lotId = userBet.getLotId();
        int userId = userBet.getUserId();
        double bet = userBet.getBet();

        statement.setInt(1,lotId);
        statement.setInt(2,userId);
        statement.setDouble(3,bet);
    }

    private void closeAll(ResultSet resultSet,
                          Connection connection, PreparedStatement statement)
            throws UserBetDAOException {
        if (resultSet != null) {
            try {
                resultSet.close();
            } catch (SQLException e) {
                throw new UserBetDAOException("Error with resultSetClose", e);
            }
        }
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                throw new UserBetDAOException("Error with Connection close", e);
            }
        }
        if (statement != null) {
            try {
                statement.close();
            } catch (SQLException e) {
                throw new UserBetDAOException("Error with statement close", e);
            }
        }
    }

}

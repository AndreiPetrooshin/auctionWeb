package com.petrushin.dao.impl;

import com.petrushin.dao.ConnectionPool;
import com.petrushin.dao.EntityDAO;
import com.petrushin.dao.exception.UserFlowerLotDAOException;
import com.petrushin.domain.UserFlowerLot;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserFlowerLotDAOImpl implements EntityDAO<UserFlowerLot> {


    private static final String FL_ID = "fl_id";
    private static final String USER_ID = "user_id";
    private static final String FL_TYPE = "fl_type";
    private static final String FL_NAME = "fl_name";
    private static final String FL_DESCRIPTION = "fl_description";
    private static final String FL_START_PRICE = "fl_start_price";
    private static final String FL_STATE = "fl_state";

    @Override
    public UserFlowerLot findById(int id) throws UserFlowerLotDAOException {
        ResultSet resultSet = null;
        Connection connection = null;
        PreparedStatement statement = null;
        UserFlowerLot flowerLot = null;
        ConnectionPool connectionPool = new ConnectionPool();
        try {
            DataSource dataSource = connectionPool.setUpPool();
            connection = dataSource.getConnection();
            statement = connection.prepareStatement(UserFlowerLot.GET_BY_ID);
            statement.setInt(1, id);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                flowerLot = createUserFlowerLot(resultSet);
            }

        } catch (ClassNotFoundException | SQLException e) {
            throw new UserFlowerLotDAOException(e.getMessage());
        } finally {
            closeAll(resultSet, connection, statement);
        }
        return flowerLot;
    }

    @Override
    public List<UserFlowerLot> getAll() throws UserFlowerLotDAOException {
        ResultSet resultSet = null;
        Connection connection = null;
        PreparedStatement statement = null;
        List<UserFlowerLot> listUserRoles = new ArrayList<>();
        ConnectionPool connectionPool = new ConnectionPool();
        try {
            DataSource dataSource = connectionPool.setUpPool();
            connection = dataSource.getConnection();
            statement = connection.prepareStatement(UserFlowerLot.GET_ALL);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                UserFlowerLot flowerLot= createUserFlowerLot(resultSet);
                listUserRoles.add(flowerLot);
            }

        } catch (ClassNotFoundException | SQLException e) {
            throw new UserFlowerLotDAOException(e.getMessage());
        } finally {
            closeAll(resultSet, connection, statement);
        }
        return listUserRoles;
    }

    @Override
    public void add(UserFlowerLot lot) throws UserFlowerLotDAOException {
        Connection connection = null;
        PreparedStatement statement = null;
        ConnectionPool connectionPool = new ConnectionPool();
        try {
            DataSource dataSource = connectionPool.setUpPool();
            connection = dataSource.getConnection();
            statement = connection.prepareStatement(UserFlowerLot.ADD_LOT);

            initStatement(lot, statement);

            statement.executeUpdate();
        } catch (ClassNotFoundException | SQLException e) {
            throw new UserFlowerLotDAOException(e.getMessage());
        } finally {
            closeAll(null, connection, statement);
        }

    }

    private UserFlowerLot createUserFlowerLot(ResultSet resultSet) throws SQLException {
        int lotId = resultSet.getInt(FL_ID);
        int userId = resultSet.getInt(USER_ID);
        String type = resultSet.getString(FL_TYPE);
        String name = resultSet.getString(FL_NAME);
        String description = resultSet.getString(FL_DESCRIPTION);
        double startPrice = resultSet.getDouble(FL_START_PRICE);
        String state = resultSet.getString(FL_STATE);

        return new UserFlowerLot(lotId, userId, type, name,
                description, startPrice, state);

    }

    private void initStatement(UserFlowerLot lot, PreparedStatement statement) throws SQLException {
        int userId = lot.getUserId();
        String type = lot.getType();
        String name = lot.getName();
        String description = lot.getDescription();
        double startPrice = lot.getStartPrice();


        statement.setInt(1,userId);
        statement.setString(2,type);
        statement.setString(3,name);
        statement.setString(4,description);
        statement.setDouble(5,startPrice);
    }

    private void closeAll(ResultSet resultSet,
                          Connection connection, PreparedStatement statement)
            throws UserFlowerLotDAOException {

        if (resultSet != null) {
            try {
                resultSet.close();
            } catch (SQLException e) {
                throw new UserFlowerLotDAOException("Error with resultSetClose", e);
            }
        }
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                throw new UserFlowerLotDAOException("Error with Connection close", e);
            }
        }
        if (statement != null) {
            try {
                statement.close();
            } catch (SQLException e) {
                throw new UserFlowerLotDAOException("Error with statement close", e);
            }
        }
    }

}

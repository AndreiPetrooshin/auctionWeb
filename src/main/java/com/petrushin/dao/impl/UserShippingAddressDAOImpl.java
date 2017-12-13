package com.petrushin.dao.impl;

import com.petrushin.dao.ConnectionPool;
import com.petrushin.dao.EntityDAO;
import com.petrushin.dao.exception.UserShippingAddressDAOException;
import com.petrushin.domain.UserShippingAddress;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserShippingAddressDAOImpl implements EntityDAO<UserShippingAddress> {


    private static final String SHIP_ADDR_ID = "ship_addr_id";
    private static final String USER_ID = "user_id";
    private static final String SA_FIRST_NAME = "sa_first_name";
    private static final String SA_SECOND_NAME = "sa_second_name";
    private static final String SA_LAST_NAME = "sa_last_name";
    private static final String SA_COUNTRY = "sa_country";
    private static final String SA_CITY = "sa_city";
    private static final String SA_STREET = "sa_street";
    private static final String SA_PHONE = "sa_phone";
    private static final String SA_POSTAL_CODE = "sa_postal_code";
    private static final String SA_IS_ACTIVE = "sa_is_active";

    @Override
    public UserShippingAddress findById(int id) throws UserShippingAddressDAOException {
        ResultSet resultSet = null;
        Connection connection = null;
        PreparedStatement statement = null;
        UserShippingAddress address = null;
        ConnectionPool connectionPool = new ConnectionPool();
        try {
            DataSource dataSource = connectionPool.setUpPool();
            connection = dataSource.getConnection();
            statement = connection.prepareStatement(UserShippingAddress.GET_BY_ID);
            statement.setInt(1, id);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                address = createUserShippingAddress(resultSet);
            }

        } catch (ClassNotFoundException | SQLException e) {
            throw new UserShippingAddressDAOException(e.getMessage());
        } finally {
            closeAll(resultSet, connection, statement);
        }
        return address;
    }

    @Override
    public List<UserShippingAddress> getAll() throws UserShippingAddressDAOException {
        ResultSet resultSet = null;
        Connection connection = null;
        PreparedStatement statement = null;
        List<UserShippingAddress> addresses = new ArrayList<>();
        ConnectionPool connectionPool = new ConnectionPool();
        try {
            DataSource dataSource = connectionPool.setUpPool();
            connection = dataSource.getConnection();
            statement = connection.prepareStatement(UserShippingAddress.GET_ALL);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                UserShippingAddress address = createUserShippingAddress(resultSet);
                addresses.add(address);
            }

        } catch (ClassNotFoundException | SQLException e) {
            throw new UserShippingAddressDAOException(e.getMessage());
        } finally {
            closeAll(resultSet, connection, statement);
        }
        return addresses;
    }

    @Override
    public void add(UserShippingAddress address) throws UserShippingAddressDAOException {
        Connection connection = null;
        PreparedStatement statement = null;
        ConnectionPool connectionPool = new ConnectionPool();
        try {
            DataSource dataSource = connectionPool.setUpPool();
            connection = dataSource.getConnection();
            statement = connection.prepareStatement(UserShippingAddress.ADD_ADDRESS);
            initStatement(address, statement);
            statement.executeUpdate();
        } catch (ClassNotFoundException | SQLException e) {
            throw new UserShippingAddressDAOException(e.getMessage());
        } finally {
            closeAll(null, connection, statement);
        }
    }

    private void initStatement(UserShippingAddress address, PreparedStatement statement) throws SQLException {
        int userId = address.getUserId();
        String fName = address.getFirstName();
        String sName = address.getSecondName();
        String lName = address.getLastName();
        String country = address.getCountry();
        String city = address.getCity();
        String street = address.getStreet();
        String phone = address.getPhone();
        String postalCode = address.getPostalCode();
        boolean isActive = address.isActive();

        statement.setInt(1,userId);
        statement.setString(2,fName);
        statement.setString(3,sName);
        statement.setString(4,lName);
        statement.setString(5,country);
        statement.setString(6,city);
        statement.setString(7,street);
        statement.setString(8,phone);
        statement.setString(9,postalCode);
        statement.setBoolean(10,isActive);
    }

    private UserShippingAddress createUserShippingAddress(ResultSet resultSet) throws SQLException {
        int shipAddrId = resultSet.getInt(SHIP_ADDR_ID);
        int userId = resultSet.getInt(USER_ID);
        String fName = resultSet.getString(SA_FIRST_NAME);
        String sName = resultSet.getString(SA_SECOND_NAME);
        String lName = resultSet.getString(SA_LAST_NAME);
        String country = resultSet.getString(SA_COUNTRY);
        String city = resultSet.getString(SA_CITY);
        String street = resultSet.getString(SA_STREET);
        String phone = resultSet.getString(SA_PHONE);
        String postalCode = resultSet.getString(SA_POSTAL_CODE);
        boolean isActive = resultSet.getBoolean(SA_IS_ACTIVE);

        return new UserShippingAddress(
                shipAddrId, userId, fName, sName, lName, country,
                city, street, phone, postalCode, isActive);
    }


    private void closeAll(ResultSet resultSet, Connection connection, PreparedStatement statement) throws UserShippingAddressDAOException {
        if (resultSet != null) {
            try {
                resultSet.close();
            } catch (SQLException e) {
                throw new UserShippingAddressDAOException("Error with resultSetClose", e);
            }
        }
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                throw new UserShippingAddressDAOException("Error with Connection close", e);
            }
        }
        if (statement != null) {
            try {
                statement.close();
            } catch (SQLException e) {
                throw new UserShippingAddressDAOException("Error with statement close", e);
            }
        }
    }
}

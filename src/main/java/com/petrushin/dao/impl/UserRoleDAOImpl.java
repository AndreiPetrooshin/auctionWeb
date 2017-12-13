package com.petrushin.dao.impl;

import com.petrushin.dao.ConnectionPool;
import com.petrushin.dao.EntityDAO;
import com.petrushin.dao.exception.UserRoleDAOException;
import com.petrushin.domain.UserRole;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserRoleDAOImpl implements EntityDAO<UserRole> {


    private static final String ROLE_ID = "role_id";
    private static final String USER_ROLE = "user_role";

    @Override
    public UserRole findById(int id) throws UserRoleDAOException {
        ResultSet resultSet = null;
        Connection connection = null;
        PreparedStatement statement = null;
        UserRole userRole = null;
        ConnectionPool connectionPool = new ConnectionPool();
        try {
            DataSource dataSource = connectionPool.setUpPool();
            connection = dataSource.getConnection();
            statement = connection.prepareStatement(UserRole.GET_BY_ID);
            statement.setInt(1, id);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                userRole = createUserRole(resultSet);
            }

        } catch (ClassNotFoundException | SQLException e) {
            throw new UserRoleDAOException(e.getMessage());
        } finally {
            closeAll(resultSet, connection, statement);
        }
        return userRole;
    }

    @Override
    public List<UserRole> getAll() throws UserRoleDAOException {
        ResultSet resultSet = null;
        Connection connection = null;
        PreparedStatement statement = null;
        List<UserRole> listUserRoles = new ArrayList<>();
        ConnectionPool connectionPool = new ConnectionPool();
        try {
            DataSource dataSource = connectionPool.setUpPool();
            connection = dataSource.getConnection();
            statement = connection.prepareStatement(UserRole.GET_ALL);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                UserRole userRole = createUserRole(resultSet);
                listUserRoles.add(userRole);
            }

        } catch (ClassNotFoundException | SQLException e) {
            throw new UserRoleDAOException(e.getMessage());
        } finally {
            closeAll(resultSet, connection, statement);
        }
        return listUserRoles;
    }

    @Override
    public void add(UserRole userRole) throws UserRoleDAOException {
        Connection connection = null;
        PreparedStatement statement = null;
        ConnectionPool connectionPool = new ConnectionPool();
        try {
            DataSource dataSource = connectionPool.setUpPool();
            connection = dataSource.getConnection();
            statement = connection.prepareStatement(UserRole.ADD_ROLE);

            String role = userRole.getRole();
            statement.setString(1,role);
            statement.executeUpdate();
        } catch (ClassNotFoundException | SQLException e) {
            throw new UserRoleDAOException(e.getMessage());
        } finally {
            closeAll(null, connection, statement);
        }
    }

    private UserRole createUserRole(ResultSet resultSet) throws SQLException {
        UserRole userRole;
        int roleId = resultSet.getInt(ROLE_ID);
        String role = resultSet.getString(USER_ROLE);
        userRole = new UserRole(roleId,role);
        return userRole;
    }

    private void closeAll(ResultSet resultSet,
                          Connection connection, PreparedStatement statement)
            throws UserRoleDAOException {

        if (resultSet != null) {
            try {
                resultSet.close();
            } catch (SQLException e) {
                throw new UserRoleDAOException("Error with resultSetClose", e);
            }
        }
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                throw new UserRoleDAOException("Error with Connection close", e);
            }
        }
        if (statement != null) {
            try {
                statement.close();
            } catch (SQLException e) {
                throw new UserRoleDAOException("Error with statement close", e);
            }
        }
    }
}

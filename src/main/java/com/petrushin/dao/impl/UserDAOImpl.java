package com.petrushin.dao.impl;

import com.petrushin.dao.ConnectionPool;
import com.petrushin.dao.EntityDAO;
import com.petrushin.dao.exception.EntityDAOException;
import com.petrushin.dao.exception.UserDAOException;
import com.petrushin.domain.User;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDAOImpl implements EntityDAO<User> {

    private static final String USER_ID = "user_id";
    private static final String ROLE_ID = "role_id";
    private static final String U_LOGIN = "u_login";
    private static final String U_PASSWORD = "u_password";
    private static final String U_EMAIL = "u_email";

    @Override
    public User findById(int id) throws UserDAOException {
        ResultSet resultSet = null;
        Connection connection = null;
        PreparedStatement statement = null;
        User user = null;
        ConnectionPool connectionPool = new ConnectionPool();
        try {
            DataSource dataSource = connectionPool.setUpPool();
            connection = dataSource.getConnection();
            statement = connection.prepareStatement(User.GET_BY_ID);
            statement.setInt(1, id);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                user = createUser(resultSet);
            }

        } catch (ClassNotFoundException | SQLException | EntityDAOException e) {
            throw new UserDAOException(e.getMessage());
        } finally {
            closeAll(resultSet, connection, statement);
        }
        return user;
    }

    public User getByLogin(String login) throws UserDAOException {
        ResultSet resultSet = null;
        Connection connection = null;
        PreparedStatement statement = null;
        User user = null;
        ConnectionPool connectionPool = new ConnectionPool();
        try {
            DataSource dataSource = connectionPool.setUpPool();
            connection = dataSource.getConnection();
            statement = connection.prepareStatement(User.GET_BY_LOGIN);
            statement.setString(1, login);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                user = createUser(resultSet);
            }

        } catch (ClassNotFoundException | SQLException | EntityDAOException e) {
            throw new UserDAOException(e.getMessage());
        } finally {
            closeAll(resultSet, connection, statement);
        }
        return user;
    }

    public boolean ifLoginExist(String login) throws UserDAOException {
        ResultSet resultSet = null;
        Connection connection = null;
        PreparedStatement statement = null;
        ConnectionPool connectionPool = new ConnectionPool();
        try {
            DataSource dataSource = connectionPool.setUpPool();
            connection = dataSource.getConnection();
            statement = connection.prepareStatement(User.GET_LOGIN);
            statement.setString(1, login);
            resultSet = statement.executeQuery();
            String userLogin = null;
            while (resultSet.next()) {
                userLogin = resultSet.getString(U_LOGIN);
            }
            return userLogin != null;

        } catch (ClassNotFoundException | SQLException e) {
            throw new UserDAOException(e.getMessage());
        } finally {
            closeAll(resultSet, connection, statement);
        }
    }

    public boolean ifEmailExist(String email) throws UserDAOException {
        ResultSet resultSet = null;
        Connection connection = null;
        PreparedStatement statement = null;
        ConnectionPool connectionPool = new ConnectionPool();
        try {
            DataSource dataSource = connectionPool.setUpPool();
            connection = dataSource.getConnection();
            statement = connection.prepareStatement(User.GET_EMAIL);
            statement.setString(1, email);
            resultSet = statement.executeQuery();
            String userEmail = null;
            while (resultSet.next()) {
                userEmail = resultSet.getString(U_EMAIL);
            }
            return userEmail != null;

        } catch (ClassNotFoundException | SQLException e) {
            throw new UserDAOException(e.getMessage());
        } finally {
            closeAll(resultSet, connection, statement);
        }
    }

    @Override
    public List<User> getAll() throws UserDAOException {
        ResultSet resultSet = null;
        Connection connection = null;
        PreparedStatement statement = null;
        List<User> listUsers = new ArrayList<>();
        ConnectionPool connectionPool = new ConnectionPool();
        try {
            DataSource dataSource = connectionPool.setUpPool();
            connection = dataSource.getConnection();
            statement = connection.prepareStatement(User.GET_ALL);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                User user = createUser(resultSet);
                listUsers.add(user);
            }

        } catch (ClassNotFoundException | SQLException | EntityDAOException e) {
            throw new UserDAOException(e.getMessage());
        } finally {
            closeAll(resultSet, connection, statement);
        }
        return listUsers;
    }

    @Override
    public void add(User user) throws UserDAOException {
        Connection connection = null;
        PreparedStatement statement = null;
        ConnectionPool connectionPool = new ConnectionPool();
        try {
            DataSource dataSource = connectionPool.setUpPool();
            connection = dataSource.getConnection();
            statement = connection.prepareStatement(User.ADD_USER);

            initStatement(user, statement);

            statement.executeUpdate();
        } catch (ClassNotFoundException | SQLException e) {
            throw new UserDAOException(e.getMessage());
        } finally {
            closeAll(null, connection, statement);
        }
    }

    private void initStatement(User user, PreparedStatement statement) throws SQLException {
        int roleId = user.getRoleId();
        String login = user.getLogin();
        String password = user.getPassword();
        String email = user.getEmail();

        statement.setInt(1, roleId);
        statement.setString(2, login);
        statement.setString(3, password);
        statement.setString(4, email);
    }

    private User createUser(ResultSet resultSet) throws SQLException, EntityDAOException {
        int id = resultSet.getInt(USER_ID);
        int roleId = resultSet.getInt(ROLE_ID);
        String login = resultSet.getString(U_LOGIN);
        String password = resultSet.getString(U_PASSWORD);
        String email = resultSet.getString(U_EMAIL);

        return new User(id, roleId, login, password, email);
    }

    private void closeAll(ResultSet resultSet,
                          Connection connection, PreparedStatement statement)
            throws UserDAOException {

        if (resultSet != null) {
            try {
                resultSet.close();
            } catch (SQLException e) {
                throw new UserDAOException("Error with resultSetClose", e);
            }
        }
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                throw new UserDAOException("Error with Connection close", e);
            }
        }
        if (statement != null) {
            try {
                statement.close();
            } catch (SQLException e) {
                throw new UserDAOException("Error with statement close", e);
            }
        }
    }

}

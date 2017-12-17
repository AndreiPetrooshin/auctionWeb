package com.petrushin.dao.impl;

import com.petrushin.builder.Builder;
import com.petrushin.builder.exceptions.AbstractBuilderException;
import com.petrushin.dao.AbstractDAO;
import com.petrushin.dao.ConnectionPool;
import com.petrushin.dao.exception.AbstractDAOException;
import com.petrushin.dao.exception.ConnectionPoolException;
import com.petrushin.dao.exception.UserDAOException;
import com.petrushin.domain.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class UserDAOImpl extends AbstractDAO<User> {


    private static final String U_LOGIN = "u_login";
    private static final String U_EMAIL = "u_email";

    public UserDAOImpl(Builder<User> builder) {
        super(builder);
    }


    public User findById(Long id)
            throws UserDAOException {
        try {
            return findById(id, User.GET_BY_ID);
        } catch (AbstractDAOException e) {
            throw new UserDAOException(e.getMessage());
        }
    }

    public boolean delete(Long id)
            throws UserDAOException {
        try {
            return delete(id, User.DELETE_BY_ID);
        } catch (AbstractDAOException e) {
            throw new UserDAOException(e.getMessage());
        }
    }

    public boolean update(User user)
            throws UserDAOException {
        try {
            return update(user, User.UPDATE_USER);
        } catch (AbstractDAOException e) {
            throw new UserDAOException(e.getMessage());
        }
    }

    public List<User> getAll()
            throws UserDAOException {
        try {
            return getAll(User.GET_ALL);
        } catch (AbstractDAOException e) {
            throw new UserDAOException(e.getMessage());
        }
    }

    public boolean add(User user)
            throws UserDAOException {
        try {
            return add(user, User.ADD_USER);
        } catch (AbstractDAOException e) {
            throw new UserDAOException(e.getMessage());
        }
    }


    public User getByLogin(String login)
            throws UserDAOException {
        PreparedStatement statement = null;
        Connection connection = null;
        try {
            connection = ConnectionPool.getConnection();
            statement = connection.prepareStatement(User.GET_BY_LOGIN);
            statement.setString(1, login);
            ResultSet resultSet = statement.executeQuery();
            User user = null;
            if(resultSet.next()){
                user = builder.createEntity(resultSet);
            }
            return user;
        } catch (SQLException | AbstractBuilderException
                | ConnectionPoolException e) {
            throw new UserDAOException(e.getMessage());
        } finally {
            try {
                closeAll(connection, statement);
            } catch (AbstractDAOException e) {
                throw new UserDAOException(e.getMessage());
            }
        }
    }


    public boolean ifLoginExist(String login)
            throws UserDAOException {
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = ConnectionPool.getConnection();
            statement = connection.prepareStatement(User.GET_LOGIN);
            statement.setString(1, login);
            ResultSet resultSet = statement.executeQuery();
            String userLogin = null;
            if (resultSet.next()) {
                userLogin = resultSet.getString(U_LOGIN);
            }
            return userLogin != null;
        } catch (SQLException | ConnectionPoolException e) {
            throw new UserDAOException(e.getMessage());
        } finally {
            try {
                closeAll(connection, statement);
            } catch (AbstractDAOException e) {
                throw new UserDAOException(e.getMessage());
            }
        }
    }

    public boolean ifEmailExist(String email)
            throws UserDAOException {
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = ConnectionPool.getConnection();
            statement = connection.prepareStatement(User.GET_EMAIL);
            statement.setString(1, email);
            ResultSet resultSet = statement.executeQuery();
            String userEmail = null;
            if (resultSet.next()) {
                userEmail = resultSet.getString(U_EMAIL);
            }
            return userEmail != null;
        } catch (SQLException | ConnectionPoolException e) {
            throw new UserDAOException(e.getMessage());
        } finally {
            try {
                closeAll(connection, statement);
            } catch (AbstractDAOException e) {
                throw new UserDAOException(e.getMessage());
            }
        }
    }


}

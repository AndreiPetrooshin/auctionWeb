package com.petrushin.dao.impl;

import com.petrushin.builder.Creator;
import com.petrushin.dao.AbstractDAO;
import com.petrushin.dao.ConnectionPool;
import com.petrushin.domain.User;
import com.petrushin.exceptions.ConnectionPoolException;
import com.petrushin.exceptions.CreatorException;
import com.petrushin.exceptions.EntityDAOException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class UserDAOImpl extends AbstractDAO<User> {


    private static final String U_LOGIN = "u_login";
    private static final String U_EMAIL = "u_email";

    public UserDAOImpl(Creator<User> creator) {
        super(creator);
    }


    public User findById(Long id)
            throws EntityDAOException {
        try {
            return findById(id, User.GET_BY_ID);
        } catch (EntityDAOException e) {
            throw new EntityDAOException(
                    "FindByID User Error. " + e.getMessage(), e);
        }
    }

    public boolean delete(Long id)
            throws EntityDAOException {
        try {
            return delete(id, User.DELETE_BY_ID);
        } catch (EntityDAOException e) {
            throw new EntityDAOException(
                    "Delete User Error. " + e.getMessage(), e);
        }
    }

    public boolean update(User user)
            throws EntityDAOException {
        try {
            return update(user, User.UPDATE_USER);
        } catch (EntityDAOException e) {
            throw new EntityDAOException(
                    "Update User Error. " + e.getMessage(), e);
        }
    }

    public List<User> getAll()
            throws EntityDAOException {
        try {
            return getAll(User.GET_ALL);
        } catch (EntityDAOException e) {
            throw new EntityDAOException(
                    "GetAll Users Error. " + e.getMessage(), e);
        }
    }

    public boolean save(User user)
            throws EntityDAOException {
        try {
            return save(user, User.ADD_USER);
        } catch (EntityDAOException e) {
            throw new EntityDAOException(
                    "Save User Error. " + e.getMessage(), e);
        }
    }


    public User getByLogin(String login)
            throws EntityDAOException {
        try (Connection connection = ConnectionPool.getConnection();
             PreparedStatement statement =
                     connection.prepareStatement(User.GET_BY_LOGIN)) {

            statement.setString(1, login);
            ResultSet resultSet = statement.executeQuery();
            User user = null;
            if (resultSet.next()) {
                user = creator.createEntity(resultSet);
            }
            return user;
        } catch (SQLException | CreatorException
                | ConnectionPoolException e) {
            throw new EntityDAOException(
                    "GetByLogin User Error. " + e.getMessage(), e);
        }
    }


    public boolean ifLoginExist(String login)
            throws EntityDAOException {
        try (Connection connection = ConnectionPool.getConnection();
             PreparedStatement statement =
                     connection.prepareStatement(User.IS_LOGIN_EXIST)) {
            statement.setString(1, login);
            ResultSet resultSet = statement.executeQuery();
            String userLogin = null;
            if (resultSet.next()) {
                userLogin = resultSet.getString(U_LOGIN);
            }
            return userLogin != null;
        } catch (SQLException | ConnectionPoolException e) {
            throw new EntityDAOException(e.getMessage(), e);
        }
    }

    public boolean ifEmailExist(String email)
            throws EntityDAOException {

        try (Connection connection = ConnectionPool.getConnection();
             PreparedStatement statement =
                     connection.prepareStatement(User.IS_EMAIL_EXIST)) {
            statement.setString(1, email);
            ResultSet resultSet = statement.executeQuery();
            String userEmail = null;
            if (resultSet.next()) {
                userEmail = resultSet.getString(U_EMAIL);
            }
            return userEmail != null;
        } catch (SQLException | ConnectionPoolException e) {
            throw new EntityDAOException(e.getMessage(), e);
        }
    }


}

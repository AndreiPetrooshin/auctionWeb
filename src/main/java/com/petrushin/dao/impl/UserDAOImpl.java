package com.petrushin.dao.impl;

import com.petrushin.creator.Creator;
import com.petrushin.dao.AbstractDAO;
import com.petrushin.dao.ConnectionPool;
import com.petrushin.domain.User;
import com.petrushin.domain.UserRole;
import com.petrushin.exceptions.ConnectionPoolException;
import com.petrushin.exceptions.CreatorException;
import com.petrushin.exceptions.EntityDAOException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class UserDAOImpl extends AbstractDAO<User> {


    private static final String PARAM_U_LOGIN = "u_login";
    private static final String PARAM_U_EMAIL = "u_email";

    public UserDAOImpl(Creator<User> creator) {
        super(creator);
    }

    public User findById(Long id) throws EntityDAOException {
            return getByPK(id, User.GET_BY_ID);
    }

    public boolean delete(Long id) throws EntityDAOException {
        return delete(id, User.DELETE_BY_ID);
    }

    public boolean update(User user) throws EntityDAOException {
        return update(user, User.UPDATE_USER);
    }

    public List<User> getAll() throws EntityDAOException {
        return getAll(User.GET_ALL);
    }

    public boolean save(User user) throws EntityDAOException {
        return save(user, User.ADD_USER);
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


    public boolean ifLoginExist(String login) throws EntityDAOException {
        try (Connection connection = ConnectionPool.getConnection();
             PreparedStatement statement = connection.prepareStatement(User.IS_LOGIN_EXIST)) {
            return ifUniqueExist(login, statement, PARAM_U_LOGIN);
        } catch (SQLException | ConnectionPoolException e) {
            throw new EntityDAOException(e.getMessage(), e);
        }
    }

    private boolean ifUniqueExist(String uniqueKey, PreparedStatement statement, String attr)
            throws SQLException {
        statement.setString(1, uniqueKey);
        ResultSet resultSet = statement.executeQuery();
        String userLogin = null;
        if (resultSet.next()) {
            userLogin = resultSet.getString(attr);
        }
        return userLogin != null;
    }

    public boolean ifEmailExist(String email)
            throws EntityDAOException {

        try (Connection connection = ConnectionPool.getConnection();
             PreparedStatement statement = connection.prepareStatement(User.IS_EMAIL_EXIST)) {
            return ifUniqueExist(email, statement, PARAM_U_EMAIL);
        } catch (SQLException | ConnectionPoolException e) {
            throw new EntityDAOException(e.getMessage(), e);
        }
    }

    public void prepareStatement(User user, PreparedStatement statement)
            throws CreatorException {
        try {
            UserRole role = user.getRole();
            Long roleId = role.getId();
            statement.setLong(1, roleId);

            String login = user.getLogin();
            statement.setString(2, login);

            String password = user.getPassword();
            statement.setString(3, password);

            String email = user.getEmail();
            statement.setString(4, email);

            Long id = user.getId();
            statement.setLong(5, id);
        } catch (SQLException e) {
            throw new CreatorException(
                    "Error with init user statement " + e.getMessage(), e);
        }
    }


}

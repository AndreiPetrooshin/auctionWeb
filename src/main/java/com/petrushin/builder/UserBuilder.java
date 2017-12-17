package com.petrushin.builder;

import com.petrushin.builder.exceptions.UserBuilderException;
import com.petrushin.dao.GenericDAO;
import com.petrushin.dao.exception.AbstractDAOException;
import com.petrushin.dao.exception.UserRoleDAOException;
import com.petrushin.dao.impl.UserRoleDAOImpl;
import com.petrushin.domain.User;
import com.petrushin.domain.UserRole;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserBuilder extends AbstractBuilder<User> {

    private static final String USER_ID = "user_id";
    private static final String ROLE_ID = "role_id";
    private static final String U_LOGIN = "u_login";
    private static final String U_PASSWORD = "u_password";
    private static final String U_EMAIL = "u_email";

    private static Builder<UserRole> builder;
    private static GenericDAO<UserRole> userRoleDAO;

    public UserBuilder() {
        builder = new UserRoleBuilder();
        userRoleDAO = new UserRoleDAOImpl(builder);
    }

    public void initStatement(User user, PreparedStatement statement)
            throws UserBuilderException {
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
            throw new UserBuilderException(
                    "Error with init user statement " + e.getMessage(),e);
        }
    }

    public User createEntity(ResultSet resultSet)
            throws UserBuilderException {
        try {
            Long id = resultSet.getLong(USER_ID);
            Long roleId = resultSet.getLong(ROLE_ID);
            UserRole role = userRoleDAO.findById(roleId);
            String login = resultSet.getString(U_LOGIN);
            String password = resultSet.getString(U_PASSWORD);
            String email = resultSet.getString(U_EMAIL);
            return new User(id, role, login, password, email);
        } catch (SQLException | AbstractDAOException e) {
            throw new UserBuilderException(
                    "Error with create user entity " + e.getMessage(), e);
        }

    }

}

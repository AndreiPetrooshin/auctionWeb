package com.petrushin.builder.impl;

import com.petrushin.builder.AbstractCreator;
import com.petrushin.domain.User;
import com.petrushin.domain.UserRole;
import com.petrushin.exceptions.CreatorException;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserCreator extends AbstractCreator<User> {

    private static final String USER_ID = "user_id";
    private static final String ROLE_ID = "role_id";
    private static final String U_LOGIN = "u_login";
    private static final String U_PASSWORD = "u_password";
    private static final String U_EMAIL = "u_email";
    private static final String USER_ROLE = "user_role";

    public void initStatement(User user, PreparedStatement statement)
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

    public User createEntity(ResultSet resultSet)
            throws CreatorException {
        try {
            Long roleId = resultSet.getLong(ROLE_ID);
            String roleValue = resultSet.getString(USER_ROLE);
            UserRole userRole = new UserRole(roleId, roleValue);
            Long id = resultSet.getLong(USER_ID);
            String login = resultSet.getString(U_LOGIN);
            String password = resultSet.getString(U_PASSWORD);
            String email = resultSet.getString(U_EMAIL);
            return new User(id, userRole, login, password, email);
        } catch (SQLException e) {
            throw new CreatorException(
                    "Error with create user entity " + e.getMessage(), e);
        }

    }

}

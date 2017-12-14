package com.petrushin.builder;

import com.petrushin.builder.exceptions.UserBuilderException;
import com.petrushin.domain.User;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserBuilder extends AbstractBuilder<User> {

    private static final String USER_ID = "user_id";
    private static final String ROLE_ID = "role_id";
    private static final String U_LOGIN = "u_login";
    private static final String U_PASSWORD = "u_password";
    private static final String U_EMAIL = "u_email";


    public void initStatement(User user, PreparedStatement statement)
            throws UserBuilderException {
        try {
            int roleId = user.getRoleId();
            statement.setInt(1, roleId);

            String login = user.getLogin();
            statement.setString(2, login);

            String password = user.getPassword();
            statement.setString(3, password);

            String email = user.getEmail();
            statement.setString(4, email);

            int id = user.getId();
            statement.setInt(5, id);
        } catch (SQLException e) {
            throw new UserBuilderException("Error with init user statement " + e.getMessage());
        }
    }

    public User createEntity(ResultSet resultSet)
            throws UserBuilderException {
        try {
            int id = resultSet.getInt(USER_ID);
            int roleId = resultSet.getInt(ROLE_ID);
            String login = resultSet.getString(U_LOGIN);
            String password = resultSet.getString(U_PASSWORD);
            String email = resultSet.getString(U_EMAIL);
            return new User(id, roleId, login, password, email);
        } catch (SQLException e) {
            throw new UserBuilderException("Error with create user entity " + e.getMessage());
        }


    }

}

package com.petrushin.epam.auction.model.creator.impl;

import com.petrushin.epam.auction.exceptions.CreatorException;
import com.petrushin.epam.auction.model.creator.AbstractCreator;
import com.petrushin.epam.auction.model.domain.User;
import com.petrushin.epam.auction.model.domain.UserRole;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserCreator extends AbstractCreator<User> {

    private static final String COLUMN_USER_ID = "user_id";
    private static final String COLUMN_ROLE_ID = "role_id";
    private static final String COLUMN_U_LOGIN = "u_login";
    private static final String COLUMN_U_PASSWORD = "u_password";
    private static final String COLUMN_U_EMAIL = "u_email";
    private static final String COLUMN_USER_ROLE = "user_role";

    public User createEntity(ResultSet resultSet) throws CreatorException {
        try {
            Long roleId = resultSet.getLong(COLUMN_ROLE_ID);
            String roleValue = resultSet.getString(COLUMN_USER_ROLE);
            UserRole userRole = new UserRole(roleId, roleValue);
            Long id = resultSet.getLong(COLUMN_USER_ID);
            String login = resultSet.getString(COLUMN_U_LOGIN);
            String password = resultSet.getString(COLUMN_U_PASSWORD);
            String email = resultSet.getString(COLUMN_U_EMAIL);
            return new User(id, userRole, login, password, email);
        } catch (SQLException e) {
            throw new CreatorException(
                    "Error with create user entity " + e.getMessage(), e);
        }

    }
}

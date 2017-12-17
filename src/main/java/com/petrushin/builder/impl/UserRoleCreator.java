package com.petrushin.builder.impl;

import com.petrushin.builder.AbstractCreator;
import com.petrushin.domain.UserRole;
import com.petrushin.exceptions.CreatorException;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserRoleCreator extends AbstractCreator<UserRole> {

    private static final String ROLE_ID = "role_id";
    private static final String USER_ROLE = "user_role";

    @Override
    public void initStatement(UserRole userRole,
                              PreparedStatement statement)
            throws CreatorException {
        try {
            String role = userRole.getRole();
            statement.setString(1, role);
            Long id = userRole.getId();
            statement.setLong(2, id);
        } catch (SQLException e) {
            throw new CreatorException(
                    "Error with init role statement" + e.getMessage(), e);
        }
    }

    @Override
    public UserRole createEntity(ResultSet resultSet)
            throws CreatorException {
        try {
            Long roleId = resultSet.getLong(ROLE_ID);
            String role = resultSet.getString(USER_ROLE);
            return new UserRole(roleId, role);
        } catch (SQLException e) {
            throw new CreatorException(
                    "Error with create role entity" + e.getMessage(), e);
        }
    }
}

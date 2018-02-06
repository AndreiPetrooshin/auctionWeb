package com.petrushin.epam.auction.dao.creator.impl;

import com.petrushin.epam.auction.dao.creator.AbstractCreator;
import com.petrushin.epam.auction.domain.UserRole;
import com.petrushin.epam.auction.exceptions.CreatorException;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Extends the {@link AbstractCreator} class
 * and creates the UserRole entity
 *
 * @author Andrei Petrushin
 * @version 1.0.0
 */
public class UserRoleCreator extends AbstractCreator<UserRole> {

    private static final String COLUMN_ROLE_ID = "role_id";
    private static final String COLUMN_USER_ROLE = "user_role";

    @Override
    public UserRole createEntity(ResultSet resultSet) throws CreatorException {
        try {
            Long roleId = resultSet.getLong(COLUMN_ROLE_ID);
            String role = resultSet.getString(COLUMN_USER_ROLE);
            return new UserRole(roleId, role);
        } catch (SQLException e) {
            throw new CreatorException(
                    "Error with create role entity" + e.getMessage(), e);
        }
    }
}

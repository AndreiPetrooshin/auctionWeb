package com.petrushin.builder;

import com.petrushin.builder.exceptions.AbstractBuilderException;
import com.petrushin.builder.exceptions.UserRoleBuilderException;
import com.petrushin.domain.UserRole;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserRoleBuilder extends AbstractBuilder<UserRole> {

    private static final String ROLE_ID = "role_id";
    private static final String USER_ROLE = "user_role";

    @Override
    public void initStatement(UserRole userRole,
                              PreparedStatement statement)
            throws AbstractBuilderException {
        try{String role = userRole.getRole();
        statement.setString(1,role);
        int id = userRole.getId();
        statement.setInt(2,id);}
        catch (SQLException e) {
            throw new UserRoleBuilderException(
                    "Error with init role statement" + e.getMessage());
        }
    }

    @Override
    public UserRole createEntity(ResultSet resultSet)
            throws AbstractBuilderException {
           try {
               UserRole userRole;
               int roleId = resultSet.getInt(ROLE_ID);
               String role = resultSet.getString(USER_ROLE);
               userRole = new UserRole(roleId, role);
               return userRole;
           } catch (SQLException e) {
               throw new UserRoleBuilderException(
                       "Error with create role entity" + e.getMessage());
        }
    }
}

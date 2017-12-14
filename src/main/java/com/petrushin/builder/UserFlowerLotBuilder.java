package com.petrushin.builder;

import com.petrushin.builder.exceptions.UserFlowerLotBuilderException;
import com.petrushin.domain.UserFlowerLot;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserFlowerLotBuilder extends AbstractBuilder<UserFlowerLot> {


    private static final String FL_ID = "fl_id";
    private static final String USER_ID = "user_id";
    private static final String FL_TYPE = "fl_type";
    private static final String FL_NAME = "fl_name";
    private static final String FL_DESCRIPTION = "fl_description";
    private static final String FL_START_PRICE = "fl_start_price";
    private static final String FL_STATE = "fl_state";

    @Override
    public UserFlowerLot createEntity(ResultSet resultSet)
            throws UserFlowerLotBuilderException {
        try {
            int lotId = resultSet.getInt(FL_ID);
            int userId = resultSet.getInt(USER_ID);
            String type = resultSet.getString(FL_TYPE);
            String name = resultSet.getString(FL_NAME);
            String description = resultSet.getString(FL_DESCRIPTION);
            double startPrice = resultSet.getDouble(FL_START_PRICE);
            String state = resultSet.getString(FL_STATE);

            return new UserFlowerLot(lotId, userId, type, name,
                    description, startPrice, state);
        } catch (SQLException e) {
            throw new UserFlowerLotBuilderException(
                    "Error with creation UserFlowerLot entity" + e.getMessage());
        }

    }

    @Override
    public void initStatement(UserFlowerLot lot,
                              PreparedStatement statement)
            throws UserFlowerLotBuilderException {
        try {
            int userId = lot.getUserId();
            statement.setInt(1, userId);

            String type = lot.getType();
            statement.setString(2, type);

            String name = lot.getName();
            statement.setString(3, name);

            String description = lot.getDescription();
            statement.setString(4, description);

            double startPrice = lot.getStartPrice();
            statement.setDouble(5, startPrice);

            int lotId = lot.getId();
            statement.setInt(6, lotId);
        } catch (SQLException e) {
            throw new UserFlowerLotBuilderException(
                    "Error with init  UserFlowerLot statement" + e.getMessage());
        }
    }
}

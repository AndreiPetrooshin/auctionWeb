package com.petrushin.builder.impl;

import com.petrushin.builder.AbstractCreator;
import com.petrushin.builder.Creator;
import com.petrushin.domain.User;
import com.petrushin.domain.UserFlowerLot;
import com.petrushin.exceptions.CreatorException;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserFlowerLotCreator extends AbstractCreator<UserFlowerLot> {


    private static final String FL_ID = "fl_id";
    private static final String FL_TYPE = "fl_type";
    private static final String FL_NAME = "fl_name";
    private static final String FL_DESCRIPTION = "fl_description";
    private static final String FL_START_PRICE = "fl_start_price";
    private static final String FL_STATE = "fl_state";

    private Creator<User> userCreator;

    public UserFlowerLotCreator(Creator<User> userCreator) {
        this.userCreator = userCreator;
    }

    @Override
    public UserFlowerLot createEntity(ResultSet resultSet)
            throws CreatorException {
        try {
            Long lotId = resultSet.getLong(FL_ID);
            User user = userCreator.createEntity(resultSet);
            String type = resultSet.getString(FL_TYPE);
            String name = resultSet.getString(FL_NAME);
            String description = resultSet.getString(FL_DESCRIPTION);
            BigDecimal startPrice = resultSet.getBigDecimal(FL_START_PRICE);
            String state = resultSet.getString(FL_STATE);

            return new UserFlowerLot(lotId, user, type, name,
                    description, startPrice, state);
        } catch (SQLException | CreatorException e) {
            throw new CreatorException(
                    "Error with creation UserFlowerLot entity" + e.getMessage(), e);
        }
    }

    @Override
    public void initStatement(UserFlowerLot lot,
                              PreparedStatement statement)
            throws CreatorException {
        try {
            User user = lot.getUser();
            Long userId = user.getId();
            statement.setLong(1, userId);

            String type = lot.getType();
            statement.setString(2, type);

            String name = lot.getName();
            statement.setString(3, name);

            String description = lot.getDescription();
            statement.setString(4, description);

            BigDecimal startPrice = lot.getStartPrice();
            statement.setBigDecimal(5, startPrice);

            Long lotId = lot.getId();
            statement.setLong(6, lotId);
        } catch (SQLException e) {
            throw new CreatorException(
                    "Error with init  UserFlowerLot statement" + e.getMessage(), e);
        }
    }
}

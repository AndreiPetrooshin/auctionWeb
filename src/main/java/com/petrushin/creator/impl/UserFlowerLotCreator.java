package com.petrushin.creator.impl;

import com.petrushin.creator.AbstractCreator;
import com.petrushin.creator.Creator;
import com.petrushin.domain.User;
import com.petrushin.domain.UserFlowerLot;
import com.petrushin.exceptions.CreatorException;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserFlowerLotCreator extends AbstractCreator<UserFlowerLot> {


    private static final String COLUMN_FL_ID = "fl_id";
    private static final String COLUMN_FL_TYPE = "fl_type";
    private static final String COLUMN_FL_NAME = "fl_name";
    private static final String COLUMN_FL_DESCRIPTION = "fl_description";
    private static final String COLUMN_FL_START_PRICE = "fl_start_price";
    private static final String COLUMN_FL_STATE = "fl_state";

    private Creator<User> userCreator;

    public UserFlowerLotCreator(Creator<User> userCreator) {
        this.userCreator = userCreator;
    }

    @Override
    public UserFlowerLot createEntity(ResultSet resultSet) throws CreatorException {
        try {
            Long lotId = resultSet.getLong(COLUMN_FL_ID);
            User user = userCreator.createEntity(resultSet);
            String type = resultSet.getString(COLUMN_FL_TYPE);
            String name = resultSet.getString(COLUMN_FL_NAME);
            String description = resultSet.getString(COLUMN_FL_DESCRIPTION);
            BigDecimal startPrice = resultSet.getBigDecimal(COLUMN_FL_START_PRICE);
            String state = resultSet.getString(COLUMN_FL_STATE);

            return new UserFlowerLot(lotId, user, type, name,
                    description, startPrice, state);
        } catch (SQLException | CreatorException e) {
            throw new CreatorException(
                    "Error with creation UserFlowerLot entity" + e.getMessage(), e);
        }
    }
}

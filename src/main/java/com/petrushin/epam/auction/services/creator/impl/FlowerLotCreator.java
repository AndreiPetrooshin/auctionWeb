package com.petrushin.epam.auction.services.creator.impl;

import com.petrushin.epam.auction.exceptions.CreatorException;
import com.petrushin.epam.auction.services.creator.AbstractCreator;
import com.petrushin.epam.auction.services.creator.Creator;
import com.petrushin.epam.auction.services.domain.FlowerLot;
import com.petrushin.epam.auction.services.domain.User;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Extends the {@link AbstractCreator} class
 * and creates the FlowerLot entity
 *
 * @author Andrei Petrushin
 * @version 1.0.0
 */
public class FlowerLotCreator extends AbstractCreator<FlowerLot> {


    private static final String COLUMN_FL_ID = "fl_id";
    private static final String COLUMN_FL_TYPE = "fl_type";
    private static final String COLUMN_FL_NAME = "fl_name";
    private static final String COLUMN_FL_DESCRIPTION = "fl_description";
    private static final String COLUMN_FL_START_PRICE = "fl_start_price";
    private static final String COLUMN_FL_STATE = "fl_state";

    private Creator<User> userCreator;

    public FlowerLotCreator(Creator<User> userCreator) {
        this.userCreator = userCreator;
    }

    @Override
    public FlowerLot createEntity(ResultSet resultSet) throws CreatorException {
        try {
            Long lotId = resultSet.getLong(COLUMN_FL_ID);
            User user = userCreator.createEntity(resultSet);
            String type = resultSet.getString(COLUMN_FL_TYPE);
            String name = resultSet.getString(COLUMN_FL_NAME);
            String description = resultSet.getString(COLUMN_FL_DESCRIPTION);
            BigDecimal startPrice = resultSet.getBigDecimal(COLUMN_FL_START_PRICE);
            String state = resultSet.getString(COLUMN_FL_STATE);

            return new FlowerLot(lotId, user, type, name,
                    description, startPrice, state);
        } catch (SQLException | CreatorException e) {
            throw new CreatorException(
                    "Error with creation FlowerLot entity" + e.getMessage(), e);
        }
    }
}

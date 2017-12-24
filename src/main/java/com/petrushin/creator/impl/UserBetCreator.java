package com.petrushin.creator.impl;

import com.petrushin.creator.AbstractCreator;
import com.petrushin.creator.Creator;
import com.petrushin.domain.User;
import com.petrushin.domain.UserBet;
import com.petrushin.domain.UserFlowerLot;
import com.petrushin.exceptions.CreatorException;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserBetCreator extends AbstractCreator<UserBet> {

    private static final String COLUMN_USER_BET = "user_bet";
    private static final String COLUMN_BET_ID = "bet_id";

    private Creator<User> userCreator;
    private Creator<UserFlowerLot> lotCreator;

    public UserBetCreator(Creator<User> userCreator,
                          Creator<UserFlowerLot> lotCreator) {
        this.userCreator = userCreator;
        this.lotCreator = lotCreator;
    }

    @Override
    public UserBet createEntity(ResultSet resultSet) throws CreatorException {
        try {
            User user = userCreator.createEntity(resultSet);
            UserFlowerLot lot = lotCreator.createEntity(resultSet);
            Long id = resultSet.getLong(COLUMN_BET_ID);
            BigDecimal bet = resultSet.getBigDecimal(COLUMN_USER_BET);

            return new UserBet(id, lot, user, bet);
        } catch (SQLException | CreatorException e) {
            throw new CreatorException(
                    "Error with creation bet entity " + e.getMessage(), e);
        }
    }
}

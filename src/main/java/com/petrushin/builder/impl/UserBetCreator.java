package com.petrushin.builder.impl;

import com.petrushin.builder.AbstractCreator;
import com.petrushin.builder.Creator;
import com.petrushin.domain.User;
import com.petrushin.domain.UserBet;
import com.petrushin.domain.UserFlowerLot;
import com.petrushin.exceptions.CreatorException;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserBetCreator extends AbstractCreator<UserBet> {

    private static final String USER_BET = "user_bet";
    private static final String BET_ID = "bet_id";

    private Creator<User> userCreator;
    private Creator<UserFlowerLot> lotCreator;

    public UserBetCreator(Creator<User> userCreator,
                          Creator<UserFlowerLot> lotCreator) {
        this.userCreator = userCreator;
        this.lotCreator = lotCreator;
    }

    @Override
    public UserBet createEntity(ResultSet resultSet)
            throws CreatorException {
        try {
            User user = userCreator.createEntity(resultSet);
            UserFlowerLot lot = lotCreator.createEntity(resultSet);
            Long id = resultSet.getLong(BET_ID);
            BigDecimal bet = resultSet.getBigDecimal(USER_BET);

            return new UserBet(id, lot, user, bet);
        } catch (SQLException | CreatorException e) {
            throw new CreatorException(
                    "Error with creation bet entity " + e.getMessage(), e);
        }
    }

    @Override
    public void initStatement(UserBet userBet,
                              PreparedStatement statement)
            throws CreatorException {
        try {
            UserFlowerLot lot = userBet.getLot();
            Long lotId = lot.getId();
            statement.setLong(1, lotId);

            User user = userBet.getUser();
            Long userId = user.getId();
            statement.setLong(2, userId);

            BigDecimal bet = userBet.getBet();
            statement.setBigDecimal(3, bet);

            Long id = userBet.getId();
            statement.setLong(4, id);


        } catch (SQLException e) {
            throw new CreatorException(
                    "Error with init bet statement" + e.getMessage(), e);
        }
    }
}

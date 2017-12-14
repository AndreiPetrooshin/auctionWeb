package com.petrushin.builder;

import com.petrushin.builder.exceptions.UserBetBuilderException;
import com.petrushin.domain.UserBet;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserBetBuilder extends AbstractBuilder<UserBet> {

    private static final String FL_ID = "fl_id";
    private static final String USER_ID = "user_id";
    private static final String USER_BET = "user_bet";

    @Override
    public UserBet createEntity(ResultSet resultSet)
            throws UserBetBuilderException {
       try{  int lotId = resultSet.getInt(FL_ID);
        int userId = resultSet.getInt(USER_ID);
        double bet = resultSet.getDouble(USER_BET);

        return new UserBet(lotId, userId, bet); }
        catch (SQLException e) {
           throw new UserBetBuilderException(
                   "Error with creation bet entity " + e.getMessage());
        }
    }

    @Override
    public void initStatement(UserBet userBet,
                              PreparedStatement statement)
            throws UserBetBuilderException {
       try{ int userId = userBet.getUserId();
        statement.setInt(1,userId);
        double bet = userBet.getBet();
        statement.setDouble(2,bet);
        int lotId = userBet.getLotId();
        statement.setInt(3,lotId); }
        catch (SQLException e) {
           throw new UserBetBuilderException(
                   "Error with init bet statement" + e.getMessage());
        }
    }
}

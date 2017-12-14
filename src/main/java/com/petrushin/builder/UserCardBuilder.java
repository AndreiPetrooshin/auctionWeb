package com.petrushin.builder;


import com.petrushin.builder.exceptions.UserCardBuilderException;
import com.petrushin.domain.UserCard;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserCardBuilder extends AbstractBuilder<UserCard> {

    private static final String CARD_ID = "card_id";
    private static final String USER_ID = "user_id";
    private static final String CARD_NUMBER = "card_number";
    private static final String CARD_NAME = "card_name";

    public UserCard createEntity(ResultSet resultSet)
            throws UserCardBuilderException {
        try {
            UserCard userCard;
            int cardId = resultSet.getInt(CARD_ID);
            int userId = resultSet.getInt(USER_ID);
            String cardNumber = resultSet.getString(CARD_NUMBER);
            String cardName = resultSet.getString(CARD_NAME);

            userCard = new UserCard(cardId, userId, cardNumber, cardName);
            return userCard;
        } catch (SQLException e) {
           throw new UserCardBuilderException(
                   "Error with creating UserCard " +e.getMessage());
        }
    }

    public void initStatement(UserCard card, PreparedStatement statement)
            throws UserCardBuilderException {
        try {

            int userId = card.getUserId();
            statement.setInt(1, userId);

            String cardNumber = card.getCardNumber();
            statement.setString(2, cardNumber);

            String cardName = card.getCardName();
            statement.setString(3, cardName);

            int cardId = card.getId();
            statement.setInt(4,cardId);
        } catch (SQLException e) {
            throw new UserCardBuilderException(
                    "Error with init userCard statement " + e.getMessage());
        }
    }
}

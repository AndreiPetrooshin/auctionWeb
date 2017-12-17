package com.petrushin.builder.impl;


import com.petrushin.builder.AbstractCreator;
import com.petrushin.builder.Creator;
import com.petrushin.domain.User;
import com.petrushin.domain.UserCard;
import com.petrushin.exceptions.CreatorException;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserCardCreator extends AbstractCreator<UserCard> {

    private static final String CARD_ID = "card_id";
    private static final String CARD_NUMBER = "card_number";
    private static final String CARD_NAME = "card_name";

    private Creator<User> userCreator;

    public UserCardCreator(Creator<User> userCreator) {
        this.userCreator = userCreator;
    }

    public UserCard createEntity(ResultSet resultSet)
            throws CreatorException {
        try {
            User user = userCreator.createEntity(resultSet);
            Long cardId = resultSet.getLong(CARD_ID);
            String cardNumber = resultSet.getString(CARD_NUMBER);
            String cardName = resultSet.getString(CARD_NAME);

            return new UserCard(cardId, user, cardNumber, cardName);
        } catch (SQLException | CreatorException e) {
            throw new CreatorException(
                    "Error with creating UserCard " + e.getMessage(), e);
        }
    }

    public void initStatement(UserCard card, PreparedStatement statement)
            throws CreatorException {
        try {

            User user = card.getUser();
            Long userId = user.getId();
            statement.setLong(1, userId);

            String cardNumber = card.getCardNumber();
            statement.setString(2, cardNumber);

            String cardName = card.getCardName();
            statement.setString(3, cardName);

            Long cardId = card.getId();
            statement.setLong(4, cardId);
        } catch (SQLException e) {
            throw new CreatorException(
                    "Error with init userCard statement " + e.getMessage(), e);
        }
    }
}

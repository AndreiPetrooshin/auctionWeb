package com.petrushin.model.creator.impl;


import com.petrushin.model.creator.AbstractCreator;
import com.petrushin.model.creator.Creator;
import com.petrushin.model.domain.User;
import com.petrushin.model.domain.UserCard;
import com.petrushin.exceptions.CreatorException;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserCardCreator extends AbstractCreator<UserCard> {

    private static final String COLUMN_CARD_ID = "card_id";
    private static final String COLUMN_CARD_NUMBER = "card_number";
    private static final String COLUMN_CARD_NAME = "card_name";

    private Creator<User> userCreator;

    public UserCardCreator(Creator<User> userCreator) {
        this.userCreator = userCreator;
    }

    public UserCard createEntity(ResultSet resultSet) throws CreatorException {
        try {
            User user = userCreator.createEntity(resultSet);
            Long cardId = resultSet.getLong(COLUMN_CARD_ID);
            String cardNumber = resultSet.getString(COLUMN_CARD_NUMBER);
            String cardName = resultSet.getString(COLUMN_CARD_NAME);

            return new UserCard(cardId, user, cardNumber, cardName);
        } catch (SQLException | CreatorException e) {
            throw new CreatorException(
                    "Error with creating UserCard " + e.getMessage(), e);
        }
    }

}

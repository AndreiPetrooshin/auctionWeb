package com.petrushin.epam.auction.services.creator.impl;


import com.petrushin.epam.auction.exceptions.CreatorException;
import com.petrushin.epam.auction.services.creator.AbstractCreator;
import com.petrushin.epam.auction.services.creator.Creator;
import com.petrushin.epam.auction.services.domain.User;
import com.petrushin.epam.auction.services.domain.UserCard;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Extends the {@link AbstractCreator} class
 * and creates the UserCard entity
 *
 * @author Andrei Petrushin
 * @version 1.0.0
 */
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

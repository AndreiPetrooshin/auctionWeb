package com.petrushin.model.dao.impl;

import com.petrushin.model.creator.Creator;
import com.petrushin.model.dao.AbstractDao;
import com.petrushin.model.domain.User;
import com.petrushin.model.domain.UserCard;
import com.petrushin.exceptions.CreatorException;
import com.petrushin.exceptions.EntityDAOException;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class UserCardDaoImpl extends AbstractDao<UserCard> {

    public UserCardDaoImpl(Creator<UserCard> creator) {
        super(creator);
    }

    public UserCard findById(Long id) throws EntityDAOException {
        return getByPK(id, UserCard.GET_BY_ID);
    }

    public List<UserCard> getAll() throws EntityDAOException {
        return getAll(UserCard.GET_ALL);
    }

    public boolean save(UserCard card) throws EntityDAOException {
        return save(card, UserCard.ADD_CARD);
    }

    public boolean update(UserCard card) throws EntityDAOException {
        return update(card, UserCard.UPDATE_USER_CARD);
    }

    public boolean delete(Long id) throws EntityDAOException {
       return delete(id, UserCard.DELETE_USER_CARD);
    }

    public void prepareStatementForUpdate(UserCard card, PreparedStatement statement)
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

    @Override
    protected void prepareStatementForInsert(UserCard userCard, PreparedStatement statement)
            throws CreatorException {
        prepareStatementForUpdate(userCard,statement);
    }

}

package com.petrushin.epam.auction.services.dao.impl;

import com.petrushin.epam.auction.exceptions.CreatorException;
import com.petrushin.epam.auction.exceptions.EntityDAOException;
import com.petrushin.epam.auction.services.creator.Creator;
import com.petrushin.epam.auction.services.dao.AbstractDao;
import com.petrushin.epam.auction.services.dao.ConnectionPool;
import com.petrushin.epam.auction.services.domain.User;
import com.petrushin.epam.auction.services.domain.UserAddress;
import com.petrushin.epam.auction.services.domain.UserCard;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * Dao class witch extends {@link AbstractDao} and implements methods for
 * {@link UserCard} entity
 *
 * @author Andrei Petrushin
 * @version 1.0.0
 */
public class UserCardDao extends AbstractDao<UserCard> {

    private static final Logger LOGGER = LogManager.getLogger(UserCardDao.class);

    public UserCardDao(Creator<UserCard> creator) {
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

    public List<UserCard> getByUserId(Long id) throws EntityDAOException {
        Connection connection = ConnectionPool.getInstance().getConnection();
        try (PreparedStatement statement = connection.prepareStatement(UserCard.GET_BY_USER_ID)) {
            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();
            return creator.createEntityList(resultSet);
        } catch (SQLException | CreatorException e) {
            LOGGER.error("Error with getByUserId operation", e);
            throw new EntityDAOException(e.getMessage(), e);
        } finally {
            ConnectionPool.getInstance().returnConnection(connection);
        }
    }

    public void prepareStatementForUpdate(UserCard card, PreparedStatement statement)
            throws EntityDAOException {
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
            throw new EntityDAOException(
                    "Error with init userCard statement " + e.getMessage(), e);
        }
    }

    @Override
    protected void prepareStatementForInsert(UserCard userCard, PreparedStatement statement)
            throws EntityDAOException {
        prepareStatementForUpdate(userCard, statement);
    }
}

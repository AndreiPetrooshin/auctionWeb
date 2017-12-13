package com.petrushin.dao.impl;

import com.petrushin.dao.ConnectionPool;
import com.petrushin.dao.EntityDAO;
import com.petrushin.dao.exception.UserCardDAOException;
import com.petrushin.dao.exception.UserRoleDAOException;
import com.petrushin.domain.UserCard;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserCardDAOImpl implements EntityDAO<UserCard> {

    @Override
    public UserCard findById(int id) throws UserCardDAOException {
        ResultSet resultSet = null;
        Connection connection = null;
        PreparedStatement statement = null;
        UserCard userCard = null;
        ConnectionPool connectionPool = new ConnectionPool();
        try {
            DataSource dataSource = connectionPool.setUpPool();
            connection = dataSource.getConnection();
            statement = connection.prepareStatement(UserCard.GET_BY_ID);
            statement.setInt(1, id);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                userCard = createUserCard(resultSet);
            }

        } catch (ClassNotFoundException | SQLException e) {
            throw new UserCardDAOException(e.getMessage());
        } finally {
            closeAll(resultSet, connection, statement);
        }
        return userCard;
    }

    @Override
    public List<UserCard> getAll() throws UserCardDAOException {
        ResultSet resultSet = null;
        Connection connection = null;
        PreparedStatement statement = null;
        List<UserCard> listUserCards = new ArrayList<>();
        ConnectionPool connectionPool = new ConnectionPool();
        try {
            DataSource dataSource = connectionPool.setUpPool();
            connection = dataSource.getConnection();
            statement = connection.prepareStatement(UserCard.GET_ALL);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                UserCard userCard= createUserCard(resultSet);
                listUserCards.add(userCard);
            }

        } catch (ClassNotFoundException | SQLException e) {
            throw new UserCardDAOException(e.getMessage());
        } finally {
            closeAll(resultSet, connection, statement);
        }
        return listUserCards;
    }

    @Override
    public void add(UserCard card) throws UserCardDAOException {
        Connection connection = null;
        PreparedStatement statement = null;
        ConnectionPool connectionPool = new ConnectionPool();
        try {
            DataSource dataSource = connectionPool.setUpPool();
            connection = dataSource.getConnection();
            statement = connection.prepareStatement(UserCard.ADD_CARD);

            initStatement(card, statement);

            statement.executeUpdate();
        } catch (ClassNotFoundException | SQLException e) {
            throw new UserCardDAOException(e.getMessage());
        } finally {
            closeAll(null, connection, statement);
        }

    }

    private UserCard createUserCard(ResultSet resultSet) throws SQLException {
        UserCard userCard;
        int cardId = resultSet.getInt("card_id");
        int userId = resultSet.getInt("user_id");
        String cardNumber = resultSet.getString("card_number");
        String cardName = resultSet.getString("card_name");

        userCard = new UserCard(cardId, userId, cardNumber, cardName);
        return userCard;
    }

    private void initStatement(UserCard card, PreparedStatement statement) throws SQLException {
        int userId = card.getUserId();
        String cardNumber = card.getCardNumber();
        String cardName = card.getCardName();

        statement.setInt(1, userId);
        statement.setString(2,cardNumber);
        statement.setString(3,cardName);
    }

    private void closeAll(ResultSet resultSet,
                          Connection connection, PreparedStatement statement)
            throws UserCardDAOException {

        if (resultSet != null) {
            try {
                resultSet.close();
            } catch (SQLException e) {
                throw new UserCardDAOException("Error with resultSetClose",e);
            }
        }
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                throw new UserCardDAOException("Error with Connection close", e);
            }
        }
        if (statement != null) {
            try {
                statement.close();
            } catch (SQLException e) {
                throw new UserCardDAOException("Error with statement close", e);
            }
        }
    }

}

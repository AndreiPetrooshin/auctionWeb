package com.petrushin.epam.auction.dao.impl;

import com.petrushin.epam.auction.dao.AbstractDao;
import com.petrushin.epam.auction.dao.ConnectionPool;
import com.petrushin.epam.auction.dao.creator.Creator;
import com.petrushin.epam.auction.domain.User;
import com.petrushin.epam.auction.domain.UserRole;
import com.petrushin.epam.auction.exceptions.CreatorException;
import com.petrushin.epam.auction.exceptions.EntityDAOException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * Dao class witch extends {@link AbstractDao} and implements methods for
 * {@link User} entity
 *
 * @author Andrei Petrushin
 * @version 1.0.0
 */
public class UserDao extends AbstractDao<User> {

    private static final Logger LOGGER = LogManager.getLogger(UserDao.class);


    public UserDao(Creator<User> creator) {
        super(creator);
    }

    public User findById(Long id) throws EntityDAOException {
        return getByPK(id, User.GET_BY_ID);
    }

    public boolean delete(Long id) throws EntityDAOException {
        return delete(id, User.DELETE_BY_ID);
    }

    public boolean update(User user) throws EntityDAOException {
        return update(user, User.UPDATE_USER);
    }

    public List<User> getAll() throws EntityDAOException {
        return getAll(User.GET_ALL);
    }

    public boolean save(User user) throws EntityDAOException {
        return save(user, User.ADD_USER);
    }


    public User getByLogin(String login) throws EntityDAOException {
        Connection connection = ConnectionPool.getInstance().getConnection();
        try (PreparedStatement statement = connection.prepareStatement(User.GET_BY_LOGIN)) {
            statement.setString(1, login);
            ResultSet resultSet = statement.executeQuery();
            User user= null;
            if (resultSet.next()) {
                user = creator.createEntity(resultSet);
            }
            return user;
        } catch (SQLException | CreatorException e) {
            LOGGER.error("Error with getByLogin operation", e);
            throw new EntityDAOException(e.getMessage(), e);
        } finally {
            ConnectionPool.getInstance().returnConnection(connection);
        }
    }

    public void prepareStatementForUpdate(User user, PreparedStatement statement)
            throws EntityDAOException {
        try {
            UserRole role = user.getRole();
            Long roleId = role.getId();
            statement.setLong(1, roleId);

            String login = user.getLogin();
            statement.setString(2, login);

            String password = user.getPassword();
            statement.setString(3, password);

            String email = user.getEmail();
            statement.setString(4, email);

            Long id = user.getId();
            statement.setLong(5, id);
        } catch (SQLException e) {
            throw new EntityDAOException(
                    "Error with init user statement " + e.getMessage(), e);
        }
    }


    @Override
    protected void prepareStatementForInsert(User user, PreparedStatement statement)
            throws EntityDAOException {
        prepareStatementForUpdate(user, statement);
    }
}

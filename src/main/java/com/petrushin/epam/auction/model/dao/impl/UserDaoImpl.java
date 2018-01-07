package com.petrushin.epam.auction.model.dao.impl;

import com.petrushin.epam.auction.exceptions.EntityDAOException;
import com.petrushin.epam.auction.model.creator.Creator;
import com.petrushin.epam.auction.model.dao.AbstractDao;
import com.petrushin.epam.auction.model.domain.User;
import com.petrushin.epam.auction.model.domain.UserRole;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

/**
 * Dao class witch extends {@link AbstractDao} and implements methods for
 * {@link User} entity
 *
 * @author Andrei Petrushin
 * @version 1.0.0
 */
public class UserDaoImpl extends AbstractDao<User> {


    public UserDaoImpl(Creator<User> creator) {
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

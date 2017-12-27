package com.petrushin.model.dao.impl;

import com.petrushin.exceptions.CreatorException;
import com.petrushin.exceptions.EntityDAOException;
import com.petrushin.model.creator.Creator;
import com.petrushin.model.dao.AbstractDao;
import com.petrushin.model.domain.User;
import com.petrushin.model.domain.UserRole;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class UserDaoImpl extends AbstractDao<User> {


    private static final String PARAM_U_LOGIN = "u_login";
    private static final String PARAM_U_EMAIL = "u_email";

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
            throws CreatorException {
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
            throw new CreatorException(
                    "Error with init user statement " + e.getMessage(), e);
        }
    }

    @Override
    protected void prepareStatementForInsert(User user, PreparedStatement statement)
            throws CreatorException {
        prepareStatementForUpdate(user, statement);
    }


}

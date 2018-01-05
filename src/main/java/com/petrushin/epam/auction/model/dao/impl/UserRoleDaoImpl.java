package com.petrushin.epam.auction.model.dao.impl;

import com.petrushin.epam.auction.exceptions.CreatorException;
import com.petrushin.epam.auction.exceptions.EntityDAOException;
import com.petrushin.epam.auction.model.creator.Creator;
import com.petrushin.epam.auction.model.dao.AbstractDao;
import com.petrushin.epam.auction.model.domain.UserRole;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

/**
 * Dao class witch extends {@link AbstractDao} and implements methods for
 * {@link UserRole} entity
 *
 * @author Andrei Petrushin
 * @version 1.0.0
 */
public class UserRoleDaoImpl extends AbstractDao<UserRole> {

    public UserRoleDaoImpl(Creator<UserRole> creator) {
        super(creator);
    }

    public UserRole findById(Long id) throws EntityDAOException {
        return getByPK(id, UserRole.GET_BY_ID);
    }

    public List<UserRole> getAll() throws EntityDAOException {
        return getAll(UserRole.GET_ALL);

    }

    public boolean save(UserRole userRole) throws EntityDAOException {
        return save(userRole, UserRole.ADD_ROLE);
    }

    public boolean update(UserRole role) throws EntityDAOException {
        return update(role, UserRole.UPDATE_ROLE);
    }

    public boolean delete(Long id) throws EntityDAOException {
        return delete(id, UserRole.DELETE_BY_ID);
    }

    @Override
    public void prepareStatementForUpdate(UserRole userRole, PreparedStatement statement)
            throws CreatorException {
        try {
            String role = userRole.getRole();
            statement.setString(1, role);
            Long id = userRole.getId();
            statement.setLong(2, id);
        } catch (SQLException e) {
            throw new CreatorException(
                    "Error with init role statement" + e.getMessage(), e);
        }
    }

    @Override
    protected void prepareStatementForInsert(UserRole userRole, PreparedStatement statement)
            throws CreatorException {
        prepareStatementForUpdate(userRole, statement);
    }

}

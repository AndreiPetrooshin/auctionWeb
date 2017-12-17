package com.petrushin.dao.impl;

import com.petrushin.builder.Builder;
import com.petrushin.dao.AbstractDAO;
import com.petrushin.dao.exception.AbstractDAOException;
import com.petrushin.dao.exception.UserRoleDAOException;
import com.petrushin.domain.UserRole;
import java.util.List;

public class UserRoleDAOImpl extends AbstractDAO<UserRole> {

    public UserRoleDAOImpl(Builder<UserRole> builder) {
        super(builder);
    }

    public UserRole findById(Long id)
            throws UserRoleDAOException {
        try {
          return findById(id, UserRole.GET_BY_ID);
        } catch (AbstractDAOException e) {
            throw new UserRoleDAOException(e.getMessage());
        }
    }

    public List<UserRole> getAll()
            throws UserRoleDAOException {
        try {
            return getAll(UserRole.GET_ALL);
        } catch (AbstractDAOException e) {
            throw new UserRoleDAOException(e.getMessage());
        }
    }

    public boolean add(UserRole userRole)
            throws UserRoleDAOException {
        try {
            return add(userRole, UserRole.ADD_ROLE);
        } catch (AbstractDAOException e) {
            throw new UserRoleDAOException(e.getMessage());
        }
    }

    public boolean update(UserRole role)
            throws UserRoleDAOException {
        try {
            return update(role, UserRole.UPDATE_ROLE);
        } catch (AbstractDAOException e) {
            throw new UserRoleDAOException(e.getMessage());
        }
    }

    public boolean delete(Long id)
            throws UserRoleDAOException {
        try {
            return delete(id, UserRole.DELETE_BY_ID);
        } catch (AbstractDAOException e) {
            throw new UserRoleDAOException(e.getMessage());
        }
    }

}

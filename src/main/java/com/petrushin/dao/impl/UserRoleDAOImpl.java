package com.petrushin.dao.impl;

import com.petrushin.builder.Creator;
import com.petrushin.dao.AbstractDAO;
import com.petrushin.domain.UserRole;
import com.petrushin.exceptions.EntityDAOException;

import java.util.List;

public class UserRoleDAOImpl extends AbstractDAO<UserRole> {

    public UserRoleDAOImpl(Creator<UserRole> creator) {
        super(creator);
    }

    public UserRole findById(Long id)
            throws EntityDAOException {
        try {
            return findById(id, UserRole.GET_BY_ID);
        } catch (EntityDAOException e) {
            throw new EntityDAOException(
                    "FindByID UserRole Error" + e.getMessage(), e);
        }
    }

    public List<UserRole> getAll()
            throws EntityDAOException {
        try {
            return getAll(UserRole.GET_ALL);
        } catch (EntityDAOException e) {
            throw new EntityDAOException(
                    "GetAll UserRole Error" + e.getMessage(), e);
        }
    }

    public boolean save(UserRole userRole)
            throws EntityDAOException {
        try {
            return save(userRole, UserRole.ADD_ROLE);
        } catch (EntityDAOException e) {
            throw new EntityDAOException(
                    "Save UserRole Error" + e.getMessage(), e);
        }
    }

    public boolean update(UserRole role)
            throws EntityDAOException {
        try {
            return update(role, UserRole.UPDATE_ROLE);
        } catch (EntityDAOException e) {
            throw new EntityDAOException(
                    "Update UserRole Error" + e.getMessage(), e);
        }
    }

    public boolean delete(Long id)
            throws EntityDAOException {
        try {
            return delete(id, UserRole.DELETE_BY_ID);
        } catch (EntityDAOException e) {
            throw new EntityDAOException(
                    "Delete UserRole Error" + e.getMessage(), e);
        }
    }

}

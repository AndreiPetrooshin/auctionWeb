package com.petrushin.services;

import com.petrushin.model.dao.impl.UserRoleDaoImpl;
import com.petrushin.model.domain.UserRole;
import com.petrushin.exceptions.EntityDAOException;

import java.util.List;

public class UserRoleService implements Service<UserRole> {

    private UserRoleDaoImpl userRoleDao;

    public UserRoleService(UserRoleDaoImpl userRoleDao) {
        this.userRoleDao = userRoleDao;
    }

    @Override
    public UserRole findById(Long id) throws EntityDAOException {
        return userRoleDao.findById(id);
}

    @Override
    public List<UserRole> getAll() throws EntityDAOException {
        return userRoleDao.getAll();
    }

    @Override
    public boolean save(UserRole userRole) throws EntityDAOException {
        return userRoleDao.save(userRole);
    }

    @Override
    public boolean delete(Long id) throws EntityDAOException {
        return userRoleDao.delete(id);
    }

    @Override
    public boolean update(UserRole userRole) throws EntityDAOException {
        return userRoleDao.update(userRole);
    }
}
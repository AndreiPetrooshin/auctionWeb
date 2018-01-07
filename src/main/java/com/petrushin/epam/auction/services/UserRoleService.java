package com.petrushin.epam.auction.services;

import com.petrushin.epam.auction.exceptions.ServiceException;
import com.petrushin.epam.auction.model.dao.impl.UserRoleDaoImpl;
import com.petrushin.epam.auction.model.domain.UserRole;

import java.util.List;

/**
 * Service class which do the main business logic
 * with {@link UserRole} entity
 *
 * @author Andrei Petrushin
 * @version 1.0.0
 */
public class UserRoleService implements Service<UserRole> {

    private UserRoleDaoImpl userRoleDao;

    public UserRoleService(UserRoleDaoImpl userRoleDao) {
        this.userRoleDao = userRoleDao;
    }

    @Override
    public UserRole findById(Long id) throws ServiceException {
        return userRoleDao.findById(id);
    }

    @Override
    public List<UserRole> getAll() throws ServiceException {
        return userRoleDao.getAll();
    }

    @Override
    public boolean save(UserRole userRole) throws ServiceException {
        return userRoleDao.save(userRole);
    }

    @Override
    public boolean delete(Long id) throws ServiceException {
        return userRoleDao.delete(id);
    }

    @Override
    public boolean update(UserRole userRole) throws ServiceException {
        return userRoleDao.update(userRole);
    }
}

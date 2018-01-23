package com.petrushin.epam.auction.services;

import com.petrushin.epam.auction.exceptions.EntityDAOException;
import com.petrushin.epam.auction.dao.impl.UserRoleDao;
import com.petrushin.epam.auction.domain.UserRole;

import java.util.List;

/**
 * Service class which do the main business logic
 * with {@link UserRole} entity
 *
 * @author Andrei Petrushin
 * @version 1.0.0
 */
public class UserRoleService implements Service<UserRole> {

    private UserRoleDao userRoleDao;

    public UserRoleService(UserRoleDao userRoleDao) {
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

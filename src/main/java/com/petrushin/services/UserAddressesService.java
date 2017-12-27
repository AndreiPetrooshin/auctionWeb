package com.petrushin.services;

import com.petrushin.model.dao.impl.UserAddressesDaoImpl;
import com.petrushin.model.domain.UserAddresses;
import com.petrushin.exceptions.EntityDAOException;

import java.util.List;

public class UserAddressesService implements Service<UserAddresses> {

    private UserAddressesDaoImpl userAddressesDao;

    public UserAddressesService(UserAddressesDaoImpl userAddressesDao) {
        this.userAddressesDao = userAddressesDao;
    }

    @Override
    public UserAddresses findById(Long id) throws EntityDAOException {
        return userAddressesDao.findById(id);
    }

    @Override
    public List<UserAddresses> getAll() throws EntityDAOException {
        return userAddressesDao.getAll();
    }

    @Override
    public boolean save(UserAddresses userAddresses) throws EntityDAOException {
        return userAddressesDao.save(userAddresses);
    }

    @Override
    public boolean delete(Long id) throws EntityDAOException {
        return userAddressesDao.delete(id);
    }

    @Override
    public boolean update(UserAddresses userAddresses) throws EntityDAOException {
        return userAddressesDao.update(userAddresses);
    }
}
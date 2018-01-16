package com.petrushin.epam.auction.services;

import com.petrushin.epam.auction.exceptions.EntityDAOException;
import com.petrushin.epam.auction.services.dao.impl.UserAddressDao;
import com.petrushin.epam.auction.services.domain.User;
import com.petrushin.epam.auction.services.domain.UserAddress;

import java.util.List;
import java.util.Objects;

/**
 * Service class which do the main business logic
 * with {@link UserAddress} entity
 *
 * @author Andrei Petrushin
 * @version 1.0.0
 */
public class UserAddressesService implements Service<UserAddress> {

    private UserAddressDao userAddressesDao;

    public UserAddressesService(UserAddressDao userAddressesDao) {
        this.userAddressesDao = userAddressesDao;
    }

    @Override
    public UserAddress findById(Long id) throws EntityDAOException {
        return userAddressesDao.findById(id);
    }

    @Override
    public List<UserAddress> getAll() throws EntityDAOException {
        return userAddressesDao.getAll();
    }

    public UserAddress getByUserId(Long id) throws EntityDAOException {
        return userAddressesDao.getByUserId(id);
    }

    @Override
    public boolean save(UserAddress userAddress) throws EntityDAOException {
        return userAddressesDao.save(userAddress);
    }

    @Override
    public boolean delete(Long id) throws EntityDAOException {
        return userAddressesDao.delete(id);
    }

    @Override
    public boolean update(UserAddress userAddress) throws EntityDAOException {
        return userAddressesDao.update(userAddress);
    }
}

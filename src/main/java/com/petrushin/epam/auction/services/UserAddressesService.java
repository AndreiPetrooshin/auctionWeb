package com.petrushin.epam.auction.services;

import com.petrushin.epam.auction.exceptions.ServiceException;
import com.petrushin.epam.auction.model.dao.impl.UserAddressDaoImpl;
import com.petrushin.epam.auction.model.domain.User;
import com.petrushin.epam.auction.model.domain.UserAddress;

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

    private UserAddressDaoImpl userAddressesDao;

    public UserAddressesService(UserAddressDaoImpl userAddressesDao) {
        this.userAddressesDao = userAddressesDao;
    }

    @Override
    public UserAddress findById(Long id) throws ServiceException {
        return userAddressesDao.findById(id);
    }

    @Override
    public List<UserAddress> getAll() throws ServiceException {
        return userAddressesDao.getAll();
    }

    public UserAddress getByUserId(Long id) throws ServiceException {
        List<UserAddress> addresses = getAll();
        UserAddress userAddress = null;
        for (UserAddress address : addresses) {
            User user = address.getUser();
            Long userId = user.getId();
            if (Objects.equals(userId, id)) {
                userAddress = address;
            }
        }
        return userAddress;
    }

    @Override
    public boolean save(UserAddress userAddress) throws ServiceException {
        return userAddressesDao.save(userAddress);
    }

    @Override
    public boolean delete(Long id) throws ServiceException {
        return userAddressesDao.delete(id);
    }

    @Override
    public boolean update(UserAddress userAddress) throws ServiceException {
        return userAddressesDao.update(userAddress);
    }
}

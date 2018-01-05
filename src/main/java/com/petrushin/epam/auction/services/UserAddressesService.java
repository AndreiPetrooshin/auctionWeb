package com.petrushin.epam.auction.services;

import com.petrushin.epam.auction.exceptions.EntityDAOException;
import com.petrushin.epam.auction.model.dao.impl.UserAddressDaoImpl;
import com.petrushin.epam.auction.model.domain.User;
import com.petrushin.epam.auction.model.domain.UserAddress;

import java.util.ArrayList;
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
    public UserAddress findById(Long id) throws EntityDAOException {
        return userAddressesDao.findById(id);
    }

    @Override
    public List<UserAddress> getAll() throws EntityDAOException {
        return userAddressesDao.getAll();
    }

    public List<UserAddress> getByUserId(Long id) throws EntityDAOException {
        List<UserAddress> addresses = getAll();
        List<UserAddress> result = new ArrayList<>();
        for (UserAddress address : addresses) {
            User user = address.getUser();
            Long userId = user.getId();
            if (Objects.equals(userId, id)) {
                result.add(address);
            }
        }
        return result;
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

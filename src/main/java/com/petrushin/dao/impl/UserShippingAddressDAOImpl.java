package com.petrushin.dao.impl;

import com.petrushin.builder.Builder;
import com.petrushin.dao.AbstractDAO;
import com.petrushin.dao.exception.AbstractDAOException;
import com.petrushin.dao.exception.UserShippingAddressDAOException;
import com.petrushin.domain.UserShippingAddress;

import java.util.List;

public class UserShippingAddressDAOImpl extends AbstractDAO<UserShippingAddress> {


    public UserShippingAddressDAOImpl(
            Builder<UserShippingAddress> builder) {
        super(builder);
    }

    public UserShippingAddress findById(Long id)
            throws UserShippingAddressDAOException {
        try {
            return findById(id, UserShippingAddress.GET_BY_ID);
        } catch (AbstractDAOException e) {
            throw new UserShippingAddressDAOException(e.getMessage());
        }
    }

    public List<UserShippingAddress> getAll()
            throws UserShippingAddressDAOException {
        try {
            return getAll(UserShippingAddress.GET_ALL);
        } catch (AbstractDAOException e) {
            throw new UserShippingAddressDAOException(e.getMessage());
        }
    }

    public boolean add(UserShippingAddress address)
            throws UserShippingAddressDAOException {
        try {
            return add(address, UserShippingAddress.ADD_ADDRESS);
        } catch (AbstractDAOException e) {
            throw new UserShippingAddressDAOException(e.getMessage());
        }
    }

    public boolean delete(Long id)
            throws UserShippingAddressDAOException {
        try {
            return delete(id, UserShippingAddress.DELETE_BY_ID);
        } catch (AbstractDAOException e) {
            throw new UserShippingAddressDAOException(e.getMessage());
        }
    }

    public boolean update(UserShippingAddress address)
            throws UserShippingAddressDAOException {
        try {
            return update(address, UserShippingAddress.UPDATE_ADDRESS);
        } catch (AbstractDAOException e) {
            throw new UserShippingAddressDAOException(e.getMessage());
        }
    }


}

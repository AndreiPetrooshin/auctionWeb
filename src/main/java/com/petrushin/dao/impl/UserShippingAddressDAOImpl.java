package com.petrushin.dao.impl;

import com.petrushin.builder.Creator;
import com.petrushin.dao.AbstractDAO;
import com.petrushin.domain.UserShippingAddress;
import com.petrushin.exceptions.EntityDAOException;

import java.util.List;

public class UserShippingAddressDAOImpl extends AbstractDAO<UserShippingAddress> {


    public UserShippingAddressDAOImpl(
            Creator<UserShippingAddress> creator) {
        super(creator);
    }

    public UserShippingAddress findById(Long id)
            throws EntityDAOException {
        try {
            return findById(id, UserShippingAddress.GET_BY_ID);
        } catch (EntityDAOException e) {
            throw new EntityDAOException(
                    "FindByID ShippingAddress Error" + e.getMessage(), e);
        }
    }

    public List<UserShippingAddress> getAll()
            throws EntityDAOException {
        try {
            return getAll(UserShippingAddress.GET_ALL);
        } catch (EntityDAOException e) {
            throw new EntityDAOException(
                    "GetAll ShippingAddress Error" + e.getMessage(), e);
        }
    }

    public boolean save(UserShippingAddress address)
            throws EntityDAOException {
        try {
            return save(address, UserShippingAddress.ADD_ADDRESS);
        } catch (EntityDAOException e) {
            throw new EntityDAOException(
                    "Save ShippingAddress Error" + e.getMessage(), e);
        }
    }

    public boolean delete(Long id)
            throws EntityDAOException {
        try {
            return delete(id, UserShippingAddress.DELETE_BY_ID);
        } catch (EntityDAOException e) {
            throw new EntityDAOException(
                    "Delete ShippingAddress Error" + e.getMessage(), e);
        }
    }

    public boolean update(UserShippingAddress address)
            throws EntityDAOException {
        try {
            return update(address, UserShippingAddress.UPDATE_ADDRESS);
        } catch (EntityDAOException e) {
            throw new EntityDAOException(
                    "Update ShippingAddress Error" + e.getMessage(), e);
        }
    }


}

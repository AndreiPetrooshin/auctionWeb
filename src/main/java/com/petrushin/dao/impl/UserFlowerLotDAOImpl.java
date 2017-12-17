package com.petrushin.dao.impl;

import com.petrushin.builder.Creator;
import com.petrushin.dao.AbstractDAO;
import com.petrushin.domain.UserFlowerLot;
import com.petrushin.exceptions.EntityDAOException;

import java.util.List;

public class UserFlowerLotDAOImpl extends AbstractDAO<UserFlowerLot> {


    public UserFlowerLotDAOImpl(Creator<UserFlowerLot> creator) {
        super(creator);
    }

    public UserFlowerLot findById(Long id)
            throws EntityDAOException {
        try {
            return findById(id, UserFlowerLot.GET_BY_ID);
        } catch (EntityDAOException e) {
            throw new EntityDAOException(
                    "FindById FlowerLot Error. " + e.getMessage(), e);
        }
    }

    public List<UserFlowerLot> getAll()
            throws EntityDAOException {
        try {
            return getAll(UserFlowerLot.GET_ALL);
        } catch (EntityDAOException e) {
            throw new EntityDAOException(
                    "GetAll FlowerLot Error" + e.getMessage(), e);
        }
    }

    public boolean save(UserFlowerLot lot)
            throws EntityDAOException {
        try {
            return save(lot, UserFlowerLot.ADD_LOT);
        } catch (EntityDAOException e) {
            throw new EntityDAOException(
                    "FlowerLot Save Error" + e.getMessage(), e);
        }
    }

    public boolean delete(Long id)
            throws EntityDAOException {
        try {
            return delete(id, UserFlowerLot.DELETE_BY_ID);
        } catch (EntityDAOException e) {
            throw new EntityDAOException(
                    "Delete FlowerLot Error. " + e.getMessage(), e);
        }
    }

    public boolean update(UserFlowerLot lot)
            throws EntityDAOException {
        try {
            return update(lot, UserFlowerLot.UPDATE_FLOWER_LOT);
        } catch (EntityDAOException e) {
            throw new EntityDAOException(
                    "Update FlowerLot Error. " + e.getMessage(), e);
        }
    }


}

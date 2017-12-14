package com.petrushin.dao.impl;

import com.petrushin.builder.AbstractBuilder;
import com.petrushin.dao.AbstractDAO;
import com.petrushin.dao.exception.AbstractDAOException;
import com.petrushin.dao.exception.UserFlowerLotDAOException;
import com.petrushin.domain.UserFlowerLot;

import java.util.List;

public class UserFlowerLotDAOImpl extends AbstractDAO<UserFlowerLot> {


    public UserFlowerLotDAOImpl(AbstractBuilder<UserFlowerLot> builder) {
        super(builder);
    }

    public UserFlowerLot findById(int id)
            throws UserFlowerLotDAOException {
        try {
            return findById(id, UserFlowerLot.GET_BY_ID);
        } catch (AbstractDAOException e) {
            throw new UserFlowerLotDAOException(e.getMessage());
        }
    }

    public List<UserFlowerLot> getAll()
            throws UserFlowerLotDAOException {
        try {
            return getAll(UserFlowerLot.GET_ALL);
        } catch (AbstractDAOException e) {
            throw new UserFlowerLotDAOException(e.getMessage());
        }
    }

    public boolean add(UserFlowerLot lot)
            throws UserFlowerLotDAOException {
        try {
            return add(lot, UserFlowerLot.ADD_LOT);
        } catch (AbstractDAOException e) {
            throw new UserFlowerLotDAOException(e.getMessage());
        }
    }

    public boolean delete(int id)
            throws UserFlowerLotDAOException {
        try {
            return delete(id, UserFlowerLot.DELETE_BY_ID);
        } catch (AbstractDAOException e) {
            throw new UserFlowerLotDAOException(e.getMessage());
        }
    }

    public boolean update(UserFlowerLot lot)
            throws UserFlowerLotDAOException {
        try {
            return update(lot, UserFlowerLot.UPDATE_FLOWER_LOT);
        } catch (AbstractDAOException e) {
            throw new UserFlowerLotDAOException(e.getMessage());
        }
    }




}

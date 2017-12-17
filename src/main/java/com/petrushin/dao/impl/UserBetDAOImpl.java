package com.petrushin.dao.impl;

import com.petrushin.builder.Creator;
import com.petrushin.dao.AbstractDAO;
import com.petrushin.domain.UserBet;
import com.petrushin.exceptions.EntityDAOException;

import java.util.List;

public class UserBetDAOImpl extends AbstractDAO<UserBet> {

    public UserBetDAOImpl(Creator<UserBet> creator) {
        super(creator);
    }

    public UserBet findById(Long id)
            throws EntityDAOException {
        try {
            return findById(id, UserBet.GET_BY_ID);
        } catch (EntityDAOException e) {
            throw new EntityDAOException(
                    "UserBet FindByID Error. " + e.getMessage(), e);
        }
    }

    public List<UserBet> getAll()
            throws EntityDAOException {
        try {
            return getAll(UserBet.GET_ALL);
        } catch (EntityDAOException e) {
            throw new EntityDAOException("GetAll UserBets Error" + e.getMessage(), e);
        }
    }


    public boolean save(UserBet userBet)
            throws EntityDAOException {
        try {
            return save(userBet, UserBet.ADD_BET);
        } catch (EntityDAOException e) {
            throw new EntityDAOException("UserBet Save Error. " + e.getMessage(), e);
        }
    }

    public boolean delete(Long id)
            throws EntityDAOException {
        try {
            return delete(id, UserBet.DELETE_BY_ID);
        } catch (EntityDAOException e) {
            throw new EntityDAOException(
                    "UserBet delete error" + e.getMessage(), e);
        }
    }


    @Override
    public boolean update(UserBet userBet)
            throws EntityDAOException {
        try {
            return update(userBet, UserBet.UPDATE_BET);
        } catch (EntityDAOException e) {
            throw new EntityDAOException("UserBet update error. " +
                    e.getMessage(), e);
        }
    }


}

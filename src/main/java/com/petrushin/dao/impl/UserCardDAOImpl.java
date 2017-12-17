package com.petrushin.dao.impl;

import com.petrushin.builder.Creator;
import com.petrushin.dao.AbstractDAO;
import com.petrushin.domain.UserCard;
import com.petrushin.exceptions.EntityDAOException;

import java.util.List;

public class UserCardDAOImpl extends AbstractDAO<UserCard> {

    public UserCardDAOImpl(Creator<UserCard> creator) {
        super(creator);
    }

    public UserCard findById(Long id)
            throws EntityDAOException {
        try {
            return findById(id, UserCard.GET_BY_ID);
        } catch (EntityDAOException e) {
            throw new EntityDAOException(
                    "FindByID UserCard Error " + e.getMessage(), e);
        }
    }

    public List<UserCard> getAll()
            throws EntityDAOException {
        try {
            return getAll(UserCard.GET_ALL);
        } catch (EntityDAOException e) {
            throw new EntityDAOException(
                    "GetAll UserCard Error" + e.getMessage(), e);
        }
    }

    public boolean save(UserCard card)
            throws EntityDAOException {
        try {
            return save(card, UserCard.ADD_CARD);
        } catch (EntityDAOException e) {
            throw new EntityDAOException(
                    "Save UserCard error. " + e.getMessage(), e);
        }
    }

    public boolean update(UserCard card)
            throws EntityDAOException {
        try {
            return update(card, UserCard.UPDATE_USER_CARD);
        } catch (EntityDAOException e) {
            throw new EntityDAOException(
                    "Update UserCard Error" + e.getMessage(), e);
        }
    }

    public boolean delete(Long id)
            throws EntityDAOException {
        try {
            return delete(id, UserCard.DELETE_USER_CARD);
        } catch (EntityDAOException e) {
            throw new EntityDAOException(
                    "Delete UserCard Error" + e.getMessage(), e);
        }
    }

}

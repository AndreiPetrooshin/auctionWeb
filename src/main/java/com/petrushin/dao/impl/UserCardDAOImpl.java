package com.petrushin.dao.impl;

import com.petrushin.builder.Builder;
import com.petrushin.dao.AbstractDAO;
import com.petrushin.dao.exception.AbstractDAOException;
import com.petrushin.dao.exception.UserCardDAOException;
import com.petrushin.domain.UserCard;

import java.util.List;

public class UserCardDAOImpl extends AbstractDAO<UserCard> {

    public UserCardDAOImpl(Builder<UserCard> builder) {
        super(builder);
    }

    public UserCard findById(Long id)
            throws UserCardDAOException {
        try {
            return findById(id, UserCard.GET_BY_ID);
        } catch (AbstractDAOException e) {
            throw new UserCardDAOException(e.getMessage());
        }
    }

    public List<UserCard> getAll()
            throws UserCardDAOException {
        try {
            return getAll(UserCard.GET_ALL);
        } catch (AbstractDAOException e) {
            throw new UserCardDAOException(e.getMessage());
        }
    }

    public boolean add(UserCard card)
            throws UserCardDAOException {
        try {
            return add(card, UserCard.ADD_CARD);
        } catch (AbstractDAOException e) {
            throw new UserCardDAOException(e.getMessage());
        }
    }

    public boolean update(UserCard card)
            throws UserCardDAOException {
        try {
            return update(card, UserCard.UPDATE_USER_CARD);
        } catch (AbstractDAOException e) {
            throw new UserCardDAOException(e.getMessage());
        }
    }

    public boolean delete(Long id)
            throws UserCardDAOException {
        try {
            return delete(id, UserCard.DELETE_USER_CARD);
        } catch (AbstractDAOException e) {
            throw new UserCardDAOException(e.getMessage());
        }
    }

}

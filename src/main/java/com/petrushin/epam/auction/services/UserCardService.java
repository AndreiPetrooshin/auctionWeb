package com.petrushin.epam.auction.services;

import com.petrushin.epam.auction.exceptions.EntityDAOException;
import com.petrushin.epam.auction.services.dao.impl.UserCardDao;
import com.petrushin.epam.auction.services.domain.UserCard;

import java.util.ArrayList;
import java.util.List;

/**
 * Service class which do the main business logic
 * with {@link UserCard} entity
 *
 * @author Andrei Petrushin
 * @version 1.0.0
 */
public class UserCardService implements Service<UserCard> {

    private UserCardDao userCardDao;

    public UserCardService(UserCardDao userCardDao) {
        this.userCardDao = userCardDao;
    }

    @Override
    public UserCard findById(Long id) throws EntityDAOException {
        return userCardDao.findById(id);
    }

    public List<UserCard> getByUserId(Long id) throws EntityDAOException {
        return userCardDao.getByUserId(id);
    }

    @Override
    public List<UserCard> getAll() throws EntityDAOException {
        return userCardDao.getAll();
    }

    @Override
    public boolean save(UserCard card) throws EntityDAOException {
        return userCardDao.save(card);
    }

    @Override
    public boolean delete(Long id) throws EntityDAOException {
        return userCardDao.delete(id);
    }

    @Override
    public boolean update(UserCard card) throws EntityDAOException {
        return userCardDao.update(card);
    }
}

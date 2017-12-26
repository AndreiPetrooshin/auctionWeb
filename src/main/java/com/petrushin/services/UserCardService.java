package com.petrushin.services;

import com.petrushin.model.dao.impl.UserCardDaoImpl;
import com.petrushin.model.domain.UserCard;
import com.petrushin.exceptions.EntityDAOException;

import java.util.ArrayList;
import java.util.List;

public class UserCardService implements Service<UserCard> {

    private UserCardDaoImpl userCardDao;

    public UserCardService(UserCardDaoImpl userCardDao) {
        this.userCardDao = userCardDao;
    }

    @Override
    public UserCard findById(Long id) throws EntityDAOException {
        return userCardDao.findById(id);
    }

    public List<UserCard> getByUserId(Long id) throws EntityDAOException {
        List<UserCard> cards = getAll();
        List<UserCard> result = new ArrayList<>();
        for(UserCard card: cards) {
            Long cardId = card.getUser().getId();
            if(id.equals(cardId)) {
                result.add(card);
            }
        }
        return result;
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

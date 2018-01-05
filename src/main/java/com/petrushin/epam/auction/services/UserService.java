package com.petrushin.epam.auction.services;

import com.petrushin.epam.auction.exceptions.EntityDAOException;
import com.petrushin.epam.auction.model.dao.impl.UserDaoImpl;
import com.petrushin.epam.auction.model.domain.User;

import java.util.List;

/**
 * Service class which do the main business logic
 * with {@link User} entity
 *
 * @author Andrei Petrushin
 * @version 1.0.0
 */
public class UserService implements Service<User> {

    private UserDaoImpl userDao;

    public UserService(UserDaoImpl userDao) {
        this.userDao = userDao;
    }

    public User getByLogin(String login) throws EntityDAOException {
        List<User> users = getAll();
        User user = null;
        for (User currUser : users) {
            if (login.equals(currUser.getLogin())) {
                user = currUser;
                break;
            }
        }
        return user;
    }


    public boolean ifLoginExist(String login) throws EntityDAOException {
        List<User> users = getAll();
        for (User currUser : users) {
            String currLogin = currUser.getLogin();
            if (login.equals(currLogin)) {
                return true;
            }
        }
        return false;
    }


    public boolean ifEmailExist(String email)
            throws EntityDAOException {
        List<User> users = getAll();
        for (User currUser : users) {
            String currEmail = currUser.getEmail();
            if (email.equals(currEmail)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public User findById(Long id) throws EntityDAOException {
        return userDao.findById(id);
    }

    @Override
    public List<User> getAll() throws EntityDAOException {
        return userDao.getAll();
    }

    @Override
    public boolean save(User user) throws EntityDAOException {
        return userDao.save(user);
    }

    @Override
    public boolean delete(Long id) throws EntityDAOException {
        return userDao.delete(id);
    }

    @Override
    public boolean update(User user) throws EntityDAOException {
        return userDao.update(user);
    }
}

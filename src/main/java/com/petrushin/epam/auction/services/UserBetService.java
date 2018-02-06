package com.petrushin.epam.auction.services;

import com.petrushin.epam.auction.dao.impl.UserBetDao;
import com.petrushin.epam.auction.domain.UserBet;
import com.petrushin.epam.auction.exceptions.EntityDAOException;

import java.util.List;

/**
 * Service class which do the main business logic
 * with {@link UserBet} entity
 *
 * @author Andrei Petrushin
 * @version 1.0.0
 */
public class UserBetService implements Service<UserBet> {

    private static final int BIGGEST_BET = 0;
    private UserBetDao userBetDao;

    public UserBetService(UserBetDao userBetDao) {
        this.userBetDao = userBetDao;
    }

    public List<UserBet> getByLotId(Long id) throws EntityDAOException {
        return userBetDao.getByLotId(id);
    }

    public UserBet getLastBetToLot(Long lotId) throws EntityDAOException {
        List<UserBet> bets = getByLotId(lotId);
        sortByBet(bets);
        UserBet userBet = null;
        if (!bets.isEmpty()) {
            userBet = bets.get(BIGGEST_BET);
        }
        return userBet;
    }

    @Override
    public UserBet findById(Long id) throws EntityDAOException {
        return userBetDao.findById(id);
    }

    @Override
    public List<UserBet> getAll() throws EntityDAOException {
        return userBetDao.getAll();
    }

    @Override
    public boolean save(UserBet userBet) throws EntityDAOException {
        return userBetDao.save(userBet);
    }

    @Override
    public boolean delete(Long id) throws EntityDAOException {
        return userBetDao.delete(id);
    }

    @Override
    public boolean update(UserBet userBet) throws EntityDAOException {
        return userBetDao.update(userBet);
    }

    private List<UserBet> sortByBet(List<UserBet> bets) {
        bets.sort((o1, o2) -> {
            double val1 = o1.getBet().doubleValue();
            double val2 = o2.getBet().doubleValue();
            return (int) (val2 - val1);
        });
        return bets;
    }
}

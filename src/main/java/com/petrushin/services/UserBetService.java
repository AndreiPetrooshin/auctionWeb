package com.petrushin.services;

import com.petrushin.model.dao.impl.UserBetDaoImpl;
import com.petrushin.model.domain.FlowerLot;
import com.petrushin.model.domain.User;
import com.petrushin.model.domain.UserBet;
import com.petrushin.exceptions.EntityDAOException;

import java.util.*;

public class UserBetService implements Service<UserBet> {

    private UserBetDaoImpl userBetDao;

    public UserBetService(UserBetDaoImpl userBetDao) {
        this.userBetDao = userBetDao;
    }

    public List<UserBet> getByLotId(Long id) throws EntityDAOException {
        List<UserBet> list = getAll();
        List<UserBet> result = new ArrayList<>();
        for(UserBet userBet: list) {
            FlowerLot lot = userBet.getLot();
            Long userId = lot.getId();
            if(Objects.equals(id, userId)){
                result.add(userBet);
            }
        }
        sortByBet(result);
        return result;
    }

    public List<UserBet> getByUserId(Long id) throws EntityDAOException {
        List<UserBet> list = getAll();
        List<UserBet> result = new ArrayList<>();
        for(UserBet userBet: list) {
            User user = userBet.getUser();
            Long userId = user.getId();
            if(Objects.equals(id, userId)){
                result.add(userBet);
            }
        }
        sortByBet(result);
        return result;
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

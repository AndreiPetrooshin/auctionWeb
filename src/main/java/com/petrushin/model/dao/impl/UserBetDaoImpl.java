package com.petrushin.model.dao.impl;

import com.petrushin.model.creator.Creator;
import com.petrushin.model.dao.AbstractDao;
import com.petrushin.model.domain.FlowerLot;
import com.petrushin.model.domain.User;
import com.petrushin.model.domain.UserBet;
import com.petrushin.exceptions.CreatorException;
import com.petrushin.exceptions.EntityDAOException;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class UserBetDaoImpl extends AbstractDao<UserBet> {

    public UserBetDaoImpl(Creator<UserBet> creator) {
        super(creator);
    }

    public UserBet findById(Long id) throws EntityDAOException {
        return getByPK(id, UserBet.GET_BY_ID);
    }

    public List<UserBet> getAll() throws EntityDAOException {
        return getAll(UserBet.GET_ALL);
    }


    public boolean save(UserBet userBet) throws EntityDAOException {
        return save(userBet, UserBet.ADD_BET);
    }

    public boolean delete(Long id) throws EntityDAOException {
        return delete(id, UserBet.DELETE_BY_ID);
    }


    @Override
    public boolean update(UserBet userBet) throws EntityDAOException {
        return update(userBet, UserBet.UPDATE_BET);
    }



    @Override
    public void prepareStatement(UserBet userBet, PreparedStatement statement)
            throws CreatorException {
        try {
            FlowerLot lot = userBet.getLot();
            Long lotId = lot.getId();
            statement.setLong(1, lotId);

            User user = userBet.getUser();
            Long userId = user.getId();
            statement.setLong(2, userId);

            BigDecimal bet = userBet.getBet();
            statement.setBigDecimal(3, bet);

            Long id = userBet.getId();
            statement.setLong(4, id);

        } catch (SQLException e) {
            throw new CreatorException(
                    "Error with init bet statement" + e.getMessage(), e);
        }
    }


}

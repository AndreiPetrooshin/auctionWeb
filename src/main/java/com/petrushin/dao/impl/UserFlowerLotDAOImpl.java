package com.petrushin.dao.impl;

import com.petrushin.creator.Creator;
import com.petrushin.dao.AbstractDAO;
import com.petrushin.domain.User;
import com.petrushin.domain.UserFlowerLot;
import com.petrushin.exceptions.CreatorException;
import com.petrushin.exceptions.EntityDAOException;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class UserFlowerLotDAOImpl extends AbstractDAO<UserFlowerLot> {


    public UserFlowerLotDAOImpl(Creator<UserFlowerLot> creator) {
        super(creator);
    }

    public UserFlowerLot findById(Long id) throws EntityDAOException {
        return getByPK(id, UserFlowerLot.GET_BY_ID);
    }

    public List<UserFlowerLot> getAll() throws EntityDAOException {
        return getAll(UserFlowerLot.GET_ALL);
    }

    public boolean save(UserFlowerLot lot) throws EntityDAOException {
        return save(lot, UserFlowerLot.ADD_LOT);
    }

    public boolean delete(Long id) throws EntityDAOException {
        return delete(id, UserFlowerLot.DELETE_BY_ID);
    }

    public boolean update(UserFlowerLot lot) throws EntityDAOException {
        return update(lot, UserFlowerLot.UPDATE_FLOWER_LOT);
    }

    @Override
    public void prepareStatement(UserFlowerLot lot, PreparedStatement statement)
            throws CreatorException {
        try {
            User user = lot.getUser();
            Long userId = user.getId();
            statement.setLong(1, userId);

            String type = lot.getType();
            statement.setString(2, type);

            String name = lot.getName();
            statement.setString(3, name);

            String description = lot.getDescription();
            statement.setString(4, description);

            BigDecimal startPrice = lot.getStartPrice();
            statement.setBigDecimal(5, startPrice);

            Long lotId = lot.getId();
            statement.setLong(6, lotId);
        } catch (SQLException e) {
            throw new CreatorException(
                    "Error with init  UserFlowerLot statement" + e.getMessage(), e);
        }
    }
}

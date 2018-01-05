package com.petrushin.epam.auction.model.dao.impl;

import com.petrushin.epam.auction.exceptions.CreatorException;
import com.petrushin.epam.auction.exceptions.EntityDAOException;
import com.petrushin.epam.auction.model.creator.Creator;
import com.petrushin.epam.auction.model.dao.AbstractDao;
import com.petrushin.epam.auction.model.domain.FlowerLot;
import com.petrushin.epam.auction.model.domain.User;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

/**
 * Dao class witch extends {@link AbstractDao} and implements methods for
 * {@link FlowerLot} entity
 *
 * @author Andrei Petrushin
 * @version 1.0.0
 */
public class FlowerLotDaoImpl extends AbstractDao<FlowerLot> {


    public FlowerLotDaoImpl(Creator<FlowerLot> creator) {
        super(creator);
    }

    public FlowerLot findById(Long id) throws EntityDAOException {
        return getByPK(id, FlowerLot.GET_BY_ID);
    }

    public List<FlowerLot> getAll() throws EntityDAOException {
        return getAll(FlowerLot.GET_ALL);
    }

    public boolean save(FlowerLot lot) throws EntityDAOException {
        return save(lot, FlowerLot.ADD_LOT);
    }

    public boolean delete(Long id) throws EntityDAOException {
        return delete(id, FlowerLot.DELETE_BY_ID);
    }

    public boolean update(FlowerLot lot) throws EntityDAOException {
        return update(lot, FlowerLot.UPDATE_FLOWER_LOT);
    }

    public void prepareStatementForUpdate(FlowerLot lot, PreparedStatement statement)
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

            String state = lot.getState();
            statement.setString(6, state);

            Long lotId = lot.getId();
            statement.setLong(7, lotId);
        } catch (SQLException e) {
            throw new CreatorException(
                    "Error with init  FlowerLot statement" + e.getMessage(), e);
        }
    }

    @Override
    protected void prepareStatementForInsert(FlowerLot lot, PreparedStatement statement)
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
                    "Error with init  FlowerLot statement" + e.getMessage(), e);
        }
    }
}

package com.petrushin.epam.auction.services;


import com.petrushin.epam.auction.exceptions.EntityDAOException;
import com.petrushin.epam.auction.services.dao.impl.FlowerLotDao;
import com.petrushin.epam.auction.services.domain.FlowerLot;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Service class which do the main business logic
 * with {@link FlowerLot} entity
 *
 * @author Andrei Petrushin
 * @version 1.0.0
 */
public class FlowerLotService implements Service<FlowerLot> {

    private FlowerLotDao flowerLotDao;

    public FlowerLotService(FlowerLotDao flowerLotDao) {
        this.flowerLotDao = flowerLotDao;
    }


    public List<FlowerLot> getByState(String state) throws EntityDAOException {
        return flowerLotDao.getByState(state);
    }


    public List<FlowerLot> getByTypeAndState(String type, String state) throws EntityDAOException {
        return flowerLotDao.getByTypeAndState(type, state);
    }

    public List<FlowerLot> getByUserId(Long id) throws EntityDAOException {
        return flowerLotDao.getByUserId(id);
    }

    @Override
    public FlowerLot findById(Long id) throws EntityDAOException {
        return flowerLotDao.findById(id);
    }

    @Override
    public List<FlowerLot> getAll() throws EntityDAOException {
        return flowerLotDao.getAll();
    }

    @Override
    public boolean save(FlowerLot flowerLot) throws EntityDAOException {
        return flowerLotDao.save(flowerLot);
    }

    @Override
    public boolean delete(Long id) throws EntityDAOException {
        return flowerLotDao.delete(id);
    }

    @Override
    public boolean update(FlowerLot flowerLot) throws EntityDAOException {
        return flowerLotDao.update(flowerLot);
    }
}

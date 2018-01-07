package com.petrushin.epam.auction.services;


import com.petrushin.epam.auction.exceptions.ServiceException;
import com.petrushin.epam.auction.model.dao.impl.FlowerLotDaoImpl;
import com.petrushin.epam.auction.model.domain.FlowerLot;

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

    private FlowerLotDaoImpl flowerLotDao;

    public FlowerLotService(FlowerLotDaoImpl flowerLotDao) {
        this.flowerLotDao = flowerLotDao;
    }


    public List<FlowerLot> getByState(String state) throws ServiceException {
        List<FlowerLot> lots = getAll();
        List<FlowerLot> result = new ArrayList<>();
        for (FlowerLot lot : lots) {
            String lotState = lot.getState();
            if (state.equalsIgnoreCase(lotState)) {
                result.add(lot);
            }
        }
        return result;
    }

    public List<FlowerLot> getByType(String type) throws ServiceException {
        List<FlowerLot> lots = getAll();
        List<FlowerLot> result = new ArrayList<>();
        for (FlowerLot lot : lots) {
            String lotType = lot.getType();
            if (type.equalsIgnoreCase(lotType)) {
                result.add(lot);
            }
        }
        return result;
    }

    public List<FlowerLot> getByTypeAndState(String type, String state) throws ServiceException {
        List<FlowerLot> lots = getAll();
        List<FlowerLot> result = new ArrayList<>();
        for (FlowerLot lot : lots) {
            String lotType = lot.getType();
            String lotState = lot.getState();
            if (type.equalsIgnoreCase(lotType)
                    && state.equalsIgnoreCase(lotState)) {
                result.add(lot);
            }
        }
        return result;
    }

    public List<FlowerLot> getByUserId(Long id) throws ServiceException {
        List<FlowerLot> lots = getAll();
        List<FlowerLot> result = new ArrayList<>();
        for (FlowerLot lot : lots) {
            Long userId = lot.getUser().getId();
            if (Objects.equals(userId, id)) {
                result.add(lot);
            }
        }
        return result;
    }

    @Override
    public FlowerLot findById(Long id) throws ServiceException {
        return flowerLotDao.findById(id);
    }

    @Override
    public List<FlowerLot> getAll() throws ServiceException {
        return flowerLotDao.getAll();
    }

    @Override
    public boolean save(FlowerLot flowerLot) throws ServiceException {
        return flowerLotDao.save(flowerLot);
    }

    @Override
    public boolean delete(Long id) throws ServiceException {
        return flowerLotDao.delete(id);
    }

    @Override
    public boolean update(FlowerLot flowerLot) throws ServiceException {
        return flowerLotDao.update(flowerLot);
    }
}

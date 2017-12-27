package com.petrushin.services;


import com.petrushin.model.dao.impl.FlowerLotDaoImpl;
import com.petrushin.model.domain.FlowerLot;
import com.petrushin.exceptions.EntityDAOException;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class FlowerLotService implements Service<FlowerLot> {

    private FlowerLotDaoImpl flowerLotDao;

    public FlowerLotService(FlowerLotDaoImpl flowerLotDao) {
        this.flowerLotDao = flowerLotDao;
    }


    public List<FlowerLot> getByState(String state) throws EntityDAOException {
        List<FlowerLot> lots = getAll();
        List<FlowerLot> result = new ArrayList<>();
        for (FlowerLot lot : lots){
            String lotState = lot.getState();
            if(state.equalsIgnoreCase(lotState)){
                result.add(lot);
            }
        }
        return result;
    }

    public List<FlowerLot> getByType(String type) throws EntityDAOException {
        List<FlowerLot> lots = getAll();
        List<FlowerLot> result = new ArrayList<>();
        for(FlowerLot lot: lots){
            String lotType = lot.getType();
            if(type.equalsIgnoreCase(lotType)){
                result.add(lot);
            }
        }
        return result;
    }

    public List<FlowerLot> getByTypeAndState(String type, String state) throws EntityDAOException {
        List<FlowerLot> lots = getAll();
        List<FlowerLot> result = new ArrayList<>();
        for(FlowerLot lot: lots){
            String lotType = lot.getType();
            String lotState = lot.getState();
            if(type.equalsIgnoreCase(lotType)
                    && state.equalsIgnoreCase(lotState)){
                result.add(lot);
            }
        }
        return result;
    }

    public List<FlowerLot> getByUserId(Long id) throws EntityDAOException {
        List<FlowerLot> lots = getAll();
        List<FlowerLot> result = new ArrayList<>();
        for(FlowerLot lot: lots) {
            Long userId = lot.getUser().getId();
            if(Objects.equals(userId, id)) {
                result.add(lot);
            }
        }
        return result;
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
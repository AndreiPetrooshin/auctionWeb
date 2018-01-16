package com.petrushin.epam.auction.services.factory;

import com.petrushin.epam.auction.services.*;
import com.petrushin.epam.auction.services.dao.impl.*;

import java.util.HashMap;
import java.util.Map;

/**
 * Factory class witch returns needed Service class by Class
 *
 * @author Andrei Petrushin
 * @version 1.0.0
 */
public class ServiceFactory {

    private DaoFactory daoFactory;
    private Map<Class, Service> map;

    public ServiceFactory(DaoFactory daoFactory) {
        this.daoFactory = daoFactory;
        this.map = createMap();
    }


    public Service getService(Class<? extends Service> clazz) {
        return map.get(clazz);
    }

    private Map<Class, Service> createMap() {
        Map<Class, Service> map = new HashMap<>();

        FlowerLotDao lotDao = (FlowerLotDao) daoFactory.getDao(FlowerLotDao.class);
        map.put(FlowerLotService.class, new FlowerLotService(lotDao));

        UserAddressDao addressesDao = (UserAddressDao) daoFactory.getDao(UserAddressDao.class);
        map.put(UserAddressesService.class, new UserAddressesService(addressesDao));

        UserBetDao betDao = (UserBetDao) daoFactory.getDao(UserBetDao.class);
        map.put(UserBetService.class, new UserBetService(betDao));

        UserCardDao cardDao = (UserCardDao) daoFactory.getDao(UserCardDao.class);
        map.put(UserCardService.class, new UserCardService(cardDao));

        UserRoleDao roleDao = (UserRoleDao) daoFactory.getDao(UserRoleDao.class);
        map.put(UserRoleService.class, new UserRoleService(roleDao));

        UserDao userDao = (UserDao) daoFactory.getDao(UserDao.class);
        map.put(UserService.class, new UserService(userDao));

        PaymentDao paymentDao = (PaymentDao) daoFactory.getDao(PaymentDao.class);
        map.put(PaymentService.class, new PaymentService(paymentDao));

        return map;
    }


}

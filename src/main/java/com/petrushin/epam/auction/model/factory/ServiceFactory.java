package com.petrushin.epam.auction.model.factory;

import com.petrushin.epam.auction.model.dao.impl.*;
import com.petrushin.epam.auction.services.*;

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

        FlowerLotDaoImpl lotDao = (FlowerLotDaoImpl) daoFactory.getDao(FlowerLotDaoImpl.class);
        map.put(FlowerLotService.class, new FlowerLotService(lotDao));

        UserAddressDaoImpl addressesDao = (UserAddressDaoImpl) daoFactory.getDao(UserAddressDaoImpl.class);
        map.put(UserAddressesService.class, new UserAddressesService(addressesDao));

        UserBetDaoImpl betDao = (UserBetDaoImpl) daoFactory.getDao(UserBetDaoImpl.class);
        map.put(UserBetService.class, new UserBetService(betDao));

        UserCardDaoImpl cardDao = (UserCardDaoImpl) daoFactory.getDao(UserCardDaoImpl.class);
        map.put(UserCardService.class, new UserCardService(cardDao));

        UserRoleDaoImpl roleDao = (UserRoleDaoImpl) daoFactory.getDao(UserRoleDaoImpl.class);
        map.put(UserRoleService.class, new UserRoleService(roleDao));

        UserDaoImpl userDao = (UserDaoImpl) daoFactory.getDao(UserDaoImpl.class);
        map.put(UserService.class, new UserService(userDao));

        PaymentDaoImpl paymentDao = (PaymentDaoImpl) daoFactory.getDao(PaymentDaoImpl.class);
        map.put(PaymentService.class, new PaymentService(paymentDao));

        return map;
    }


}

package com.petrushin.epam.auction.factory;

import com.petrushin.epam.auction.dao.GenericDao;
import com.petrushin.epam.auction.dao.creator.Creator;
import com.petrushin.epam.auction.dao.creator.impl.*;
import com.petrushin.epam.auction.dao.impl.*;

import java.util.HashMap;
import java.util.Map;

/**
 * Factory class witch returns needed Dao class by Class
 *
 * @author Andrei Petrushin
 * @version 1.0.0
 */
public class DaoFactory {

    private CreatorFactory creatorFactory;
    private Map<Class, GenericDao> map;

    public DaoFactory(CreatorFactory creatorFactory) {
        this.creatorFactory = creatorFactory;
        this.map = createMap();
    }

    public GenericDao getDao(Class<? extends GenericDao> clazz) {
        return map.get(clazz);
    }

    private Map<Class, GenericDao> createMap() {
        Map<Class, GenericDao> map = new HashMap<>();

        Creator lotCreator = creatorFactory.getCreator(FlowerLotCreator.class);
        map.put(FlowerLotDao.class, new FlowerLotDao(lotCreator));

        Creator addressesCreator = creatorFactory.getCreator(UserAddressCreator.class);
        map.put(UserAddressDao.class, new UserAddressDao(addressesCreator));

        Creator betCreator = creatorFactory.getCreator(UserBetCreator.class);
        map.put(UserBetDao.class, new UserBetDao(betCreator));

        Creator cardCreator = creatorFactory.getCreator(UserCardCreator.class);
        map.put(UserCardDao.class, new UserCardDao(cardCreator));

        Creator userCreator = creatorFactory.getCreator(UserCreator.class);
        map.put(UserDao.class, new UserDao(userCreator));

        Creator roleCreator = creatorFactory.getCreator(UserRoleCreator.class);
        map.put(UserRoleDao.class, new UserRoleDao(roleCreator));

        Creator paymentCreator = creatorFactory.getCreator(PaymentCreator.class);
        map.put(PaymentDao.class, new PaymentDao(paymentCreator));

        return map;
    }


}

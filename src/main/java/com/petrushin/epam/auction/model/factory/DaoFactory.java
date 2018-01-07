package com.petrushin.epam.auction.model.factory;

import com.petrushin.epam.auction.model.creator.Creator;
import com.petrushin.epam.auction.model.creator.impl.*;
import com.petrushin.epam.auction.model.dao.GenericDao;
import com.petrushin.epam.auction.model.dao.impl.*;

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
        map.put(FlowerLotDaoImpl.class, new FlowerLotDaoImpl(lotCreator));

        Creator addressesCreator = creatorFactory.getCreator(UserAddressCreator.class);
        map.put(UserAddressDaoImpl.class, new UserAddressDaoImpl(addressesCreator));

        Creator betCreator = creatorFactory.getCreator(UserBetCreator.class);
        map.put(UserBetDaoImpl.class, new UserBetDaoImpl(betCreator));

        Creator cardCreator = creatorFactory.getCreator(UserCardCreator.class);
        map.put(UserCardDaoImpl.class, new UserCardDaoImpl(cardCreator));

        Creator userCreator = creatorFactory.getCreator(UserCreator.class);
        map.put(UserDaoImpl.class, new UserDaoImpl(userCreator));

        Creator roleCreator = creatorFactory.getCreator(UserRoleCreator.class);
        map.put(UserRoleDaoImpl.class, new UserRoleDaoImpl(roleCreator));

        Creator paymentCreator = creatorFactory.getCreator(PaymentCreator.class);
        map.put(PaymentDaoImpl.class, new PaymentDaoImpl(paymentCreator));

        return map;
    }


}

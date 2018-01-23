package com.petrushin.epam.auction.factory;

import com.petrushin.epam.auction.dao.creator.Creator;
import com.petrushin.epam.auction.dao.creator.impl.*;

import java.util.HashMap;
import java.util.Map;

/**
 * Factory class witch returns needed Creator class by Class
 *
 * @author Andrei Petrushin
 * @version 1.0.0
 */
public class CreatorFactory {

    private Map<Class, Creator> map = createMap();


    public Creator getCreator(Class<? extends Creator> clazz) {
        return map.get(clazz);
    }

    private Map<Class, Creator> createMap() {
        Map<Class, Creator> map = new HashMap<>();

        map.put(FlowerLotCreator.class, new FlowerLotCreator(new UserCreator()));

        map.put(UserAddressCreator.class, new UserAddressCreator(new UserCreator()));

        FlowerLotCreator lotCreator = new FlowerLotCreator(new UserCreator());
        map.put(UserBetCreator.class, new UserBetCreator(new UserCreator(), lotCreator));

        map.put(UserCardCreator.class, new UserCardCreator(new UserCreator()));

        map.put(UserRoleCreator.class, new UserRoleCreator());

        map.put(UserCreator.class, new UserCreator());

        map.put(PaymentCreator.class, new PaymentCreator(new UserCreator(), lotCreator));

        return map;
    }

}

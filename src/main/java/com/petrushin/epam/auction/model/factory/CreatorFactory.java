package com.petrushin.epam.auction.model.factory;

import com.petrushin.epam.auction.model.creator.Creator;
import com.petrushin.epam.auction.model.creator.impl.*;

import java.util.HashMap;
import java.util.Map;

public class CreatorFactory {

    private Map<Class, Creator> map = createMap();


    public Creator getCreator(Class<? extends Creator> clazz) {
        return map.get(clazz);
    }

    private Map<Class, Creator> createMap() {
        Map<Class, Creator> map = new HashMap<>();

        map.put(FlowerLotCreator.class, new FlowerLotCreator(new UserCreator()));

        map.put(UserAddressesCreator.class, new UserAddressesCreator(new UserCreator()));

        FlowerLotCreator lotCreator = new FlowerLotCreator(new UserCreator());
        map.put(UserBetCreator.class, new UserBetCreator(new UserCreator(), lotCreator));

        map.put(UserCardCreator.class, new UserCardCreator(new UserCreator()));

        map.put(UserRoleCreator.class, new UserRoleCreator());

        map.put(UserCreator.class, new UserCreator());

        return map;
    }

}

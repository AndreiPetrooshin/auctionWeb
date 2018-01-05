package com.petrushin.epam.auction.model.factory;

import com.petrushin.epam.auction.model.command.*;
import com.petrushin.epam.auction.services.*;

import java.util.HashMap;
import java.util.Map;

/**
 * Factory class witch returns needed command by String
 *
 * @author Andrei Petrushin
 * @version 1.0.0
 */
public final class CommandFactory {

    private static final String COMMAND_DELETE = "delete";
    private static final String COMMAND_LOGIN = "login";
    private static final String COMMAND_REGISTRATION = "registration";
    private static final String COMMAND_LOGOUT = "logout";
    private static final String COMMAND_SHOW_LOTS = "showLots";
    private static final String COMMAND_SHOW_LOT = "showLot";
    private static final String COMMAND_MAKE_BET = "makeBet";
    private static final String COMMAND_PROFILE = "profile";
    private static final String COMMAND_UPDATE_LOT = "updateLot";
    private static final String COMMAND_SAVE = "save";
    private static final String COMMAND_LOCALE = "locale";
    private static final String COMMAND_ADMIN = "admin";

    private ServiceFactory serviceFactory;
    private Map<String, Command> map;

    public CommandFactory(ServiceFactory serviceFactory) {
        this.serviceFactory = serviceFactory;
        this.map = createMap();
    }

    public Command getCommand(String command) {
        return map.get(command);
    }

    private Map<String, Command> createMap() {
        Map<String, Command> map = new HashMap<>();

        FlowerLotService lotService = (FlowerLotService) serviceFactory.getService(FlowerLotService.class);
        UserAddressesService addressesService = (UserAddressesService) serviceFactory.getService(UserAddressesService.class);
        UserBetService betService = (UserBetService) serviceFactory.getService(UserBetService.class);
        UserCardService cardService = (UserCardService) serviceFactory.getService(UserCardService.class);
        UserRoleService roleService = (UserRoleService) serviceFactory.getService(UserRoleService.class);
        UserService userService = (UserService) serviceFactory.getService(UserService.class);


        map.put(COMMAND_LOGIN, new LoginCommand(userService));

        map.put(COMMAND_REGISTRATION, new RegistrationCommand(userService));

        map.put(COMMAND_LOGOUT, new LogoutCommand());

        map.put(COMMAND_SHOW_LOTS, new ShowLotsCommand(lotService));

        map.put(COMMAND_SHOW_LOT, new ShowLotCommand(lotService, betService));

        map.put(COMMAND_MAKE_BET, new MakeBetCommand(betService, lotService));

        map.put(COMMAND_PROFILE, new ProfileCommand(cardService, lotService, addressesService));

        map.put(COMMAND_UPDATE_LOT, new UpdateLotCommand(lotService, userService));

        map.put(COMMAND_DELETE, new DeleteCommand(lotService, cardService));

        map.put(COMMAND_SAVE, new SaveCommand(lotService, cardService, addressesService));

        map.put(COMMAND_LOCALE, new LocaleCommand());

        map.put(COMMAND_ADMIN, new AdminCommand(lotService));

        return map;
    }

}

package com.petrushin.model.command;

import com.petrushin.model.creator.impl.*;
import com.petrushin.model.dao.impl.*;
import com.petrushin.services.*;

public final class CommandFactory {

    private static final String COMMAND_LOGIN = "login";
    private static final String COMMAND_REGISTRATION = "registration";
    private static final String COMMAND_LOGOUT = "logout";
    private static final String COMMAND_SHOW_LOTS = "showLots";
    private static final String COMMAND_SHOW_LOT = "showLot";
    private static final String COMMAND_MAKE_BET = "makeBet";
    private static final String COMMAND_PROFILE = "profile";

    private CommandFactory() {

    }

    public static Command getCommand(String command) {

        if (COMMAND_LOGIN.equalsIgnoreCase(command)) {
            UserCreator creator = new UserCreator();
            UserDaoImpl userDAO = new UserDaoImpl(creator);
            UserService userService = new UserService(userDAO);
            return new LoginCommand(userService);
        }

        if (COMMAND_REGISTRATION.equalsIgnoreCase(command)) {
            UserCreator creator = new UserCreator();
            UserDaoImpl userDAO = new UserDaoImpl(creator);
            UserService userService = new UserService(userDAO);
            return new RegistrationCommand(userService);
        }

        if (COMMAND_LOGOUT.equalsIgnoreCase(command)) {
            return new LogoutCommand();
        }
        if (COMMAND_SHOW_LOTS.equalsIgnoreCase(command)) {
            UserCreator userCreator = new UserCreator();
            FlowerLotCreator creator = new FlowerLotCreator(userCreator);
            FlowerLotDaoImpl userFlowerLotDAO = new FlowerLotDaoImpl(creator);
            FlowerLotService flowerLotService = new FlowerLotService(userFlowerLotDAO);
            return new ShowLotsCommand(flowerLotService);
        }
        if (COMMAND_SHOW_LOT.equalsIgnoreCase(command)) {
            UserCreator userCreator = new UserCreator();
            FlowerLotCreator flowerLotCreator = new FlowerLotCreator(userCreator);
            FlowerLotDaoImpl FlowerLotDAO = new FlowerLotDaoImpl(flowerLotCreator);

            UserBetCreator betCreator = new UserBetCreator(userCreator, flowerLotCreator);
            UserBetDaoImpl userBetDAO = new UserBetDaoImpl(betCreator);

            FlowerLotService flowerLotService = new FlowerLotService(FlowerLotDAO);
            UserBetService userBetService = new UserBetService(userBetDAO);
            return new ShowLotCommand(flowerLotService, userBetService);
        }
        if (COMMAND_MAKE_BET.equalsIgnoreCase(command)) {
            UserCreator userCreator = new UserCreator();
            FlowerLotCreator lotCreator = new FlowerLotCreator(userCreator);
            UserBetCreator betCreator = new UserBetCreator(userCreator, lotCreator);

            UserBetDaoImpl userBetDao = new UserBetDaoImpl(betCreator);
            FlowerLotDaoImpl flowerLotDao = new FlowerLotDaoImpl(lotCreator);

            UserBetService userBetService = new UserBetService(userBetDao);
            FlowerLotService flowerLotService = new FlowerLotService(flowerLotDao);
            return new MakeBetCommand(userBetService, flowerLotService);
        }
        if(COMMAND_PROFILE.equalsIgnoreCase(command)) {
            UserCreator userCreator = new UserCreator();
            UserCardCreator creator = new UserCardCreator(userCreator);
            UserCardDaoImpl userCardDao = new UserCardDaoImpl(creator);
            UserCardService cardService = new UserCardService(userCardDao);

            FlowerLotCreator lotCreator = new FlowerLotCreator(userCreator);
            FlowerLotDaoImpl flowerLotDao = new FlowerLotDaoImpl(lotCreator);
            FlowerLotService lotService = new FlowerLotService(flowerLotDao);

            UserAddressesCreator addressesCreator = new UserAddressesCreator(userCreator);
            UserAddressesDaoImpl userAddressesDao = new UserAddressesDaoImpl(addressesCreator);
            UserAddressesService addressesService = new UserAddressesService(userAddressesDao);

            return new ProfileCommand(cardService, lotService, addressesService);
        }

        else {
            return null;
        }


    }

}

package com.petrushin.service;

import com.petrushin.creator.impl.UserCreator;
import com.petrushin.dao.impl.UserDAOImpl;
import com.petrushin.service.impl.LoginCommand;
import com.petrushin.service.impl.LogoutCommand;
import com.petrushin.service.impl.RegistrationCommand;

public final class CommandFactory {

    private static final String COMMAND_LOGIN = "login";
    private static final String COMMAND_REGISTRATION = "registration";
    private static final String COMMAND_LOGOUT = "logout";

    private CommandFactory() {

    }

    public static Command getCommand(String command) {

        if (command.equalsIgnoreCase(COMMAND_LOGIN)) {
            UserCreator creator = new UserCreator();
            UserDAOImpl userDAO = new UserDAOImpl(creator);
            return new LoginCommand(userDAO);
        }

        if (command.equalsIgnoreCase(COMMAND_REGISTRATION)) {
            UserCreator creator = new UserCreator();
            UserDAOImpl userDAO = new UserDAOImpl(creator);
            return new RegistrationCommand(userDAO);
        }

        if (command.equals(COMMAND_LOGOUT)) {
            return new LogoutCommand();
        } else {
            return null;
        }

    }

}

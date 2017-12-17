package com.petrushin.service;

import com.petrushin.service.impl.LoginCommand;
import com.petrushin.service.impl.LogoutCommand;
import com.petrushin.service.impl.RegistrationCommand;

public final class CommandFactory {

    private static final String LOGIN = "login";
    private static final String REGISTRATION = "registration";
    private static final String LOGOUT = "logout";

    private CommandFactory() {

    }

    public static Command getCommand(String command) {

        if (command.equalsIgnoreCase(LOGIN)) {
            return new LoginCommand();
        }

        if (command.equalsIgnoreCase(REGISTRATION)) {
            return new RegistrationCommand();
        }

        if (command.equals(LOGOUT)) {
            return new LogoutCommand();
        } else {
            return null;
        }

    }

}

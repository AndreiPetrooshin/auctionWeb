package com.petrushin.service;

import com.petrushin.service.impl.LoginCommandService;
import com.petrushin.service.impl.LogoutCommandService;
import com.petrushin.service.impl.RegistrationCommandService;

public final class CommandFactory {

    private static final String LOGIN = "login";
    private static final String REGISTRATION = "registration";
    public static final String LOGOUT = "logout";

    private CommandFactory(){

    }

    public static CommandService getCommand(String command) {

        if (command.equalsIgnoreCase(LOGIN)) {
            return new LoginCommandService();
        }

        if(command.equalsIgnoreCase(REGISTRATION)) {
            return  new RegistrationCommandService();
        }

        if(command.equals(LOGOUT)) {
            return  new LogoutCommandService();
        }

        else return  null;

    }

}

package com.petrushin.service.impl;

import com.petrushin.constants.Pages;
import com.petrushin.service.Command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LogoutCommand implements Command {


    private static final String USER = "user";

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();
        if (session != null) {
            session.removeAttribute(USER);
        }
        return Pages.START_PAGE;
    }
}

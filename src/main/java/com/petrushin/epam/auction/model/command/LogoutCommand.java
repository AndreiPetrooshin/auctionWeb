package com.petrushin.epam.auction.model.command;

import com.petrushin.epam.auction.constants.Pages;

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

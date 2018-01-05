package com.petrushin.epam.auction.model.command;

import com.petrushin.epam.auction.constants.Pages;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Class implements {@link Command} and handles the
 * logout operation
 *
 * @author Andrei Petrushin
 * @version 1.0.0
 */
public class LogoutCommand implements Command {


    private static final String ATTR_USER = "user";

    /**
     * Removes the user attribute from session
     *
     * @return String with url to forwarding
     */
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();
        if (session != null) {
            session.removeAttribute(ATTR_USER);
        }
        return Pages.START_PAGE;
    }
}

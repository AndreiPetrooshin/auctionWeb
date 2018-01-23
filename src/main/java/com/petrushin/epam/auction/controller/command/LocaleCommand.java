package com.petrushin.epam.auction.controller.command;

import com.petrushin.epam.auction.constants.Pages;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Class implements {@link Command} and changes Locale at web page.
 *
 * @author Andrei Petrushin
 * @version 1.0.0
 */
public class LocaleCommand implements Command {

    private static final String PARAM_LANG = "lang";
    private static final String LOCALE_EN = "en";
    private static final String LOCALE_RU = "ru";
    private static final String LOCALE_ES = "es";

    /**
     * Handles the lang element from request and changes the Locale
     * at web page.
     * Example of url request (localhost/command=locale&lang=???)
     *
     * @return String with url to forwarding
     */
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        String lang = request.getParameter(PARAM_LANG);
        HttpSession session = request.getSession();

        switch (lang) {
            case LOCALE_EN: {
                session.setAttribute(PARAM_LANG, LOCALE_EN);
                break;
            }
            case LOCALE_RU: {
                session.setAttribute(PARAM_LANG, LOCALE_RU);
                break;
            }
            case LOCALE_ES: {
                session.setAttribute(PARAM_LANG, LOCALE_ES);
                break;
            }
            default: {
                break;
            }
        }
        return Pages.HOME_PAGE;
    }
}

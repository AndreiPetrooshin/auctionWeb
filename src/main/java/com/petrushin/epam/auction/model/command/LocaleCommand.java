package com.petrushin.epam.auction.model.command;

import com.petrushin.epam.auction.constants.Pages;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LocaleCommand implements Command {

    private static final String PARAM_LANG = "lang";
    private static final String LOCALE_EN = "en";
    private static final String LOCALE_RU = "ru";
    private static final String LOCALE_BEL = "bel";

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
            case LOCALE_BEL: {
                session.setAttribute(PARAM_LANG, LOCALE_BEL);
                break;
            }
            default: {
                break;
            }
        }
        return Pages.HOME_PAGE;
    }
}

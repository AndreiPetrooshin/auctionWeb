package com.petrushin.epam.auction.command;

import com.petrushin.epam.auction.constants.Pages;
import com.petrushin.epam.auction.domain.User;
import com.petrushin.epam.auction.exceptions.EntityDAOException;
import com.petrushin.epam.auction.services.UserService;
import com.petrushin.epam.auction.services.encode.MD5EncodingService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Class implements {@link Command} and handles the
 * authorization operation
 *
 * @author Andrei Petrushin
 * @version 1.0.0
 */
public class LoginCommand implements Command {

    private static final Logger LOGGER = LogManager.getLogger(LoginCommand.class);

    private static final String PARAM_PASSWORD = "password";
    private static final String PARAM_USER = "user";
    private static final String PARAM_LOGIN = "login";
    private static final String ERROR_LOGIN = "errorLogin";

    private UserService userService;

    public LoginCommand(UserService userService) {
        this.userService = userService;
    }

    /**
     * Makes authorization operation.
     *
     * @return String with url to forwarding
     */
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {

        HttpSession session = request.getSession();
        String page = null;

        Object userObject = session.getAttribute(PARAM_USER);
        if (userObject == null) {
            String login = request.getParameter(PARAM_LOGIN);
            String password = request.getParameter(PARAM_PASSWORD);
            String md5Password = MD5EncodingService.encode(password);
            boolean ifUserExist = validate(session, login, md5Password);
            if (ifUserExist) {
                page = Pages.HOME_PAGE;
                session.removeAttribute(ERROR_LOGIN);
            } else {
                session.setAttribute(ERROR_LOGIN, true);
                page = Pages.LOGIN_PAGE;
            }
        }
        return page;
    }


    /**
     * Checks if the with that login and password exist at DB.
     *
     * @return true if user exist and false if not;
     */
    private boolean validate(HttpSession session, String login, String md5Password) {
        User bdUser = null;
        try {
            bdUser = userService.getByLogin(login);
        } catch (EntityDAOException e) {
            LOGGER.error("Error with user validation", e);
            session.setAttribute(ERROR_LOGIN, true);
        }
        boolean ifLoginSame = false;
        boolean ifPasswordSame = false;
        if (bdUser != null) {
            String bdLogin = bdUser.getLogin();
            String bdPassword = bdUser.getPassword();
            ifLoginSame = bdLogin.equals(login);
            ifPasswordSame = bdPassword.equals(md5Password);
        }
        if (ifLoginSame && ifPasswordSame) {
            session.setAttribute(PARAM_USER, bdUser);
        }
        return ifLoginSame && ifPasswordSame;
    }

}

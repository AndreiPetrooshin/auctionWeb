package com.petrushin.epam.auction.command;

import com.petrushin.epam.auction.constants.Pages;
import com.petrushin.epam.auction.exceptions.EntityDAOException;
import com.petrushin.epam.auction.domain.User;
import com.petrushin.epam.auction.domain.UserRole;
import com.petrushin.epam.auction.services.UserService;
import com.petrushin.epam.auction.util.ValidatorUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


/**
 * Class implements {@link Command} and handles the
 * Registration command.
 *
 * @author Andrei Petrushin
 * @version 1.0.0
 */
public class RegistrationCommand implements Command {

    private static final String ATTR_LOGIN_ERROR = "errorLogin";
    private static final String ATTR_INCORRECT_LOGIN = "incorrectLogin";
    private static final String ATTR_INCORRECT_EMAIL = "incorrectEmail";
    private static final String ATTR_INCORRECT_PASSWORD = "incorrectPassword";
    private static final String PARAM_LOGIN = "login";
    private static final String PARAM_PASSWORD = "password";
    private static final String PARAM_EMAIL = "email";
    private static final String ROLE_USER = "user";

    private UserService userService;

    public RegistrationCommand(UserService userService) {
        this.userService = userService;
    }

    /**
     * Registers the user at DB.
     *
     * @return String with url to forwarding
     */
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {

        HttpSession session = request.getSession();

        String login = request.getParameter(PARAM_LOGIN);
        String password = request.getParameter(PARAM_PASSWORD);
        String email = request.getParameter(PARAM_EMAIL);
        String url;
        try {
            if (validate(session, login, password, email)) {
                url = Pages.LOGIN_PAGE;
            } else {
                url = Pages.REGISTRATION_PAGE;
            }
        } catch (EntityDAOException e) {
            url = Pages.ERROR_PAGE;
        }
        return url;
    }

    /**
     * Validate login, password, email
     */
    private boolean validate(HttpSession session, String login, String password, String email)
            throws EntityDAOException {
        boolean isLoginCorrect = ValidatorUtil.validateLogin(login);
        if (!isLoginCorrect) {
            session.setAttribute(ATTR_INCORRECT_LOGIN, true);
        }
        boolean isEmailCorrect = ValidatorUtil.validateEmail(email);
        if (!isEmailCorrect) {
            session.setAttribute(ATTR_INCORRECT_EMAIL, true);
        }
        boolean isPasswordCorrect = ValidatorUtil.validatePassword(password);
        if (!isPasswordCorrect) {
            session.setAttribute(ATTR_INCORRECT_PASSWORD, true);
        }

        return isLoginCorrect && isEmailCorrect && isPasswordCorrect
                & createUserIfNotExist(session, login, password, email);
    }

    /**
     * Create user with valid parameters if email and login is unique
     *
     * @return true if user was created and false if not
     */
    private boolean createUserIfNotExist(HttpSession session, String login, String password, String email)
            throws EntityDAOException {
        boolean ifLoginExist = userService.ifLoginExist(login);
        boolean ifEmailExist = userService.ifEmailExist(email);
        if (!ifLoginExist && !ifEmailExist) {
            UserRole role = new UserRole(2L, ROLE_USER);
            User user = new User(0L, role, login, password, email);
            userService.save(user);
            session.removeAttribute(ATTR_INCORRECT_EMAIL);
            session.removeAttribute(ATTR_INCORRECT_LOGIN);
            session.removeAttribute(ATTR_INCORRECT_PASSWORD);
            session.removeAttribute(ATTR_LOGIN_ERROR);
            return true;
        } else {
            session.setAttribute(ATTR_LOGIN_ERROR, true);
        }
        return false;
    }
}

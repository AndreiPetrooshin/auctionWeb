package com.petrushin.epam.auction.model.command;

import com.petrushin.epam.auction.constants.Pages;
import com.petrushin.epam.auction.model.domain.User;
import com.petrushin.epam.auction.model.domain.UserRole;
import com.petrushin.epam.auction.model.validator.ValidatorUtil;
import com.petrushin.epam.auction.exceptions.EntityDAOException;
import com.petrushin.epam.auction.services.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


public class RegistrationCommand implements Command {

    private static final String ATTR_LOGIN_ERROR = "loginError";
    private static final String ATTR_INCORRECT_LOGIN = "incorrect_login";
    private static final String ATTR_INCORRECT_EMAIL = "incorrect_email";
    private static final String ATTR_INCORRECT_PASSWORD = "incorrect_password";
    private static final String PARAM_LOGIN = "login";
    private static final String PARAM_PASSWORD = "password";
    private static final String PARAM_EMAIL = "email";
    private static final String ROLE_USER = "user";
    private UserService userService;

    public RegistrationCommand(UserService userService) {
        this.userService = userService;
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {

        HttpSession session = request.getSession();

        String login = request.getParameter(PARAM_LOGIN);
        String password = request.getParameter(PARAM_PASSWORD);
        String email = request.getParameter(PARAM_EMAIL);
        String url;
        try {
            if (validate(session, login, password, email)) {
                url = Pages.HOME_PAGE;
            } else {
                url = Pages.REGISTRATION_PAGE;
            }
        } catch (EntityDAOException e) {
            url = Pages.ERROR_PAGE;
        }
        return url;
    }

    private boolean validate(HttpSession session, String login, String password, String email)
            throws EntityDAOException {
        boolean isLoginCorrect = ValidatorUtil.validateLogin(login);
        if (!isLoginCorrect) {
            session.setAttribute(ATTR_INCORRECT_LOGIN,
                    "Login should starts with letter and consist 5 or more characters");
        }
        boolean isEmailCorrect = ValidatorUtil.validateEmail(email);
        if (!isEmailCorrect) {
            session.setAttribute(ATTR_INCORRECT_EMAIL, "Email wrong syntax");
        }
        boolean isPasswordCorrect = ValidatorUtil.validatePassword(password);
        if (!isPasswordCorrect) {
            session.setAttribute(ATTR_INCORRECT_PASSWORD, "Password should consist 5 or more characters");
        }

        if (isLoginCorrect && isEmailCorrect && isPasswordCorrect) {

            boolean ifLoginExist = userService.ifLoginExist(login);
            boolean ifEmailExist = userService.ifEmailExist(email);
            if (!ifLoginExist && !ifEmailExist) {
                UserRole role = new UserRole(2L, ROLE_USER);
                User user = new User(0L, role, login, password, email);
                userService.save(user);
                return true;
            } else {
                session.setAttribute(ATTR_LOGIN_ERROR, "Login or Email Already Exist");
            }
        }
        return false;
    }
}

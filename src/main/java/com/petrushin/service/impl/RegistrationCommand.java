package com.petrushin.service.impl;

import com.petrushin.builder.UserBuilder;
import com.petrushin.dao.exception.UserDAOException;
import com.petrushin.dao.impl.UserDAOImpl;
import com.petrushin.domain.User;
import com.petrushin.service.Command;
import com.petrushin.service.exception.RegistrationCommandException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class RegistrationCommand implements Command {

    private static final String LOGIN = "login";
    private static final String PASSWORD = "password";
    private static final String EMAIL = "email";
    private static final String GO_TO_REGISTRATION_PAGE = "registration";
    private static final String LOGIN_EXIST = "loginExist";
    private static final String EMAIL_EXIST = "emailExist";
    private static final String GO_TO_LOGIN_PAGE = "login";

    private UserDAOImpl userDAO;

    public RegistrationCommand() {
        UserBuilder builder = new UserBuilder();
        userDAO = new UserDAOImpl(builder);
    }

    @Override
    public String execute(HttpServletRequest request,
                          HttpServletResponse response)
            throws RegistrationCommandException {

        HttpSession session = request.getSession();

        String login = request.getParameter(LOGIN);
        String password = request.getParameter(PASSWORD);
        String email = request.getParameter(EMAIL);
        try {
            if (login != null && password != null && email != null) {
                boolean ifLoginExist = userDAO.ifLoginExist(login);
                if (ifLoginExist) {
                    session.setAttribute(LOGIN_EXIST, true);
                    return GO_TO_REGISTRATION_PAGE;
                }

                boolean ifEmailExist = userDAO.ifEmailExist(email);
                if (ifEmailExist) {
                    session.setAttribute(EMAIL_EXIST, true);
                    return GO_TO_REGISTRATION_PAGE;
                }
                session.removeAttribute(LOGIN_EXIST);
                session.removeAttribute(EMAIL_EXIST);
                User user = new User(0, 2, login, password, email);
                userDAO.add(user);

                return GO_TO_LOGIN_PAGE;
            }
            return GO_TO_REGISTRATION_PAGE;
        } catch (UserDAOException e) {
            throw new RegistrationCommandException(e.getMessage());
        }
    }
}

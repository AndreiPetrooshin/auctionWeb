package com.petrushin.service.impl;

import com.petrushin.builder.impl.UserCreator;
import com.petrushin.dao.impl.UserDAOImpl;
import com.petrushin.domain.User;
import com.petrushin.domain.UserRole;
import com.petrushin.exceptions.CommandException;
import com.petrushin.exceptions.EntityDAOException;
import com.petrushin.service.Command;

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
    private static final String USER = "user";

    private UserDAOImpl userDAO;

    public RegistrationCommand() {
        UserCreator builder = new UserCreator();
        userDAO = new UserDAOImpl(builder);
    }

    @Override
    public String execute(HttpServletRequest request,
                          HttpServletResponse response)
            throws CommandException {

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
                UserRole role = new UserRole(2L, USER);
                User user = new User(0L, role, login, password, email);
                userDAO.save(user);

                return GO_TO_LOGIN_PAGE;
            }
            return GO_TO_REGISTRATION_PAGE;
        } catch (EntityDAOException e) {
            throw new CommandException(e.getMessage());
        }
    }
}

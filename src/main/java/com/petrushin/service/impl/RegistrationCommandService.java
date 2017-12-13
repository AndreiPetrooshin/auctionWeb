package com.petrushin.service.impl;

import com.petrushin.dao.exception.UserDAOException;
import com.petrushin.dao.impl.UserDAOImpl;
import com.petrushin.domain.User;
import com.petrushin.service.CommandService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class RegistrationCommandService implements CommandService {

    private static final String LOGIN = "login";
    private static final String PASSWORD = "password";
    private static final String EMAIL = "email";
    private UserDAOImpl userDAO;

    public RegistrationCommandService() {
        userDAO = new UserDAOImpl();
    }

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) {

        HttpSession session = request.getSession();

        String login = request.getParameter(LOGIN);
        String password = request.getParameter(PASSWORD);
        String email = request.getParameter(EMAIL);
        try {
            if (login != null && password != null && email != null) {
                boolean ifLoginExist = userDAO.ifLoginExist(login);

                if (ifLoginExist) {
                    session.setAttribute("loginExist", true);
                    response.sendRedirect("pages/registration.jsp");
                    return;
                }

                boolean ifEmailExist = userDAO.ifEmailExist(email);

                if (ifEmailExist) {
                    session.setAttribute("emailExist", true);
                    response.sendRedirect("pages/registration.jsp");
                    return;
                }
                session.removeAttribute("loginExist");
                session.removeAttribute("emailExist");

                User user = new User(0, 2, login, password, email);
                userDAO.add(user);
                response.sendRedirect("pages/login.jsp");

            }
        } catch (UserDAOException | IOException e) {
            e.printStackTrace();
        }

    }
}

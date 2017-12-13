package com.petrushin.service.impl;

import com.petrushin.dao.exception.UserDAOException;
import com.petrushin.dao.impl.UserDAOImpl;
import com.petrushin.domain.User;
import com.petrushin.service.CommandService;
import com.petrushin.service.encode.MD5EncodingService;
import com.petrushin.service.exception.MD5EncodingServiceException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class LoginCommandService implements CommandService {


    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) {

        HttpSession session = request.getSession();

        String login = request.getParameter("login");
        String password = request.getParameter("password");

        try {
            String md5Password = MD5EncodingService.encode(password);
            UserDAOImpl userDAO = new UserDAOImpl();
            User bdUser = userDAO.getByLogin(login);

            if (bdUser == null) {
                request.setAttribute("error", true);
//                response.sendRedirect("/pages/login.jsp");
                RequestDispatcher dispatcher = request.getRequestDispatcher("login");
                dispatcher.forward(request, response);
                return;
            }

            String bdLogin = bdUser.getLogin();
            String bdPassword = bdUser.getPassword();

            if (bdLogin.equals(login) && bdPassword.equals(md5Password)) {
                session.setAttribute("user", bdUser);
//                response.sendRedirect("/pages/home.jsp");
                RequestDispatcher dispatcher = request.getRequestDispatcher("home");
                dispatcher.forward(request, response);
            }


        } catch (MD5EncodingServiceException | UserDAOException | IOException | ServletException e) {
            e.printStackTrace();
        }

    }


}

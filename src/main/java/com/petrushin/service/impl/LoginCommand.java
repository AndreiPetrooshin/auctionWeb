package com.petrushin.service.impl;

import com.petrushin.builder.impl.UserCreator;
import com.petrushin.dao.impl.UserDAOImpl;
import com.petrushin.domain.User;
import com.petrushin.exceptions.CommandException;
import com.petrushin.exceptions.EntityDAOException;
import com.petrushin.exceptions.MD5EncodingServiceException;
import com.petrushin.service.Command;
import com.petrushin.service.encode.MD5EncodingService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LoginCommand implements Command {


    private static final String GO_TO_LOGIN_PAGE = "login";
    private static final String PASSWORD = "password";
    private static final String ERROR = "error";
    private static final String USER = "user";
    private static final String GO_TO_HOME_PAGE = "home";
    private UserDAOImpl userDAO;

    public LoginCommand() {
        UserCreator builder = new UserCreator();
        this.userDAO = new UserDAOImpl(builder);
    }

    @Override
    public String execute(HttpServletRequest request,
                          HttpServletResponse response)
            throws CommandException {

        HttpSession session = request.getSession();

        if (session.getAttribute(USER) != null) {
            return GO_TO_HOME_PAGE;
        }

        String login = request.getParameter(GO_TO_LOGIN_PAGE);
        String password = request.getParameter(PASSWORD);

        try {
            String md5Password = MD5EncodingService.encode(password);
            User bdUser = userDAO.getByLogin(login);
            if (bdUser != null) {
                String bdLogin = bdUser.getLogin();
                String bdPassword = bdUser.getPassword();
                if (bdLogin.equals(login) && bdPassword.equals(md5Password)) {
                    session.setAttribute(USER, bdUser);
                    return GO_TO_HOME_PAGE;
                }
            }
            request.setAttribute(ERROR, true);
            return GO_TO_LOGIN_PAGE;

        } catch (MD5EncodingServiceException | EntityDAOException e) {
            throw new CommandException(e.getMessage());
        }
    }


}

package com.petrushin.service.impl;

import com.petrushin.constants.Pages;
import com.petrushin.creator.impl.UserCreator;
import com.petrushin.dao.impl.UserDAOImpl;
import com.petrushin.domain.User;
import com.petrushin.exceptions.EntityDAOException;
import com.petrushin.service.Command;
import com.petrushin.service.encode.MD5EncodingService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LoginCommand implements Command {


    private static final String PARAM_PASSWORD = "password";
    private static final String PARAM_USER = "user";
    private static final String PARAM_LOGIN = "login";
    private static final String PARAM_ERROR = "error";

    private UserDAOImpl userDAO;

    public LoginCommand(UserDAOImpl userDAO) {
        this.userDAO = userDAO;
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {

        HttpSession session = request.getSession();
        String url = Pages.START_PAGE;

        if (session.getAttribute(PARAM_USER) == null) {
            String login = request.getParameter(PARAM_LOGIN);
            String password = request.getParameter(PARAM_PASSWORD);
            String md5Password = MD5EncodingService.encode(password);
            boolean ifUserExist = false;
            try {
                ifUserExist = validate(session, login, md5Password);
            } catch (EntityDAOException e) {
                request.setAttribute(PARAM_ERROR, "Something go wrong with DAO");
            }
            if(ifUserExist){
                url = Pages.HOME_PAGE;
            } else {
                request.setAttribute(PARAM_ERROR, "Password or login is incorrect");
                url = Pages.LOGIN_PAGE;
            }
        }
        return url;
    }

    private boolean validate(HttpSession session, String login, String md5Password) throws EntityDAOException {
        User bdUser = userDAO.getByLogin(login);
        boolean ifLoginSame = false;
        boolean ifPasswordSame = false;
        if (bdUser != null) {
            String bdLogin = bdUser.getLogin();
            String bdPassword = bdUser.getPassword();
            ifLoginSame = bdLogin.equals(login);
            ifPasswordSame = bdPassword.equals(md5Password);
            session.setAttribute(PARAM_USER, bdUser);
        }
        return ifLoginSame && ifPasswordSame;
    }

}

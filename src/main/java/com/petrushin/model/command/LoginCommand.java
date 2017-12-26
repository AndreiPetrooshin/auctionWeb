package com.petrushin.model.command;

import com.petrushin.constants.Pages;
import com.petrushin.model.domain.User;
import com.petrushin.exceptions.EntityDAOException;
import com.petrushin.model.encode.MD5EncodingService;
import com.petrushin.services.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LoginCommand implements Command {


    private static final String PARAM_PASSWORD = "password";
    private static final String PARAM_USER = "user";
    private static final String PARAM_LOGIN = "login";
    private static final String PARAM_ERROR = "error";

    private UserService userService;

    public LoginCommand(UserService userService) {
        this.userService = userService;
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
        User bdUser = userService.getByLogin(login);
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

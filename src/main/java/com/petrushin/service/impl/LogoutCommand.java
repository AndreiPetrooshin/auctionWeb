package com.petrushin.service.impl;

import com.petrushin.service.Command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class LogoutCommand implements Command {


    private static final String USER = "user";
    private static final String GO_TO_INDEX_PAGE = "";

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession(false);
        if(session!=null){
            session.removeAttribute(USER);
            session.invalidate();
            return GO_TO_INDEX_PAGE;
        } else {
            return GO_TO_INDEX_PAGE;
        }
    }
}

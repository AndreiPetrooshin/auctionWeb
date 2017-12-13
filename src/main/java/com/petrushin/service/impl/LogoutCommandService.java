package com.petrushin.service.impl;

import com.petrushin.service.CommandService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class LogoutCommandService implements CommandService {


    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) {
        try {
            HttpSession session = request.getSession(false);
            if(session!=null){
                session.removeAttribute("user");
                session.invalidate();
                response.sendRedirect("/");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

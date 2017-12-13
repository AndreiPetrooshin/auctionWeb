package com.petrushin.controller;

import com.petrushin.service.CommandFactory;
import com.petrushin.service.CommandService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ServletFrontController extends HttpServlet {

    private static final String COMMAND = "command";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        String command = req.getParameter(COMMAND);
        if (command != null) {
            CommandService service = CommandFactory.getCommand(command);
            if (service != null) {
                service.execute(req, resp);
            }
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        
        resp.setContentType("text/html");

        String command = req.getParameter(COMMAND);
        if (command != null) {
            CommandService service = CommandFactory.getCommand(command);
            if (service != null) {
                service.execute(req, resp);
            }
        }


    }
}

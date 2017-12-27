package com.petrushin.controller;

import com.petrushin.constants.Pages;
import com.petrushin.model.command.Command;
import com.petrushin.model.command.CommandFactory;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ServletFrontController extends HttpServlet {

    private static final String COMMAND = "command";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        processCommand(req, resp);
    }


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        processCommand(req, resp);
    }

    private void processCommand(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException {
        String command = req.getParameter(COMMAND);
        if (command != null) {
            Command service = CommandFactory.getCommand(command);
            if (service != null) {
                try {
                    String link = service.execute(req, resp);
                    RequestDispatcher dispatcher;
                    if (link != null) {
                        dispatcher = req.getRequestDispatcher(link);
                        dispatcher.forward(req, resp);
                    } else {
                        dispatcher = req.getRequestDispatcher(Pages.HOME_PAGE);
                        dispatcher.forward(req, resp);
                    }
                } catch (ServletException | IOException e) {
                    throw new ServletException(e.getMessage(), e);
                }
            }
        }
    }
}

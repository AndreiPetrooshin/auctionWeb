package com.petrushin.epam.auction.controller;

import com.petrushin.epam.auction.constants.Pages;
import com.petrushin.epam.auction.services.command.Command;
import com.petrushin.epam.auction.services.factory.CommandFactory;
import com.petrushin.epam.auction.services.factory.CreatorFactory;
import com.petrushin.epam.auction.services.factory.DaoFactory;
import com.petrushin.epam.auction.services.factory.ServiceFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ServletFrontController extends HttpServlet {

    private static final String COMMAND = "command";
    private static final String ATTR_REDIRECT = "redirect";

    private static final Logger LOGGER = LogManager.getLogger(ServletFrontController.class);

    /**
     * Injection of all needed dependencies to {@link CommandFactory}
     */
    private static CreatorFactory creatorFactory = new CreatorFactory();
    private static DaoFactory daoFactory = new DaoFactory(creatorFactory);
    private static ServiceFactory serviceFactory = new ServiceFactory(daoFactory);
    private static CommandFactory commandFactory = new CommandFactory(serviceFactory);

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

    /**
     * This method process command from url request (Example: localhost/command=????)
     * by extracting the required command {@link Command} from the factory by command name
     */
    private void processCommand(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException {

        String command = req.getParameter(COMMAND);
        if (command == null || !command.isEmpty()) {
            Command service = commandFactory.getCommand(command);
            if (service != null) {
                String page = service.execute(req, resp);
                if (page != null) {
                    forwardToPage(req, resp, page);
                } else {
                    forwardToPage(req, resp, Pages.HOME_PAGE);
                }
            }
        }
    }

    /**
     * Forwarding to current page
     */
    private void forwardToPage(HttpServletRequest req, HttpServletResponse resp, String page) {
        RequestDispatcher dispatcher;
        dispatcher = req.getRequestDispatcher(page);
        boolean isRedirect = (boolean) req.getAttribute(ATTR_REDIRECT);
        try {
            if (isRedirect) {
                resp.sendRedirect(page);
            } else {
                dispatcher.forward(req, resp);
            }
        } catch (ServletException | IOException e) {
            LOGGER.error("Error with forward page to:" + page, e);
            dispatcher = req.getRequestDispatcher(Pages.ERROR_PAGE);
            try {
                dispatcher.forward(req, resp);
            } catch (ServletException | IOException e1) {
                LOGGER.error("Error with forwarding", e);
            }
        }
    }
}

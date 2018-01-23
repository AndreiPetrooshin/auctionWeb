package com.petrushin.epam.auction.controller;

import com.petrushin.epam.auction.constants.Pages;
import com.petrushin.epam.auction.controller.command.Command;
import com.petrushin.epam.auction.factory.CommandFactory;
import com.petrushin.epam.auction.factory.CreatorFactory;
import com.petrushin.epam.auction.factory.DaoFactory;
import com.petrushin.epam.auction.factory.ServiceFactory;
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
        String page = processCommand(req, resp);
        RequestDispatcher requestDispatcher = req.getRequestDispatcher(page);
        requestDispatcher.forward(req, resp);
    }


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        String page = processCommand(req, resp);
        resp.sendRedirect(page);
    }

    /**
     * This method process command from url request (Example: localhost/command=????)
     * by extracting the required command {@link Command} from the factory by command name
     */
    private String processCommand(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException {
        String command = req.getParameter(COMMAND);
        if (command == null || !command.isEmpty()) {
            Command service = commandFactory.getCommand(command);
            if (service != null) {
                String page = service.execute(req, resp);
                if (page != null) {
                    return page;
                }
            }
        }
        return Pages.HOME_PAGE;
    }

}

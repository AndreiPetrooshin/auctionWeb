package com.petrushin.epam.auction.controller.command;

import com.petrushin.epam.auction.constants.Pages;
import com.petrushin.epam.auction.exceptions.EntityDAOException;
import com.petrushin.epam.auction.domain.FlowerLot;
import com.petrushin.epam.auction.services.FlowerLotService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class AdminCommand implements Command {

    private static final Logger LOGGER = LogManager.getLogger(AdminCommand.class);
    private static final String ATTR_LOTS = "lots";
    private static final String ATTR_ERROR_ADMIN = "errorAdmin";

    private FlowerLotService lotService;

    public AdminCommand(FlowerLotService lotService) {
        this.lotService = lotService;
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        try {
            List<FlowerLot> lots = lotService.getAll();
            request.setAttribute(ATTR_LOTS, lots);
            request.removeAttribute(ATTR_ERROR_ADMIN);
        } catch (EntityDAOException e) {
            LOGGER.error("Error with getting all lots", e);
            request.setAttribute(ATTR_ERROR_ADMIN, true);
        }
        return Pages.ADMIN_PAGE;
    }
}

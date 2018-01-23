package com.petrushin.epam.auction.controller.command;

import com.petrushin.epam.auction.constants.Pages;
import com.petrushin.epam.auction.domain.UserAddress;
import com.petrushin.epam.auction.exceptions.EntityDAOException;
import com.petrushin.epam.auction.services.UserAddressesService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class WinnerAddressCommand implements Command {

    private static final Logger LOGGER = LogManager.getLogger(WinnerAddressCommand.class);
    private static final String WINNER_ADDRESS_ERROR = "winner_address_error";

    private UserAddressesService addressesService;

    public WinnerAddressCommand(UserAddressesService addressesService) {
        this.addressesService = addressesService;
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        String userIdValue = request.getParameter("userId");
        Long userId = null;
        if (userIdValue != null) {
            userId = Long.valueOf(userIdValue);
        }
        try {
            UserAddress userAddress = addressesService.getByUserId(userId);
            request.setAttribute("address", userAddress);
        } catch (EntityDAOException e) {
            LOGGER.error(e.getMessage(), e);
            request.setAttribute(WINNER_ADDRESS_ERROR, true);
        }
        return Pages.SEND_LOT_PAGE;
    }
}

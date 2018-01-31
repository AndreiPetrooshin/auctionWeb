package com.petrushin.epam.auction.command;

import com.petrushin.epam.auction.constants.Pages;
import com.petrushin.epam.auction.domain.UserAddress;
import com.petrushin.epam.auction.domain.dto.UserAddressDto;
import com.petrushin.epam.auction.exceptions.EntityDAOException;
import com.petrushin.epam.auction.services.UserAddressesService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class WinnerAddressCommand implements Command {

    private static final Logger LOGGER = LogManager.getLogger(WinnerAddressCommand.class);
    private static final String WINNER_ADDRESS_ERROR = "winner_address_error";
    private static final String PARAM_USER_ID = "userId";
    private static final String ATTR_ADDRESS = "address";

    private UserAddressesService addressesService;

    public WinnerAddressCommand(UserAddressesService addressesService) {
        this.addressesService = addressesService;
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        String userIdValue = request.getParameter(PARAM_USER_ID);
        Long userId = null;
        if (userIdValue != null) {
            userId = Long.valueOf(userIdValue);
        }
        try {
            UserAddress userAddress = addressesService.getByUserId(userId);
            UserAddressDto addressDto = new UserAddressDto(userAddress);
            request.setAttribute(ATTR_ADDRESS, addressDto);
        } catch (EntityDAOException e) {
            LOGGER.error(e.getMessage(), e);
            request.setAttribute(WINNER_ADDRESS_ERROR, true);
        }
        return Pages.SEND_LOT_PAGE;
    }
}

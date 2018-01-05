package com.petrushin.epam.auction.model.command;

import com.petrushin.epam.auction.constants.Pages;
import com.petrushin.epam.auction.exceptions.EntityDAOException;
import com.petrushin.epam.auction.model.domain.FlowerLot;
import com.petrushin.epam.auction.model.domain.User;
import com.petrushin.epam.auction.model.domain.UserAddress;
import com.petrushin.epam.auction.model.domain.UserCard;
import com.petrushin.epam.auction.services.FlowerLotService;
import com.petrushin.epam.auction.services.UserAddressesService;
import com.petrushin.epam.auction.services.UserCardService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.math.BigDecimal;

/**
 * Class implements {@link Command} and handles the
 * save commands.
 *
 * @author Andrei Petrushin
 * @version 1.0.0
 */
public class SaveCommand implements Command {

    private static final Logger LOGGER = LogManager.getLogger(SaveCommand.class);

    private static final String ATTR_ERROR = "error";
    private static final String ATTR_USER = "user";
    private static final String LOT_STATE = "new";
    private static final String PARAM_ELEMENT = "element";
    private static final String PARAM_ADDRESS = "address";
    private static final String PARAM_LOT = "lot";
    private static final String PARAM_TYPE = "type";
    private static final String PARAM_NAME = "name";
    private static final String PARAM_DESCRIPTION = "description";
    private static final String PARAM_START_PRICE = "startPrice";
    private static final String PARAM_CARD = "card";
    private static final String PARAM_CARD_NUMBER = "card_number";
    private static final String PARAM_FIRST_NAME = "firstName";
    private static final String PARAM_SECOND_NAME = "secondName";
    private static final String PARAM_LAST_NAME = "lastName";
    private static final String PARAM_COUNTRY = "country";
    private static final String PARAM_CITY = "city";
    private static final String PARAM_STREET = "street";
    private static final String PARAM_PHONE = "phone";
    private static final String PARAM_POSTAL_CODE = "postalCode";
    private static final String PARAM_ACTIVE = "active";

    private FlowerLotService lotService;
    private UserCardService cardService;
    private UserAddressesService addressesService;

    public SaveCommand(FlowerLotService lotService, UserCardService cardService,
                       UserAddressesService addressesService) {
        this.lotService = lotService;
        this.cardService = cardService;
        this.addressesService = addressesService;
    }

    /**
     * Saves the users things by 'element' parameter from request
     * Url example: localhost/command=save&element=lot;
     *
     * @return String with url to forwarding
     */
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {

        String element = request.getParameter(PARAM_ELEMENT);

        switch (element) {
            case PARAM_LOT: {
                saveLot(request);
                break;
            }
            case PARAM_CARD: {
                saveCard(request);
            }
            case PARAM_ADDRESS: {
                saveAddress(request);
            }
            default: {
                break;
            }
        }
        return Pages.PROFILE_PAGE;
    }

    /**
     * Saves the user's address
     */
    private void saveAddress(HttpServletRequest request) {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute(ATTR_USER);
        String firstName = request.getParameter(PARAM_FIRST_NAME);
        String secondName = request.getParameter(PARAM_SECOND_NAME);
        String lastName = request.getParameter(PARAM_LAST_NAME);
        String country = request.getParameter(PARAM_COUNTRY);
        String city = request.getParameter(PARAM_CITY);
        String street = request.getParameter(PARAM_STREET);
        String phone = request.getParameter(PARAM_PHONE);
        String postalCode = request.getParameter(PARAM_POSTAL_CODE);
        String active = request.getParameter(PARAM_ACTIVE);
        boolean isActive = Boolean.parseBoolean(active);
        UserAddress address = new UserAddress(0L, user,
                firstName, secondName, lastName, country,
                city, street, phone, postalCode, isActive);
        try {
            addressesService.save(address);
        } catch (EntityDAOException e) {
            LOGGER.error("Error with saving address", e);
            request.setAttribute(ATTR_ERROR, "Error with address saving, sorry");
        }
    }

    /**
     * Saves the user's card
     */
    private void saveCard(HttpServletRequest request) {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute(ATTR_USER);
        String cardName = request.getParameter(PARAM_NAME);
        String cardNumber = request.getParameter(PARAM_CARD_NUMBER);
        UserCard card = new UserCard(0L, user, cardNumber, cardName);
        try {
            cardService.save(card);
        } catch (EntityDAOException e) {
            request.setAttribute(ATTR_ERROR, true);
            LOGGER.error("Error with saving card", e);
        }
    }

    /**
     * Saves the user's lot
     */
    private void saveLot(HttpServletRequest request) {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute(ATTR_USER);
        String type = request.getParameter(PARAM_TYPE);
        String name = request.getParameter(PARAM_NAME);
        String description = request.getParameter(PARAM_DESCRIPTION);
        String startPriceValue = request.getParameter(PARAM_START_PRICE);
        BigDecimal startPrice = null;
        if (!startPriceValue.isEmpty()) {
            Double temp = Double.valueOf(startPriceValue);
            startPrice = BigDecimal.valueOf(temp);
        }
        FlowerLot lot = new FlowerLot(0L, user, type, name, description, startPrice, LOT_STATE);
        try {
            lotService.save(lot);
        } catch (EntityDAOException e) {
            request.setAttribute(ATTR_ERROR, true);
            LOGGER.error("Error with saving lot", e);
        }
    }
}

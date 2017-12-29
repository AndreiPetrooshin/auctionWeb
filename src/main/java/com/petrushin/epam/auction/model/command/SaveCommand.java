package com.petrushin.epam.auction.model.command;

import com.petrushin.epam.auction.constants.Pages;
import com.petrushin.epam.auction.exceptions.EntityDAOException;
import com.petrushin.epam.auction.model.domain.User;
import com.petrushin.epam.auction.model.domain.UserCard;
import com.petrushin.epam.auction.services.FlowerLotService;
import com.petrushin.epam.auction.model.domain.FlowerLot;
import com.petrushin.epam.auction.services.UserCardService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.math.BigDecimal;

public class SaveCommand implements Command {

    private static final Logger LOGGER = LogManager.getLogger(SaveCommand.class);

    private static final String PARAM_ELEMENT = "element";
    private static final String PARAM_LOT = "lot";
    private static final String ATTR_USER = "user";
    private static final String LOT_STATE = "new";
    private static final String PARAM_TYPE = "type";
    private static final String PARAM_NAME = "name";
    private static final String PARAM_DESCRIPTION = "description";
    private static final String PARAM_START_PRICE = "startPrice";
    private static final String PARAM_CARD = "card";
    private static final String PARAM_CARD_NUMBER = "card_number";

    private FlowerLotService lotService;
    private UserCardService cardService;

    public SaveCommand(FlowerLotService lotService, UserCardService cardService) {
        this.lotService = lotService;
        this.cardService = cardService;
    }

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
            default:break;
        }

        return Pages.PROFILE_PAGE;
    }

    private void saveCard(HttpServletRequest request) {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute(ATTR_USER);
        String cardName = request.getParameter(PARAM_NAME);
        String cardNumber = request.getParameter(PARAM_CARD_NUMBER);
        UserCard card = new UserCard(0L, user, cardNumber, cardName);
        try {
            cardService.save(card);
        } catch (EntityDAOException e) {
            request.setAttribute("error", "Error with card saving, sorry");
            LOGGER.error("Error with saving card", e);
        }
    }

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
            request.setAttribute("error", "Error with lot saving, sorry");
            LOGGER.error("Error with saving lot", e);
        }
    }
}

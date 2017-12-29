package com.petrushin.epam.auction.model.command;

import com.petrushin.epam.auction.constants.Pages;
import com.petrushin.epam.auction.exceptions.EntityDAOException;
import com.petrushin.epam.auction.services.FlowerLotService;
import com.petrushin.epam.auction.services.UserCardService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class DeleteCommand implements Command {

    private static final Logger LOGGER = LogManager.getLogger(DeleteCommand.class);

    private static final String PARAM_CARD = "card";
    private static final String PARAM_ELEMENT = "element";
    private static final String PARAM_LOT = "lot";
    private static final String PARAM_ID = "id";

    private FlowerLotService lotService;
    private UserCardService cardService;

    public DeleteCommand(FlowerLotService lotService, UserCardService cardService) {
        this.lotService = lotService;
        this.cardService = cardService;
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        String element = request.getParameter(PARAM_ELEMENT);
        String url = null;
        switch (element) {
            case PARAM_LOT: {
                url = deleteLot(request);
                break;
            }
            case PARAM_CARD: {
                url = deleteCard(request);
            }
            default: {
                break;
            }
        }
        if (PARAM_LOT.equals(element)) {
            url = deleteLot(request);
        }
        return url;

    }

    private String deleteCard(HttpServletRequest request) {
        String idValue = request.getParameter(PARAM_ID);
        if (!idValue.isEmpty()) {
            Long id = Long.valueOf(idValue);
            try {
                cardService.delete(id);
            } catch (EntityDAOException e) {
                request.setAttribute("error", "Error with card deleting");
                LOGGER.error("Delete card operation error", e);
            }
        }
        return Pages.PROFILE_ABOUT_CARDS;
    }

    private String deleteLot(HttpServletRequest request) {
        String idValue = request.getParameter(PARAM_ID);
        if (!idValue.isEmpty()) {
            Long id = Long.valueOf(idValue);
            try {
                lotService.delete(id);
            } catch (EntityDAOException e) {
                request.setAttribute("error", "Error with lot deleting");
                LOGGER.error("Delete lot operation error", e);
            }
        }
        return Pages.PROFILE_ABOUT_MYSELF_PAGE;
    }
}

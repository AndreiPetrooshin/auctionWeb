package com.petrushin.epam.auction.model.command;

import com.petrushin.epam.auction.constants.Pages;
import com.petrushin.epam.auction.exceptions.EntityDAOException;
import com.petrushin.epam.auction.services.FlowerLotService;
import com.petrushin.epam.auction.services.UserCardService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Class implements {@link Command} and handling the Delete commands
 *
 * @author Andrei Petrushin
 * @version 1.0.0
 */
public class DeleteCommand implements Command {

    private static final Logger LOGGER = LogManager.getLogger(DeleteCommand.class);

    private static final String PARAM_CARD = "card";
    private static final String PARAM_ELEMENT = "element";
    private static final String PARAM_LOT = "lot";
    private static final String PARAM_ID = "id";
    private static final String ATTR_DELETE_CARD_ERROR = "delete_card_error";
    private static final String ATTR_DELETE_LOT_ERROR = "delete_lot_error";


    private FlowerLotService lotService;
    private UserCardService cardService;

    public DeleteCommand(FlowerLotService lotService, UserCardService cardService) {
        this.lotService = lotService;
        this.cardService = cardService;
    }

    /**
     * Handles the element witch will be deleting by
     * url request (localhost/command=delete&element=???)
     *
     * @return String with url to forwarding
     */
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

    /**
     * This method deletes the user card from DB
     *
     * @return String with url to forwarding
     */
    private String deleteCard(HttpServletRequest request) {
        String idValue = request.getParameter(PARAM_ID);
        if (!idValue.isEmpty()) {
            Long id = Long.valueOf(idValue);
            try {
                cardService.delete(id);
            } catch (EntityDAOException e) {
                LOGGER.error("Delete card operation error", e);
                request.setAttribute(ATTR_DELETE_CARD_ERROR, true);
            }
        }
        return Pages.PROFILE_ABOUT_CARDS;
    }

    /**
     * This method deletes the user lot from DB
     *
     * @return String with url to forwarding
     */
    private String deleteLot(HttpServletRequest request) {
        String idValue = request.getParameter(PARAM_ID);
        if (!idValue.isEmpty()) {
            Long id = Long.valueOf(idValue);
            try {
                lotService.delete(id);
            } catch (EntityDAOException e) {
                LOGGER.error("Delete lot operation error", e);
                request.setAttribute(ATTR_DELETE_LOT_ERROR, true);
            }
        }
        return Pages.PROFILE_ABOUT_MYSELF_PAGE;
    }
}

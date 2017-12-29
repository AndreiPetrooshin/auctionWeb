package com.petrushin.epam.auction.model.command;

import com.petrushin.epam.auction.constants.Pages;
import com.petrushin.epam.auction.exceptions.EntityDAOException;
import com.petrushin.epam.auction.model.domain.FlowerLot;
import com.petrushin.epam.auction.model.domain.User;
import com.petrushin.epam.auction.model.domain.UserCard;
import com.petrushin.epam.auction.services.FlowerLotService;
import com.petrushin.epam.auction.services.UserAddressesService;
import com.petrushin.epam.auction.services.UserCardService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

public class ProfileCommand implements Command {

    private static final Logger LOGGER = LogManager.getLogger(ProfileCommand.class);

    private static final String PARAM_LOTS = "lots";
    private static final String ATTR_ABOUT_CARDS = "about_cards";
    private static final String PARAM_ABOUT = "about";
    private static final String PARAM_MYSELF = "myself";
    private static final String PARAM_CARDS = "cards";
    private static final String ATTR_USER = "user";
    private static final String ATTR_MYSELF = "about_myself";
    private static final String ATTR_ABOUT_LOTS = "about_lots";

    private UserCardService cardService;
    private FlowerLotService lotService;
    private UserAddressesService addressesService;

    public ProfileCommand(UserCardService cardService,
                          FlowerLotService lotService, UserAddressesService addressesService) {
        this.cardService = cardService;
        this.lotService = lotService;
        this.addressesService = addressesService;
    }



    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        String about = request.getParameter(PARAM_ABOUT);
        HttpSession session = request.getSession();
        String url = null;

        switch (about) {
            case PARAM_MYSELF: {
                url = processParamMyself(request);
                break;
            }
            case PARAM_CARDS: {
                url = processParamCards(request, session);
                break;
            }
            case PARAM_LOTS: {
                url = processParamLots(request, session);
                break;
            }
            default:break;
        }
        return url;


    }

    private String processParamLots(HttpServletRequest request, HttpSession session) {
        User user = (User) session.getAttribute(ATTR_USER);
        Long id = user.getId();
        try {
            List<FlowerLot> lots = lotService.getByUserId(id);
            request.setAttribute(PARAM_LOTS, lots);
            request.setAttribute(ATTR_ABOUT_LOTS, true);
        } catch (EntityDAOException e) {
            LOGGER.error("Error with getting User Lots by user id", e);
        }
        return Pages.PROFILE_PAGE;
    }

    private String processParamMyself(HttpServletRequest request) {
        request.setAttribute(ATTR_MYSELF, true);
        return Pages.PROFILE_PAGE;
    }

    private String processParamCards(HttpServletRequest request, HttpSession session) {
        User user = (User) session.getAttribute(ATTR_USER);
        Long id = user.getId();
        try {
            List<UserCard> cards = cardService.getByUserId(id);
            request.setAttribute(ATTR_ABOUT_CARDS, true);
            request.setAttribute(PARAM_CARDS, cards);

        } catch (EntityDAOException e) {
            LOGGER.error("Error with getting Payment cards by user id", e);
        }
        return Pages.PROFILE_PAGE;
    }
}

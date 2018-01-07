package com.petrushin.epam.auction.model.command;

import com.petrushin.epam.auction.constants.Pages;
import com.petrushin.epam.auction.exceptions.ServiceException;
import com.petrushin.epam.auction.model.domain.*;
import com.petrushin.epam.auction.services.FlowerLotService;
import com.petrushin.epam.auction.services.PaymentService;
import com.petrushin.epam.auction.services.UserAddressesService;
import com.petrushin.epam.auction.services.UserCardService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;


/**
 * Class implements {@link Command} and handles the
 * profile commands.
 *
 * @author Andrei Petrushin
 * @version 1.0.0
 */
public class ProfileCommand implements Command {

    private static final Logger LOGGER = LogManager.getLogger(ProfileCommand.class);

    private static final String PARAM_LOTS = "lots";
    private static final String PARAM_CARDS = "cards";
    private static final String PARAM_ABOUT = "about";
    private static final String PARAM_MYSELF = "myself";
    private static final String PARAM_ADDRESSES = "address";
    private static final String ATTR_USER = "user";
    private static final String ATTR_ERROR = "error";
    private static final String ATTR_MYSELF = "about_myself";
    private static final String ATTR_ABOUT_LOTS = "about_lots";
    private static final String ATTR_ABOUT_CARDS = "about_cards";
    private static final String ATTR_ABOUT_ADDRESSES = "about_addresses";
    private static final String PARAM_WINNINGS = "winnings";
    private static final String ATTR_ABOUT_WINS = "about_wins";
    private static final String ATTR_WINS = "winnings";
    private static final String ATTR_CARDS = "cards";

    private UserCardService cardService;
    private FlowerLotService lotService;
    private UserAddressesService addressesService;
    private PaymentService paymentService;

    public ProfileCommand(UserCardService cardService, FlowerLotService lotService,
                          UserAddressesService addressesService, PaymentService paymentService) {
        this.cardService = cardService;
        this.lotService = lotService;
        this.addressesService = addressesService;
        this.paymentService = paymentService;
    }

    /**
     * Handles the 'about' element from request and shows the
     * necessary information about user.
     * Example of url request (localhost/command=profile&about=???)
     *
     * @return String with url to forwarding
     */
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
            case PARAM_ADDRESSES: {
                url = processParamAddresses(request, session);
                break;
            }
            case PARAM_WINNINGS: {
                url = processParamWins(request, session);
                break;
            }
            default:
                break;
        }
        return url;
    }

    /**
     * Processing the wins parameter and setting attribute
     * to request with user wins
     */
    private String processParamWins(HttpServletRequest request, HttpSession session) {
        User user = (User) session.getAttribute(ATTR_USER);
        if (user == null) {
            return Pages.START_PAGE;
        }
        Long id = user.getId();
        try {
            List<Payment> winnings = paymentService.getByUserId(id);
            List<UserCard> cards = cardService.getByUserId(id);
            request.setAttribute(ATTR_CARDS, cards);
            request.setAttribute(ATTR_WINS, winnings);
            request.setAttribute(ATTR_ABOUT_WINS, true);
        } catch (ServiceException e) {
            LOGGER.error("Error with getting User's wins by user id", e);
            request.setAttribute(ATTR_ERROR, true);
        }
        return Pages.PROFILE_PAGE;
    }

    /**
     * Processing the addresses parameter and setting attribute
     * to request with user addresses
     */
    private String processParamAddresses(HttpServletRequest request, HttpSession session) {
        User user = (User) session.getAttribute(ATTR_USER);
        if (user == null) {
            return Pages.START_PAGE;
        }
        Long id = user.getId();
        try {
            UserAddress address = addressesService.getByUserId(id);
            request.setAttribute(PARAM_ADDRESSES, address);
            request.setAttribute(ATTR_ABOUT_ADDRESSES, true);
        } catch (ServiceException e) {
            LOGGER.error("Error with getting User Addresses by user id", e);
            request.setAttribute(ATTR_ERROR, true);
        }
        return Pages.PROFILE_PAGE;

    }

    /**
     * Processing the addresses parameter and setting attribute
     * to request with user lots
     */
    private String processParamLots(HttpServletRequest request, HttpSession session) {
        User user = (User) session.getAttribute(ATTR_USER);
        String url = null;
        if (user == null) {
            return Pages.START_PAGE;
        }
        Long id = user.getId();
        try {
            List<FlowerLot> lots = lotService.getByUserId(id);
            request.setAttribute(PARAM_LOTS, lots);
            request.setAttribute(ATTR_ABOUT_LOTS, true);
            url = Pages.PROFILE_PAGE;
        } catch (ServiceException e) {
            LOGGER.error("Error with getting User Lots by user id", e);
        }
        return url;
    }

    /**
     * Processing the addresses parameter and setting attribute
     * to request with user info
     */
    private String processParamMyself(HttpServletRequest request) {
        request.setAttribute(ATTR_MYSELF, true);
        return Pages.PROFILE_PAGE;
    }

    /**
     * Processing the addresses parameter and setting attribute
     * to request with user cards
     */
    private String processParamCards(HttpServletRequest request, HttpSession session) {
        User user = (User) session.getAttribute(ATTR_USER);
        if (user == null) {
            return Pages.START_PAGE;
        }
        Long id = user.getId();
        try {
            List<UserCard> cards = cardService.getByUserId(id);
            request.setAttribute(ATTR_ABOUT_CARDS, true);
            request.setAttribute(PARAM_CARDS, cards);

        } catch (ServiceException e) {
            LOGGER.error("Error with getting Payment cards by user id", e);
        }
        return Pages.PROFILE_PAGE;
    }
}

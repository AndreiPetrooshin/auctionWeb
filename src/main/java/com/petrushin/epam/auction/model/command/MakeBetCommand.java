package com.petrushin.epam.auction.model.command;

import com.petrushin.epam.auction.constants.Pages;
import com.petrushin.epam.auction.exceptions.ServiceException;
import com.petrushin.epam.auction.model.domain.FlowerLot;
import com.petrushin.epam.auction.model.domain.User;
import com.petrushin.epam.auction.model.domain.UserBet;
import com.petrushin.epam.auction.services.FlowerLotService;
import com.petrushin.epam.auction.services.UserBetService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.math.BigDecimal;

/**
 * Class implements {@link Command} and handles the
 * bet operations
 *
 * @author Andrei Petrushin
 * @version 1.0.0
 */
public class MakeBetCommand implements Command {

    private static final Logger LOGGER = LogManager.getLogger(MakeBetCommand.class);

    private static final String PARAM_BET = "bet";
    private static final String PARAM_LOT_ID = "lotId";
    private static final String ATTR_USER = "user";
    private static final String ATTR_ERROR = "error";
    private UserBetService userBetService;
    private FlowerLotService flowerLotService;

    public MakeBetCommand(UserBetService userBetService, FlowerLotService flowerLotService) {
        this.userBetService = userBetService;
        this.flowerLotService = flowerLotService;
    }

    /**
     * Makes bets if user and bet exists.
     *
     * @return String with url to forwarding
     */
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {

        String lotIdValue = request.getParameter(PARAM_LOT_ID);
        Long lotId = Long.valueOf(lotIdValue);
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute(ATTR_USER);
        String bet = request.getParameter(PARAM_BET);
        if (bet != null && !bet.isEmpty() && user != null) {
            try {
                makeBet(lotId, user, bet);
            } catch (ServiceException e) {
                LOGGER.error("Error with saving user bet", e);
                request.setAttribute(ATTR_ERROR, true);
            }
        }
        return Pages.SHOW_LOT_INFO_PAGE + lotId;
    }

    /**
     * Makes user bet to lot.
     */
    private void makeBet(Long lotId, User user, String bet) throws ServiceException {
        Double betToLong = Double.parseDouble(bet);
        BigDecimal betValue = BigDecimal.valueOf(betToLong);

        FlowerLot flowerLot = flowerLotService.findById(lotId);
        UserBet userBet = new UserBet(0L, flowerLot, user, betValue);
        userBetService.save(userBet);


    }
}

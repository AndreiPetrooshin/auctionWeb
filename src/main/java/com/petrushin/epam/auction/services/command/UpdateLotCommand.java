package com.petrushin.epam.auction.services.command;

import com.petrushin.epam.auction.constants.Pages;
import com.petrushin.epam.auction.exceptions.EntityDAOException;
import com.petrushin.epam.auction.services.domain.FlowerLot;
import com.petrushin.epam.auction.services.domain.Payment;
import com.petrushin.epam.auction.services.domain.User;
import com.petrushin.epam.auction.services.domain.UserBet;
import com.petrushin.epam.auction.services.FlowerLotService;
import com.petrushin.epam.auction.services.PaymentService;
import com.petrushin.epam.auction.services.UserBetService;
import com.petrushin.epam.auction.services.UserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.math.BigDecimal;

/**
 * Class implements {@link Command} and handles the
 * lot updating.
 *
 * @author Andrei Petrushin
 * @version 1.0.0
 */
public class UpdateLotCommand implements Command {

    private static final String PARAM_USER_ID = "userId";
    private static final Logger LOGGER = LogManager.getLogger(UpdateLotCommand.class);
    private static final String ATTR_USER = "user";
    private static final String ATTR_ERROR = "error";
    private static final String PARAM_LOT_ID = "lotId";
    private static final String PARAM_TYPE = "type";
    private static final String PARAM_NAME = "name";
    private static final String PARAM_DESCRIPTION = "description";
    private static final String PARAM_START_PRICE = "startPrice";
    private static final String PARAM_STATE = "state";
    private static final String STATE_SOLD = "sold";

    private FlowerLotService lotService;
    private UserService userService;
    private PaymentService paymentService;
    private UserBetService betService;


    public UpdateLotCommand(FlowerLotService lotService, UserService userService,
                            PaymentService paymentService, UserBetService betService) {
        this.lotService = lotService;
        this.userService = userService;
        this.paymentService = paymentService;
        this.betService = betService;
    }

    /**
     * Updates lot from DB
     *
     * @return String with url to forwarding
     */
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        try {
            FlowerLot lot = createLot(request);
            lotService.update(lot);
        } catch (EntityDAOException e) {
            LOGGER.error("Error with lot updating");
            request.setAttribute(ATTR_ERROR, true);
        }
        return Pages.PROFILE_ABOUT_MYSELF_PAGE;
    }

    /**
     * Create lot by parameters from request
     *
     * @return {@link FlowerLot}
     */
    private FlowerLot createLot(HttpServletRequest request) throws EntityDAOException {
        String lotIdValue = request.getParameter(PARAM_LOT_ID);
        Long lotId = null;
        if (lotIdValue != null) {
            lotId = Long.valueOf(lotIdValue);
        }
        String lotType = request.getParameter(PARAM_TYPE);
        String lotName = request.getParameter(PARAM_NAME);
        String lotDescription = request.getParameter(PARAM_DESCRIPTION);
        String lotStartPriceValue = request.getParameter(PARAM_START_PRICE);
        BigDecimal lostStartPrice = new BigDecimal(0L);
        if (lotStartPriceValue != null && !lotStartPriceValue.isEmpty()) {
            Double valueOf = Double.valueOf(lotStartPriceValue);
            lostStartPrice = BigDecimal.valueOf(valueOf);
        }
        String lotState = request.getParameter(PARAM_STATE);

        User user = getUserFromRequest(request);
        FlowerLot flowerLot = new FlowerLot(lotId, user, lotType, lotName, lotDescription, lostStartPrice, lotState);
        if (lotState.equals(STATE_SOLD)) {
            createPaymentForWinner(flowerLot);
        }
        return flowerLot;
    }


    private void createPaymentForWinner(FlowerLot flowerLot) throws EntityDAOException {
        Long id = flowerLot.getId();
        UserBet userBet = betService.getLastBetToLot(id);
        BigDecimal lastBet = userBet.getBet();
        User user = userBet.getUser();
        Payment payment = new Payment(0L, flowerLot, user, lastBet, false);
        paymentService.save(payment);

    }

    private User getUserFromRequest(HttpServletRequest request) {
        String userIdValue = request.getParameter(PARAM_USER_ID);
        User user = null;
        if (userIdValue != null && !userIdValue.isEmpty()) {
            Long userId = Long.valueOf(userIdValue);
            try {
                user = userService.findById(userId);
            } catch (EntityDAOException e) {
                LOGGER.error("Error with finding user by id", e);
                request.setAttribute(ATTR_ERROR, true);
            }
        } else {
            HttpSession session = request.getSession();
            user = (User) session.getAttribute(ATTR_USER);
        }
        return user;
    }
}

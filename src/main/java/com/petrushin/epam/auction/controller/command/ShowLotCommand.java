package com.petrushin.epam.auction.controller.command;

import com.petrushin.epam.auction.constants.Pages;
import com.petrushin.epam.auction.exceptions.EntityDAOException;
import com.petrushin.epam.auction.domain.FlowerLot;
import com.petrushin.epam.auction.domain.UserBet;
import com.petrushin.epam.auction.services.FlowerLotService;
import com.petrushin.epam.auction.services.UserBetService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * Class implements {@link Command} and handles the
 * show lot command.
 *
 * @author Andrei Petrushin
 * @version 1.0.0
 */
public class ShowLotCommand implements Command {

    private static final Logger LOGGER = LogManager.getLogger(ShowLotCommand.class);

    private static final String PARAM_ID = "id";
    private static final String ATTR_LOT = "lot";
    private static final String ATTR_BETS = "bets";
    private static final String ATTR_ERROR = "error";

    private FlowerLotService flowerLotService;
    private UserBetService userBetService;

    public ShowLotCommand(FlowerLotService flowerLotService, UserBetService userBetService) {
        this.flowerLotService = flowerLotService;
        this.userBetService = userBetService;
    }

    /**
     * Show information about lot
     *
     * @return String with url to forwarding
     */
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        String idValue = request.getParameter(PARAM_ID);
        if(idValue!=null && !idValue.isEmpty()){
            Long id = Long.valueOf(idValue);
            setLotInfo(request, id);
        }
        return Pages.LOT_PAGE;
    }

    /**
     * Sets lot info into request
     */
    private void setLotInfo(HttpServletRequest request, Long id) {
        try {
            FlowerLot lot = flowerLotService.findById(id);
            if (lot != null) {
                List<UserBet> userBetList = userBetService.getByLotId(id);
                request.setAttribute(ATTR_BETS, userBetList);
                request.setAttribute(ATTR_LOT, lot);
            }
        } catch (EntityDAOException e) {
            LOGGER.error("Error with find lot by id", e);
            request.setAttribute(ATTR_ERROR, true);
        }
    }
}

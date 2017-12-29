package com.petrushin.epam.auction.model.command;

import com.petrushin.epam.auction.constants.Pages;
import com.petrushin.epam.auction.exceptions.EntityDAOException;
import com.petrushin.epam.auction.model.domain.UserBet;
import com.petrushin.epam.auction.model.domain.FlowerLot;
import com.petrushin.epam.auction.services.FlowerLotService;
import com.petrushin.epam.auction.services.UserBetService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class ShowLotCommand implements Command {

    private static final Logger LOGGER = LogManager.getLogger(ShowLotCommand.class);

    private static final String PARAM_ID = "id";
    private static final String ATTR_LOT = "lot";
    private static final String ATTR_BETS = "bets";

    private FlowerLotService flowerLotService;
    private UserBetService userBetService;

    public ShowLotCommand(FlowerLotService flowerLotService, UserBetService userBetService) {
        this.flowerLotService = flowerLotService;
        this.userBetService = userBetService;
    }



    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        String param = request.getParameter(PARAM_ID);
        Long id = Long.valueOf(param);
        setLotInfo(request, id);
        return Pages.LOT_PAGE;
    }

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
        }
    }
}

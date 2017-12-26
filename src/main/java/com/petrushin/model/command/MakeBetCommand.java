package com.petrushin.model.command;

import com.petrushin.constants.Pages;
import com.petrushin.model.domain.FlowerLot;
import com.petrushin.model.domain.User;
import com.petrushin.model.domain.UserBet;
import com.petrushin.exceptions.EntityDAOException;
import com.petrushin.services.FlowerLotService;
import com.petrushin.services.UserBetService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.math.BigDecimal;

public class MakeBetCommand implements Command {

    private static final String PARAM_BET = "bet";
    private static final String PARAM_LOT_ID = "lotId";
    private static final String ATTR_USER = "user";
    private UserBetService userBetService;
    private FlowerLotService flowerLotService;

    public MakeBetCommand(UserBetService userBetService, FlowerLotService flowerLotService) {
        this.userBetService = userBetService;
        this.flowerLotService = flowerLotService;
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {

        String bet = request.getParameter(PARAM_BET);
        String lotIdValue = request.getParameter(PARAM_LOT_ID);
        Long lotId = Long.valueOf(lotIdValue);
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute(ATTR_USER);
        if (bet != null && user != null) {
            Double betToLong = Double.parseDouble(bet);
            BigDecimal betValue = BigDecimal.valueOf(betToLong);
            try {
                FlowerLot flowerLot = flowerLotService.findById(lotId);
                UserBet userBet = new UserBet(0L, flowerLot, user, betValue);
                userBetService.save(userBet);
            } catch (EntityDAOException e) {
                e.printStackTrace();
            }
        }
        return Pages.HOME_PAGE;
    }
}

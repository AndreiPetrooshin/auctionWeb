package com.petrushin.model.command;

import com.petrushin.constants.Pages;
import com.petrushin.exceptions.EntityDAOException;
import com.petrushin.model.domain.FlowerLot;
import com.petrushin.model.domain.User;
import com.petrushin.services.FlowerLotService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.math.BigDecimal;

public class UpdateCommand implements Command {

    private static final String ATTR_USER = "user";
    private static final String PARAM_LOT_ID = "id";
    private static final String PARAM_TYPE = "type";
    private static final String PARAM_NAME = "name";
    private static final String PARAM_DESCRIPTION = "description";
    private static final String PARAM_START_PRICE = "startPrice";
    private static final String PARAM_STATE = "state";
    private FlowerLotService lotService;


    public UpdateCommand(FlowerLotService lotService) {
        this.lotService = lotService;
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
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
        if (!lotStartPriceValue.isEmpty()) {
            lostStartPrice = BigDecimal.valueOf(Double.valueOf(lotStartPriceValue));
        }
        String lotState = request.getParameter(PARAM_STATE);

        HttpSession session = request.getSession();
        User user = (User) session.getAttribute(ATTR_USER);

        FlowerLot lot = new FlowerLot(lotId,user,lotType,lotName,lotDescription,lostStartPrice,lotState);
        try {
            lotService.update(lot);
        } catch (EntityDAOException e) {
            e.printStackTrace();
        }
        return Pages.PROFILE_ABOUT_MYSELF_PAGE;
    }
}

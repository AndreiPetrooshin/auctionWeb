package com.petrushin.epam.auction.model.command;

import com.petrushin.epam.auction.constants.Pages;
import com.petrushin.epam.auction.exceptions.EntityDAOException;
import com.petrushin.epam.auction.model.domain.FlowerLot;
import com.petrushin.epam.auction.services.FlowerLotService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class ShowLotsCommand implements Command {

    private static final String ATTR_LOT_LIST = "lotList";
    private static final String PARAM_TYPE = "type";
    private static final String PARAM_TRADING = "trading";
    private FlowerLotService flowerLotService;

    public ShowLotsCommand(FlowerLotService flowerLotService) {
        this.flowerLotService = flowerLotService;
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {

        String type = request.getParameter(PARAM_TYPE);

        try {
            List<FlowerLot> lotList;
            if(type == null) {
                lotList = flowerLotService.getByState(PARAM_TRADING);
            } else {
                lotList = flowerLotService.getByTypeAndState(type, PARAM_TRADING);
            }

            if(lotList.size()> 10) {
                lotList =  lotList.subList(0,10);
                request.setAttribute(ATTR_LOT_LIST, lotList);
            } else {
                request.setAttribute(ATTR_LOT_LIST, lotList);
            }
        } catch (EntityDAOException e) {
            e.printStackTrace();
        }

        return Pages.HOME_PAGE;
    }
}

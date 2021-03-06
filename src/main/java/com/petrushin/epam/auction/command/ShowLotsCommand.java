package com.petrushin.epam.auction.command;

import com.petrushin.epam.auction.constants.Pages;
import com.petrushin.epam.auction.domain.FlowerLot;
import com.petrushin.epam.auction.domain.dto.FlowerLotDto;
import com.petrushin.epam.auction.exceptions.EntityDAOException;
import com.petrushin.epam.auction.services.FlowerLotService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

/**
 * Class implements {@link Command} and handles the
 * show lots command.
 *
 * @author Andrei Petrushin
 * @version 1.0.0
 */
public class ShowLotsCommand implements Command {

    private static final Logger LOGGER = LogManager.getLogger(ShowLotsCommand.class);

    private static final String ATTR_LOT_LIST = "lotList";
    private static final String ATTR_ERROR = "error";
    private static final String PARAM_TYPE = "type";
    private static final String PARAM_TRADING = "trading";

    private FlowerLotService flowerLotService;


    public ShowLotsCommand(FlowerLotService flowerLotService) {
        this.flowerLotService = flowerLotService;
    }

    /**
     * Show information about lots
     *
     * @return String with url to forwarding
     */
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {

        String type = request.getParameter(PARAM_TYPE);

        try {
            List<FlowerLot> lotList;
            if (type == null) {
                lotList = flowerLotService.getByState(PARAM_TRADING);
            } else {
                lotList = flowerLotService.getByTypeAndState(type, PARAM_TRADING);
            }
            List<FlowerLotDto> dtoList = convertLotListToDtoList(lotList);
            request.setAttribute(ATTR_LOT_LIST, dtoList);
        } catch (EntityDAOException e) {
            LOGGER.error("Error with showing lots", e);
            request.setAttribute(ATTR_ERROR, true);
        }

        return Pages.HOME_PAGE;
    }

    /**
     * Converts List of FlowerLots to FlowerLotsDto List
     * */
    private List<FlowerLotDto> convertLotListToDtoList(List<FlowerLot> lotList) {
        List<FlowerLotDto> dtoList = new ArrayList<>();
        for (FlowerLot lot: lotList) {
            dtoList.add(new FlowerLotDto(lot));
        }
        return dtoList;
    }
}

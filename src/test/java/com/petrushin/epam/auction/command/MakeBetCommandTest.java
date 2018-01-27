package com.petrushin.epam.auction.command;

import com.petrushin.epam.auction.command.Command;
import com.petrushin.epam.auction.command.MakeBetCommand;
import com.petrushin.epam.auction.constants.Pages;
import com.petrushin.epam.auction.domain.FlowerLot;
import com.petrushin.epam.auction.domain.User;
import com.petrushin.epam.auction.domain.UserBet;
import com.petrushin.epam.auction.exceptions.EntityDAOException;
import com.petrushin.epam.auction.services.FlowerLotService;
import com.petrushin.epam.auction.services.UserBetService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.math.BigDecimal;

import static org.junit.Assert.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class MakeBetCommandTest {

    @Mock
    private UserBetService userBetService;
    @Mock
    private FlowerLotService flowerLotService;
    @InjectMocks
    private Command command = new MakeBetCommand(userBetService, flowerLotService);
    @Mock
    private HttpServletRequest request;
    @Mock
    private HttpServletResponse response;
    @Mock
    private HttpSession session;
    @Mock
    private FlowerLot flowerLot;
    @Mock

    private static User user;
    private static final String LOT_ID = "8";
    private static final String BET = "2334.0";
    private static final BigDecimal BET_VALUE = new BigDecimal(BET);

    private static final String PARAM_BET = "bet";
    private static final String PARAM_LOT_ID = "lotId";
    private static final String ATTR_USER = "user";

    @Before
    public void before() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void execute() throws EntityDAOException {
        when(request.getParameter(PARAM_LOT_ID)).thenReturn(LOT_ID);
        when(request.getSession()).thenReturn(session);
        when(session.getAttribute(ATTR_USER)).thenReturn(user);
        when(request.getParameter(PARAM_BET)).thenReturn(BET);
        when(flowerLotService.findById(8L)).thenReturn(flowerLot);

        UserBet userBet = new UserBet(0L, flowerLot, user, BET_VALUE);

        String result = command.execute(request, response);

        verify(request).getParameter(PARAM_LOT_ID);
        verify(request).getSession();
        verify(session).getAttribute(ATTR_USER);
        verify(request).getParameter(PARAM_BET);
        verify(flowerLotService).findById(8L);
        verify(userBetService).save(userBet);

        assertEquals(Pages.SHOW_LOT_INFO_PAGE+LOT_ID, result);
    }
}
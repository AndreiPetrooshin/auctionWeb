package com.petrushin.epam.auction.command;

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
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

public class ShowLotCommandTest {

    private static final String ID = "id";
    private static final String ANY_ID_VALUE = "8";
    private static final Long ANY_ID = 8l;
    private static final String BETS = "bets";
    private static final String LOT = "lot";
    private static final List<UserBet> bets = new ArrayList<>();

    @Mock
    private HttpServletRequest request;
    @Mock
    private HttpServletResponse response;
    @Mock
    private FlowerLotService lotService;
    @Mock
    private UserBetService userBetService;
    @Mock
    private FlowerLot lot;
    @Mock
    private User user;

    @InjectMocks
    private ShowLotCommand command = new ShowLotCommand(lotService, userBetService);

    @Before
    public void before() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void shouldSetLotsToRequestIfParamIdIsExist() throws EntityDAOException {
        when(request.getParameter(ID)).thenReturn(ANY_ID_VALUE);
        when(lotService.findById(ANY_ID)).thenReturn(lot);
        when(lot.getUser()).thenReturn(user);
        when(userBetService.getByLotId(ANY_ID)).thenReturn(bets);

        String result =  command.execute(request, response);

        verify(request).getParameter(ID);
        verify(request).setAttribute(eq(BETS), any());
        verify(request).setAttribute(eq(LOT), any());
        verify(lotService).findById(anyLong());
        verify(userBetService).getByLotId(anyLong());
        verifyNoMoreInteractions(request, userBetService);

        assertEquals(Pages.LOT_PAGE, result);
    }


}
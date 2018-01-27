package com.petrushin.epam.auction.command;

import com.petrushin.epam.auction.command.Command;
import com.petrushin.epam.auction.command.ProfileCommand;
import com.petrushin.epam.auction.constants.Pages;
import com.petrushin.epam.auction.domain.*;
import com.petrushin.epam.auction.services.FlowerLotService;
import com.petrushin.epam.auction.services.PaymentService;
import com.petrushin.epam.auction.services.UserAddressesService;
import com.petrushin.epam.auction.services.UserCardService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class ProfileCommandTest {
    private static final String PARAM_LOTS = "lots";
    private static final String PARAM_CARDS = "cards";
    private static final String PARAM_ABOUT = "about";
    private static final String PARAM_MYSELF = "myself";
    private static final String PARAM_ADDRESSES = "address";
    private static final String PARAM_WINNINGS = "winnings";
    private static final String ATTR_USER = "user";
    private static final String ATTR_MYSELF = "about_myself";
    private static final String ATTR_ABOUT_LOTS = "about_lots";
    private static final String ATTR_ABOUT_CARDS = "about_cards";
    private static final String ATTR_ABOUT_ADDRESSES = "about_addresses";
    private static final String ATTR_ABOUT_WINS = "about_wins";
    private static final String ATTR_WINS = "winnings";
    private static final String ATTR_CARDS = "cards";
    private static final String ATTR_PAYMENTS = "payments";


    @Mock
    private HttpServletRequest request;
    @Mock
    private HttpServletResponse response;
    @Mock
    private UserCardService cardService;
    @Mock
    private FlowerLotService lotService;
    @Mock
    private UserAddressesService addressesService;
    @Mock
    private PaymentService paymentService;
    @InjectMocks
    private Command command = new ProfileCommand(cardService, lotService, addressesService, paymentService);
    @Mock
    private HttpSession session;
    @Mock
    private User user;
    @Mock
    private List<UserCard> cards;
    @Mock
    private UserAddress address;

    private List<Payment> winnings = new ArrayList<>();
    private List<FlowerLot> lots = Arrays.asList(new FlowerLot(), new FlowerLot());
    private List<Payment> payments = new ArrayList<>();

    @Before
    public void before() {
        MockitoAnnotations.initMocks(this);
        when(request.getSession()).thenReturn(session);
        when(session.getAttribute(ATTR_USER)).thenReturn(user);
        when(user.getId()).thenReturn(2L);
    }

    @Test
    public void shouldExecuteWithParamMyself() {
        when(request.getParameter(PARAM_ABOUT)).thenReturn(PARAM_MYSELF);

        String resultUrl = command.execute(request, response);

        verify(request).getParameter(PARAM_ABOUT);
        verify(request).getSession();
        verify(request).setAttribute(ATTR_MYSELF, true);

        assertEquals(Pages.PROFILE_PAGE, resultUrl);
    }

    @Test
    public void shouldExecuteWithParamCards() throws Exception {
        when(request.getParameter(PARAM_ABOUT)).thenReturn(PARAM_CARDS);
        when(cardService.getByUserId(user.getId())).thenReturn(cards);

        command.execute(request, response);

        verify(request).getParameter(PARAM_ABOUT);
        verify(request).getSession();
        verify(session).getAttribute(ATTR_USER);
        verify(cardService).getByUserId(user.getId());
        verify(request).setAttribute(ATTR_ABOUT_CARDS, true);
        verify(request).setAttribute(PARAM_CARDS, cards);
    }

    @Test
    public void shouldExecuteWithParamLots() throws Exception {
        when(request.getParameter(PARAM_ABOUT)).thenReturn(PARAM_LOTS);
        when(lotService.getByUserId(user.getId())).thenReturn(lots);

        command.execute(request, response);

        verify(request).setAttribute(PARAM_LOTS, lots);
        verify(request).setAttribute(ATTR_PAYMENTS, payments);
        verify(request).setAttribute(ATTR_ABOUT_LOTS, true);
    }

    @Test
    public void shouldExecuteWithParamAddress() throws Exception {
        when(request.getParameter(PARAM_ABOUT)).thenReturn(PARAM_ADDRESSES);
        when(addressesService.getByUserId(user.getId())).thenReturn(address);

        command.execute(request, response);

        verify(request).setAttribute(PARAM_ADDRESSES, address);
        verify(request).setAttribute(ATTR_ABOUT_ADDRESSES, true);
    }

    @Test
    public void shouldExecuteWithParamWins() throws Exception {
        when(request.getParameter(PARAM_ABOUT)).thenReturn(PARAM_WINNINGS);
        when(paymentService.getByUserId(user.getId())).thenReturn(payments);
        when(cardService.getByUserId(user.getId())).thenReturn(cards);

        command.execute(request, response);

        verify(session).getAttribute(ATTR_USER);
        verify(request).setAttribute(ATTR_CARDS, cards);
        verify(request).setAttribute(ATTR_WINS, winnings);
        verify(request).setAttribute(ATTR_ABOUT_WINS, true);
    }
}
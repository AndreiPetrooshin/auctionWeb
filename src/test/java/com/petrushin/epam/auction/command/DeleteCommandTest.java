package com.petrushin.epam.auction.command;

import com.petrushin.epam.auction.constants.Pages;
import com.petrushin.epam.auction.exceptions.EntityDAOException;
import com.petrushin.epam.auction.services.FlowerLotService;
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

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

public class DeleteCommandTest {

    private static final String SOME_NUMBER = "5";
    private static final String LOT = "lot";
    public static final String ADDRESS = "address";
    public static final String CARD = "card";

    @Mock
    private FlowerLotService lotService;

    @Mock
    private UserCardService cardService;

    @Mock
    private UserAddressesService addressesService;

    @Mock
    private HttpServletRequest request;

    @Mock
    private HttpServletResponse response;

    @Mock
    private HttpSession httpSession;

    @InjectMocks
    private DeleteCommand deleteCommand = new DeleteCommand(lotService, cardService, addressesService);

    @Before
    public void before() {
        MockitoAnnotations.initMocks(this);
        when(request.getSession()).thenReturn(httpSession);
    }

    @Test
    public void shouldReturnNeededPageWhenParameterIsLot() throws EntityDAOException {
        when(request.getParameter(anyString())).thenReturn(LOT).thenReturn(SOME_NUMBER);
        String result = deleteCommand.execute(request, response);

        verify(lotService, times(1)).delete(anyLong());
        assertEquals(Pages.PROFILE_ABOUT_MYSELF_PAGE, result);
    }

    @Test
    public void shouldReturnNeededPageWhenParameterIsCard() throws EntityDAOException {
        when(request.getParameter(anyString())).thenReturn(CARD).thenReturn(SOME_NUMBER);
        String result = deleteCommand.execute(request, response);

        verify(cardService, times(1)).delete(anyLong());
        assertEquals(Pages.PROFILE_ABOUT_CARDS, result);
    }

    @Test
    public void shouldReturnNeededPageWhenParameterIsAddress() throws EntityDAOException {
        when(request.getParameter(anyString())).thenReturn(ADDRESS).thenReturn(SOME_NUMBER);
        String result = deleteCommand.execute(request, response);

        verify(addressesService, times(1)).delete(anyLong());
        assertEquals(Pages.PROFILE_ABOUT_MYSELF_PAGE, result);
    }




}

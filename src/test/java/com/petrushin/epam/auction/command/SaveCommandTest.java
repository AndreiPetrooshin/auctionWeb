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
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;


public class SaveCommandTest {

    private static final String PARAM_ELEMENT = "element";
    private static final String LOT = "lot";
    private static final String CARD = "card";
    private static final String ADDRESS = "address";

    @Mock
    private UserAddressesService addressesService;

    @Mock
    private FlowerLotService lotService;

    @Mock
    private UserCardService cardService;

    @Mock
    private HttpServletRequest request;

    @Mock
    private HttpServletResponse response;

    @Mock
    private HttpSession httpSession;

    @InjectMocks
    private SaveCommand saveCommand = new SaveCommand(lotService, cardService, addressesService);

    @Before
    public void before() {
        MockitoAnnotations.initMocks(this);
        when(request.getSession()).thenReturn(httpSession);
    }

    @Test
    public void shouldSaveLotWhenRequestParameterIsLot() throws EntityDAOException {
        when(request.getParameter(PARAM_ELEMENT)).thenReturn(LOT);
        String result = saveCommand.execute(request,response);

        verify(lotService).save(any());
        verifyNoMoreInteractions(lotService);
        assertEquals(Pages.PROFILE_PAGE, result);
    }

    @Test
    public void shouldSaveLotWhenRequestParameterIsCard() throws EntityDAOException {
        when(request.getParameter(PARAM_ELEMENT)).thenReturn(CARD);

        String result = saveCommand.execute(request,response);

        verify(cardService).save(any());
        verifyNoMoreInteractions(cardService);
        assertEquals(Pages.PROFILE_PAGE, result);
    }

    @Test
    public void shouldSaveLotWhenRequestParameterIsAddress() throws EntityDAOException {
        when(request.getParameter(PARAM_ELEMENT)).thenReturn(ADDRESS);
        String result = saveCommand.execute(request,response);

        verify(addressesService).save(any());
        verifyNoMoreInteractions(addressesService);
        assertEquals(Pages.PROFILE_PAGE, result);
    }




}
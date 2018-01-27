package com.petrushin.epam.auction.command;

import com.petrushin.epam.auction.command.WinnerAddressCommand;
import com.petrushin.epam.auction.constants.Pages;
import com.petrushin.epam.auction.domain.UserAddress;
import com.petrushin.epam.auction.exceptions.EntityDAOException;
import com.petrushin.epam.auction.services.UserAddressesService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

public class WinnerAddressCommandTest {

    private static final String PARAM_USER_ID = "userId";
    private static final String ATTR_ADDRESS = "address";
    private static final String ANY_ID_VALUE = "6";
    private static final long ANY_ID = 6L;

    @Mock
    private HttpServletRequest request;

    @Mock
    private HttpServletResponse response;

    @Mock
    private UserAddressesService addressesService;

    @Mock
    private UserAddress userAddress;

    @InjectMocks
    private WinnerAddressCommand command = new WinnerAddressCommand(addressesService);

    @Before
    public void before() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void shouldAddUserAddressToRequestIfUserIdExist() throws EntityDAOException {
        when(request.getParameter(PARAM_USER_ID)).thenReturn(ANY_ID_VALUE);
        when(addressesService.getByUserId(ANY_ID)).thenReturn(userAddress);

        String result = command.execute(request, response);

        verify(request).getParameter(PARAM_USER_ID);
        verify(addressesService).getByUserId(ANY_ID);
        verify(request).setAttribute(ATTR_ADDRESS, userAddress);
        verifyNoMoreInteractions(request, userAddress);

        assertEquals(result, Pages.SEND_LOT_PAGE);
    }


}
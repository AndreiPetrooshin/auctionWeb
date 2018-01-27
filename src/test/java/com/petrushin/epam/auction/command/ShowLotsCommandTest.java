package com.petrushin.epam.auction.command;

import com.petrushin.epam.auction.command.ShowLotsCommand;
import com.petrushin.epam.auction.domain.FlowerLot;
import com.petrushin.epam.auction.exceptions.EntityDAOException;
import com.petrushin.epam.auction.services.FlowerLotService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

public class ShowLotsCommandTest {

    private static final List<FlowerLot> lotList = new ArrayList<>();
    private static final String TYPE = "type";
    private static final String ATTR_LOT_LIST = "lotList";
    private static final String PARAM_TRADING = "trading";
    private static final String MEADOW_FLOWERS = "луговые";

    @Mock
    private HttpServletRequest request;
    @Mock
    private HttpServletResponse response;
    @Mock
    private FlowerLotService lotService;
    @InjectMocks
    private ShowLotsCommand command = new ShowLotsCommand(lotService);

    @Before
    public void before() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void shouldAddToRequestListOfFlowersByStateIfTypeNotExist() throws EntityDAOException {
        when(lotService.getByState(anyString())).thenReturn(lotList);

        command.execute(request,response);

        verify(lotService).getByState(anyString());
        verify(request).setAttribute(ATTR_LOT_LIST,lotList);
        verifyNoMoreInteractions(lotService);

    }


    @Test
    public void shouldAddToRequestListOfFlowersByStateAndTypeIfTypeExist() throws EntityDAOException {
        when(request.getParameter(TYPE)).thenReturn(MEADOW_FLOWERS);
        when(lotService.getByTypeAndState(MEADOW_FLOWERS,PARAM_TRADING)).thenReturn(lotList);

        command.execute(request,response);

        verify(lotService).getByTypeAndState(MEADOW_FLOWERS, PARAM_TRADING);
        verify(request).setAttribute(ATTR_LOT_LIST,lotList);

    }



}
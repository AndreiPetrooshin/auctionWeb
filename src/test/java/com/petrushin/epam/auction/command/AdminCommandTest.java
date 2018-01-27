package com.petrushin.epam.auction.command;

import com.petrushin.epam.auction.constants.Pages;
import com.petrushin.epam.auction.domain.FlowerLot;
import com.petrushin.epam.auction.exceptions.EntityDAOException;
import com.petrushin.epam.auction.services.FlowerLotService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class AdminCommandTest {


    @Mock
    private FlowerLotService lotService;

    @InjectMocks
    private AdminCommand adminCommand = new AdminCommand(lotService);

    @Mock
    private HttpServletRequest request;

    @Mock
    private HttpServletResponse response;

    @Mock
    private List<FlowerLot> lots;


    @Test
    public void shouldReturnAdminPage() throws EntityDAOException {
        when(lotService.getAll()).thenReturn(lots);

        String result = adminCommand.execute(request, response);

        assertEquals(Pages.ADMIN_PAGE, result);
    }
}
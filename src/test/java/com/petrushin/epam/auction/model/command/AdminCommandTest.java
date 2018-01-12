package com.petrushin.epam.auction.model.command;

import com.petrushin.epam.auction.constants.Pages;
import com.petrushin.epam.auction.exceptions.ServiceException;
import com.petrushin.epam.auction.services.FlowerLotService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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


    @Test
    public void shouldReturnAdminPage() throws ServiceException {
        AdminCommand command = mock(AdminCommand.class);
        when(command.execute(request,response)).thenReturn(Pages.ADMIN_PAGE);

        assertEquals(Pages.ADMIN_PAGE, command.execute(request,response));
    }

    @Test
    public void shouldCallGetAllFromFlowerLotServiceWhenExecute() throws ServiceException {
        adminCommand.execute(request,response);

        verify(lotService).getAll();
    }

    @Test
    public void shouldSetAttributeToRequestWhenExecute() {
        adminCommand.execute(request,response);

        verify(request).setAttribute(anyString(),any());
    }
}
package com.petrushin.epam.auction.services.command;

import com.petrushin.epam.auction.constants.Pages;
import com.petrushin.epam.auction.controller.command.PayCommand;
import com.petrushin.epam.auction.exceptions.EntityDAOException;
import com.petrushin.epam.auction.domain.Payment;
import com.petrushin.epam.auction.services.PaymentService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class PayCommandTest {

    @Mock
    private HttpServletResponse response;

    @Mock
    private HttpServletRequest request;

    @Mock
    private Payment payment;

    @Mock
    private PaymentService paymentService;

    @InjectMocks
    private PayCommand payCommand = new PayCommand(paymentService) ;


    @Test
    public void shouldCallsCorrectPaymentServiceMethods() throws EntityDAOException {
        when(request.getParameter(anyString())).thenReturn("2");
        when(paymentService.findById(anyLong())).thenReturn(payment);
        payCommand.execute(request,response);

        verify(paymentService).findById(anyLong());
        verify(paymentService).update(any(Payment.class));

    }

    @Test
    public void shouldReturnProfilePage() throws EntityDAOException {
        String result = payCommand.execute(request,response);
        assertEquals(Pages.PROFILE_PAGE, result);
    }
}
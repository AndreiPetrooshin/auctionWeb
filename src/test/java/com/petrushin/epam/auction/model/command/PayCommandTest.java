package com.petrushin.epam.auction.model.command;

import com.petrushin.epam.auction.constants.Pages;
import com.petrushin.epam.auction.exceptions.ServiceException;
import com.petrushin.epam.auction.model.domain.Payment;
import com.petrushin.epam.auction.services.PaymentService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.doNothing;
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
    public void shouldCallsCorrectPaymentServiceMethods() throws ServiceException {
        when(request.getParameter(anyString())).thenReturn("2");
        when(paymentService.findById(anyLong())).thenReturn(payment);
        payCommand.execute(request,response);

        verify(paymentService).findById(anyLong());
        verify(paymentService).update(any(Payment.class));

    }

    @Test
    public void shouldReturnProfilePage() throws ServiceException {
        String result = payCommand.execute(request,response);
        assertEquals(Pages.PROFILE_PAGE, result);
    }
}
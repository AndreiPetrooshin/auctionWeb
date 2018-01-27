package com.petrushin.epam.auction.command;

import com.petrushin.epam.auction.constants.Pages;
import com.petrushin.epam.auction.command.PayCommand;
import com.petrushin.epam.auction.exceptions.EntityDAOException;
import com.petrushin.epam.auction.domain.Payment;
import com.petrushin.epam.auction.services.PaymentService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

public class PayCommandTest {

    private static final String SOME_NUMBER = "2";

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

    @Before
    public void before() {
        MockitoAnnotations.initMocks(this);
        when(request.getParameter(anyString())).thenReturn(SOME_NUMBER);
    }


    @Test
    public void shouldUpdatePaymentIfPaymentExist() throws EntityDAOException {
        when(paymentService.findById(anyLong())).thenReturn(payment);

        payCommand.execute(request,response);

        verify(paymentService).findById(anyLong());
        verify(paymentService).update(payment);
        verifyNoMoreInteractions(paymentService);

    }

    @Test
    public void shouldNotUpdatePaymentIfPaymentNotExist() throws EntityDAOException {
        payCommand.execute(request,response);

        verify(paymentService).findById(anyLong());
        verifyNoMoreInteractions(paymentService);
    }


    @Test
    public void shouldReturnProfilePage() throws EntityDAOException {
        String result = payCommand.execute(request,response);
        assertEquals(Pages.PROFILE_PAGE, result);
    }
}
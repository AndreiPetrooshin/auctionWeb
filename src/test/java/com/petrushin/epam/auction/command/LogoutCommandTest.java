package com.petrushin.epam.auction.command;

import com.petrushin.epam.auction.constants.Pages;
import com.petrushin.epam.auction.command.LogoutCommand;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class LogoutCommandTest {

    private static final String ATTR_USER = "user";

    @Mock
    private HttpServletResponse response;

    @Mock
    private HttpServletRequest request;

    @Mock
    private HttpSession session;

    private static LogoutCommand logoutCommand = new LogoutCommand();

    @Test
    public void shouldCallRemoveAttributeIfSessionExistAndReturnsStartPage() {
        when(request.getSession()).thenReturn(session);

        String result = logoutCommand.execute(request,response);

        verify(session).removeAttribute(ATTR_USER);
        verifyNoMoreInteractions(session);

        assertEquals(Pages.START_PAGE, result);
    }

    @Test
    public void shouldReturnStartPage() {
        String result = logoutCommand.execute(request,response);
        assertEquals(Pages.START_PAGE, result);
    }
}
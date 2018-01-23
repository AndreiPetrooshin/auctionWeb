package com.petrushin.epam.auction.services.command;

import com.petrushin.epam.auction.constants.Pages;
import com.petrushin.epam.auction.controller.command.LoginCommand;
import com.petrushin.epam.auction.services.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


import static org.mockito.ArgumentMatchers.anyString;
import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class LoginCommandTest {

    @Mock
    private HttpServletResponse response;

    @Mock
    private HttpServletRequest request;

    @Mock
    private HttpSession session;

    @Mock
    private UserService  userService;

    @InjectMocks
    private LoginCommand loginCommand = new LoginCommand(userService);


    @Test
    public void shouldReturnNullIfUserExist() {
        when(request.getSession()).thenReturn(session);
        when(session.getAttribute(anyString())).thenReturn(new Object());

        assertNull(loginCommand.execute(request,response));
    }

    @Test
    public void shouldReturnLoginPageIfUserDoesNotExist() {
        when(request.getSession()).thenReturn(session);
        when(session.getAttribute(anyString())).thenReturn(null);

        String result = loginCommand.execute(request, response);
        assertEquals(Pages.LOGIN_PAGE, result);
    }


}
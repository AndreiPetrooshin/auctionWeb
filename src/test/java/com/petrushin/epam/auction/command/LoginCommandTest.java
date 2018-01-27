package com.petrushin.epam.auction.command;

import com.petrushin.epam.auction.command.LoginCommand;
import com.petrushin.epam.auction.constants.Pages;
import com.petrushin.epam.auction.domain.User;
import com.petrushin.epam.auction.exceptions.EntityDAOException;
import com.petrushin.epam.auction.services.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class LoginCommandTest {

   private static final String SOME_LOGIN = "dima";
   private static final String USER = "user";
   private static final String PARAM_LOGIN = "login";
   private static final String PARAM_PASSWORD = "password";
   private static final String SOME_PASSWORD = "123";
   private static final String MD5_PASSWORD = "202cb962ac59075b964b07152d234b70";

    @Mock
    private UserService userService;

    @Mock
    private HttpServletRequest request;

    @Mock
    private HttpServletResponse response;

    @Mock
    private User user;

    @Mock
    private HttpSession httpSession;

    @InjectMocks
    private LoginCommand loginCommand = new LoginCommand(userService);

    @Test
    public void shouldReturnLoginPageIfUserParametersNotMatchesWithDb() {
        when(request.getSession()).thenReturn(httpSession);

        String result = loginCommand.execute(request, response);

        assertEquals(Pages.LOGIN_PAGE, result);
    }

    @Test
    public void shouldReturnNullIfUserAlreadyExistInRequest() {
        when(request.getSession()).thenReturn(httpSession);
        when(httpSession.getAttribute(USER)).thenReturn(user);

        assertNull(loginCommand.execute(request, response));
    }

    @Test
    public void shouldReturnHomePageIfUserParametersMatchesWithDb() throws EntityDAOException {
        when(request.getSession()).thenReturn(httpSession);
        when(request.getParameter(PARAM_LOGIN)).thenReturn(SOME_LOGIN);
        when(request.getParameter(PARAM_PASSWORD)).thenReturn(SOME_PASSWORD);
        when(userService.getByLogin(any())).thenReturn(user);
        when(user.getLogin()).thenReturn(SOME_LOGIN);
        when(user.getPassword()).thenReturn(MD5_PASSWORD);

        String result = loginCommand.execute(request, response);

        assertEquals(Pages.HOME_PAGE, result);

    }


}
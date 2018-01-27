package com.petrushin.epam.auction.command;

import com.petrushin.epam.auction.command.LocaleCommand;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class LocaleCommandTest {

    private static final String PARAM_LANG = "lang";
    private static final String LOCALE_EN = "en";
    private static final String LOCALE_RU = "ru";
    private static final String LOCALE_ES = "es";

    @Mock
    private HttpServletResponse response;

    @Mock
    private HttpServletRequest request;

    @Mock
    private HttpSession session;


    private static LocaleCommand localeCommand = new LocaleCommand();

    @Test
    public void shouldCallSetAttributeFromSessionIfLocaleExist() {
        when(request.getParameter(PARAM_LANG)).thenReturn(LOCALE_EN).thenReturn(LOCALE_RU).thenReturn(LOCALE_ES);
        when(request.getSession()).thenReturn(session);

        localeCommand.execute(request,response);
        localeCommand.execute(request,response);
        localeCommand.execute(request,response);

        verify(session).setAttribute(PARAM_LANG, LOCALE_EN);
        verify(session).setAttribute(PARAM_LANG, LOCALE_RU);
        verify(session).setAttribute(PARAM_LANG, LOCALE_ES);
        verify(session, times(3)).setAttribute(anyString(),any());
    }
}
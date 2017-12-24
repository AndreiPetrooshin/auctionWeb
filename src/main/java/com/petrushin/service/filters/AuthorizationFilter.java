package com.petrushin.service.filters;


import com.petrushin.domain.User;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class AuthorizationFilter implements Filter {


    private static final String PARAM_LOGIN = "login";
    private static final String PARAM_COMMAND = "command";
    private static final String PARAM_USER = "user";
    private static final String PARAM_HOME = "home";
    private static final String PARAM_LOGOUT = "logout";

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse,
                         FilterChain filterChain) throws IOException, ServletException {

        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        String command = request.getParameter(PARAM_COMMAND);
        if (PARAM_LOGIN.equalsIgnoreCase(command)) {
            filterChain.doFilter(request, response);

        } else {
            HttpSession session = request.getSession();
            User user = (User) session.getAttribute(PARAM_USER);
            if (user != null && !PARAM_LOGOUT.equalsIgnoreCase(command)) {
                RequestDispatcher dispatcher = request.getRequestDispatcher(PARAM_HOME);
                dispatcher.forward(request, response);
            }
            filterChain.doFilter(request, response);
        }

    }
}

package com.petrushin.service.filters;


import com.petrushin.domain.User;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class AuthorizationFilter implements Filter {

    private static final String USER = "user";
    private static final String ADMIN = "admin";


    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse,
                         FilterChain filterChain) throws IOException, ServletException {

        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        if (user != null) {
            filterChain.doFilter(request, response);
        }
        filterChain.doFilter(request, response);
//
//        RequestDispatcher dispatcher = request.getRequestDispatcher("login");
//        dispatcher.forward(request, response);


        }


}

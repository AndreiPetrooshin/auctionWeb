package com.petrushin.service.filters;


import com.petrushin.domain.User;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class AuthorizationFilter implements Filter {



    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse,
                         FilterChain filterChain) throws IOException, ServletException {

        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        if ("login".equalsIgnoreCase(request.getParameter("command"))) {
            filterChain.doFilter(request, response);
            return;
        }
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        if (user != null) {
            RequestDispatcher dispatcher = request.getRequestDispatcher("home");
            dispatcher.forward(request, response);
        }
        filterChain.doFilter(request, response);
        }


}

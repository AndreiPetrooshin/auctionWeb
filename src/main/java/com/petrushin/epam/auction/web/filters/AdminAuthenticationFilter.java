package com.petrushin.epam.auction.web.filters;

import com.petrushin.epam.auction.constants.Pages;
import com.petrushin.epam.auction.domain.User;
import com.petrushin.epam.auction.domain.UserRole;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class AdminAuthenticationFilter implements Filter {

    private static final String USER = "user";
    private static final String ADMIN = "admin";

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse,
                         FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute(USER);
        if (user != null) {
            UserRole userRole = user.getRole();
            String role = userRole.getRole();
            if (ADMIN.equals(role)) {
                filterChain.doFilter(request, response);
            } else {
                response.sendRedirect(Pages.HOME_PAGE);
            }
        } else {
            response.sendRedirect(Pages.START_PAGE);
        }
    }
}

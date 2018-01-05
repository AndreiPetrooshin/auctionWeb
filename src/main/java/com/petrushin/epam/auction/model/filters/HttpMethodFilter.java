package com.petrushin.epam.auction.model.filters;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * This Filter determines witch method is used and
 * sets "redirect" attribute to request
 *
 * @author Andrei Petrushin
 * @version 1.0.0
 */
public class HttpMethodFilter implements Filter {

    private static final String ATTR_REDIRECT = "redirect";
    private static final String METHOD_POST = "POST";
    private static final String METHOD_GET = "GET";

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse,
                         FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        if (request.getMethod().equalsIgnoreCase(METHOD_POST)) {
            request.setAttribute(ATTR_REDIRECT, true);
        } else if (request.getMethod().equalsIgnoreCase(METHOD_GET)) {
            request.setAttribute(ATTR_REDIRECT, false);
        }
        filterChain.doFilter(request, servletResponse);

    }

    @Override
    public void destroy() {

    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }
}

package com.petrushin.epam.auction.model.filters;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

public class HttpMethodFilter implements Filter {

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse,
                         FilterChain filterChain) throws IOException, ServletException {


        HttpServletRequest request = (HttpServletRequest) servletRequest;
        if(request.getMethod().equalsIgnoreCase("POST")){
            request.setAttribute("redirect", true);
        } else  if(request.getMethod().equalsIgnoreCase("GET")) {
            request.setAttribute("redirect", false);
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

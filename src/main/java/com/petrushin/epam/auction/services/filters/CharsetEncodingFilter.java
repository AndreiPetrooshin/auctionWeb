package com.petrushin.epam.auction.services.filters;

import javax.servlet.*;
import java.io.IOException;

/**
 * Filter to change character Encoding at
 * request and response
 *
 * @author Andrei Petrushin
 * @version 1.0.0
 */
public class CharsetEncodingFilter implements Filter {

    private static final String PARAM_ENCODING = "encoding";
    private String code;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        code = filterConfig.getInitParameter(PARAM_ENCODING);
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
            throws IOException, ServletException {
        String codeRequest = servletRequest.getCharacterEncoding();
        if (code != null && !code.equalsIgnoreCase(codeRequest)) {
            servletRequest.setCharacterEncoding(code);
            servletResponse.setCharacterEncoding(code);
        }
        filterChain.doFilter(servletRequest, servletResponse);
    }

    public void destroy() {
        code = null;
    }
}


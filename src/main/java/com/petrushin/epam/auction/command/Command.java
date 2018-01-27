package com.petrushin.epam.auction.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * This Interface inherits all classes witch handle the
 * COMMAND - url parameter.
 */
public interface Command {

    String execute(HttpServletRequest request,
                   HttpServletResponse response);
}

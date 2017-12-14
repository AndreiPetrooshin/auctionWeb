package com.petrushin.service;

import com.petrushin.service.exception.CommandException;
import com.petrushin.service.exception.LoginCommandException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface Command {

    String execute(HttpServletRequest request,
                   HttpServletResponse response) throws CommandException;
}

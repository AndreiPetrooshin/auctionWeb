package com.petrushin.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface CommandService {

    void execute(HttpServletRequest request, HttpServletResponse response);
}

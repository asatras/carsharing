package com.carsharing.command;

import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LoginCommand implements Command {

    private static final String PAGE_LOGIN_PAGE = "/login.jsp";
    private static final Logger logger = Logger.getLogger(LoginCommand.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        logger.debug("LoginCommand starts");

        logger.debug("LoginCommand finished");
        return PAGE_LOGIN_PAGE;
    }
}

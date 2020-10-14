package com.carsharing.command;

import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class NoCommand implements Command {

    public static final String PAGE_ERROR_PAGE = "/WEB-INF/error404.jsp";
    private static final Logger logger = Logger.getLogger(NoCommand.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        logger.debug("NoCommand starts");

        String errorMessage = "No such command";
        request.setAttribute("errorMessage", errorMessage);
        logger.error("Set the request attribute: errorMessage --> " + errorMessage);

        logger.debug("NoCommand finished");
        return PAGE_ERROR_PAGE;
    }
}

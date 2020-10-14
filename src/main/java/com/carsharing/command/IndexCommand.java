package com.carsharing.command;

import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class IndexCommand implements Command {

    private static final String PAGE_INDEX_PAGE = "/index.jsp";
    private static final Logger logger = Logger.getLogger(IndexCommand.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        logger.debug("IndexCommand starts");

        logger.debug("IndexCommand finished");
        return PAGE_INDEX_PAGE;
    }
}

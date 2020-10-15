package com.carsharing.command;

import com.carsharing.model.service.CommandService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LogOutCommand implements Command {

    private static final String PAGE_LOGIN_PAGE = "login.jsp";

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        request.getSession().invalidate();
        CommandService.deleteUserFromContextAndSession(request);
        return PAGE_LOGIN_PAGE;
    }
}

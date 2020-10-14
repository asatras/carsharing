package com.carsharing.command;

import com.carsharing.model.db.entity.Role;
import com.carsharing.model.db.entity.User;
import com.carsharing.model.service.CommandService;
import com.carsharing.model.service.UserService;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.util.Optional;

import static java.util.Objects.nonNull;

public class LoginCommand implements Command {

    private static final String PAGE_LOGIN_PAGE = "/login.jsp";
    private static final String PAGE_ADMIN_BASIC = "/WEB-INF/admin/adminBasic.jsp";
    private static final String PAGE_USER_BASIC = "/WEB-INF/user/userBasic.jsp";
    private static final String PAGE_MANAGER_BASIC = "/WEB-INF/manager/managerBasic.jsp";
    private static final String PAGE_INDEX_PAGE = "/index.jsp";
    private static final Logger logger = Logger.getLogger(LoginCommand.class);
    private UserService userService;

    public LoginCommand(UserService userService) {
        this.userService = userService;
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        logger.debug("LoginCommand starts");

        String login = request.getParameter("login");
        String password = request.getParameter("password");

        if (login == null)
            return PAGE_LOGIN_PAGE;

        logger.info("user enter login: " + login + " and password: " + password);

        if (nonNull(request.getSession().getAttribute("login")))
            return "/welcome.jsp";

        Optional<User> user = userService.findUser(login, password);

        if (!user.isPresent()) {
            logger.info("Invalid attempt of user login: '" + login + "'");
            request.setAttribute("error", true);
            return PAGE_LOGIN_PAGE;
        }

        if (CommandService.checkUserIsLogged(request, login)) {
            request.setAttribute("error", true);
            logger.info("User login " + login + " already logged.");
            throw new RuntimeException("You are already logged");
        }
        logger.info("User login " + login + " logged successfully.");

        request.getSession(true).setAttribute("login", login);

        if (user.get().getRole().equals(Role.ADMIN)) {
            CommandService.setUserRole(request, Role.ADMIN, login);
            return "redirect:" + PAGE_ADMIN_BASIC;
        } else if (user.get().getRole().equals(Role.USER)) {
            CommandService.setUserRole(request, Role.USER, login);
            return "redirect:" + PAGE_USER_BASIC;
        } else if (user.get().getRole().equals(Role.MANAGER)) {
            CommandService.setUserRole(request, Role.MANAGER, login);
            return "redirect:" + PAGE_MANAGER_BASIC;
        } else {
            logger.debug("LoginCommand finished");
            return "redirect:" + PAGE_INDEX_PAGE;
        }
    }
}

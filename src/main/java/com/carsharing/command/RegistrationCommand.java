package com.carsharing.command;

import com.carsharing.model.service.UserService;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.SQLException;

public class RegistrationCommand implements Command {

    public static final String PAGE_REGISTRATION_PAGE = "/registration.jsp";
    public static final String PAGE_REDIRECT_TO_LOGIN_PAGE = "redirect:/login";
    private static final Logger logger = Logger.getLogger(RegistrationCommand.class);


    private UserService userService;

    public RegistrationCommand(UserService userService) {
        this.userService = userService;
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        logger.debug("Getting parameters from request");
        String login = request.getParameter("login");
        String password = request.getParameter("password");
        String email = request.getParameter("email");
        String name = request.getParameter("name");
        String surname = request.getParameter("surname");
        String passport = request.getParameter("passport");

        if (login == null || login.equals("")
                || password == null || password.equals("")
                || email == null || email.equals("")
                || name == null || name.equals("")
                || surname == null || surname.equals("")
                || passport == null || passport.equals("")
        ) {
            logger.debug("Some fields were empty or null");
            return PAGE_REGISTRATION_PAGE;
        }
        try {
            if (userService.findByLogin(login).isPresent()) {
                request.setAttribute("error", true);
                logger.debug("User with this login already exists --->" + userService.findByLogin(login));
                return PAGE_REGISTRATION_PAGE;
            }
        } catch (Exception e) {
            logger.debug("Exception while registration", e);
            throw new Exception();
        }

        userService.addUser(login, password, email, name, surname, passport);
        logger.debug("Execute method in RegistrationCommand finished, now redirecting");

        return PAGE_REDIRECT_TO_LOGIN_PAGE;
    }
}

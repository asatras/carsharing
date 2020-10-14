package com.carsharing.model.service;

import com.carsharing.model.db.entity.Role;
import org.apache.log4j.Logger;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashSet;

public class CommandService {

    private final static Logger logger = Logger.getLogger(CommandService.class);

    public static boolean checkUserIsLogged(HttpServletRequest request, String login) {

        @SuppressWarnings("unchecked") final HashSet<String> loggedUsers = (HashSet<String>) request.getSession()
                .getServletContext().getAttribute("loggedUsers");

        if (loggedUsers.isEmpty()) {
            logger.debug("No logged users");
            return false;
        }

        if (loggedUsers.stream().anyMatch(login::equalsIgnoreCase)) {
            logger.debug("User is logged");
            return true;
        }

        loggedUsers.add(login);
        request.getSession().getServletContext().setAttribute("loggedUsers", loggedUsers);

        return false;
    }

    public static void setUserRole(HttpServletRequest request, Role role, String login) {
        HttpSession session = request.getSession();
        session.setAttribute("login", login);
        session.setAttribute("role", role);
    }

    public static void deleteUserFromContextAndSession(HttpServletRequest request) {
        HttpSession session = request.getSession();
        ServletContext context = request.getSession().getServletContext();
        String login = (String) context.getAttribute("login");

        @SuppressWarnings("unchecked")
        HashSet<String> loggedUsers = (HashSet<String>) request.getSession()
                .getServletContext().getAttribute("loggedUsers");
        loggedUsers.remove(login);
        request.getSession().getServletContext().setAttribute("loggedUsers", loggedUsers);
    }
}

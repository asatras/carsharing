package com.carsharing;

import com.carsharing.command.Command;
import com.carsharing.command.CommandContainer;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/Servlet")
public class Servlet extends HttpServlet {


    private static final Logger logger = Logger.getLogger(Servlet.class);

    //TODO /Carsharing/ must be deleted, but it doesnt work without it
    private static String ERROR_404_PAGE = "/WEB-INF/error404.jsp";
    private static String SERVER_ERROR_PAGE = "/WEB-INF/serverError.jsp";

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        logger.debug("doGet starts");

        String commandName = request.getParameter("command");
        logger.debug("request.getParameter(command) get command -->" + commandName);

        Command command = CommandContainer.getCommand(commandName);

        logger.trace("Obtained command --> " + command);
        String forward = ERROR_404_PAGE;

        try {
            logger.trace("Try to forward command");

            forward = command.execute(request, response);
            logger.debug("String forward is ------>" + forward);

            request.getRequestDispatcher(forward).forward(request, response);

        } catch (Exception e) {
            logger.fatal("Exception in try section", e);
            response.sendRedirect(ERROR_404_PAGE);
        }
        logger.trace("Forward address --> " + forward);

        logger.debug("doGet finished, now go to forward address --> " + forward);
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {

        logger.debug("doPost starts");

        String commandName = request.getParameter("command");
        Command command = CommandContainer.getCommand(commandName);
        String redirect = ERROR_404_PAGE;

        try {
            logger.trace("Try to redirect command");
            redirect = command.execute(request, response);
        } catch (Exception e) {
            logger.fatal("Exception while redirect", e);
            request.getSession().setAttribute("redirectError", e);
        }

        logger.trace("Redirect address --> " + redirect);

        logger.debug("doPost finished, now go to redirect address --> " + redirect);
        response.sendRedirect(redirect);
    }
}


package com.carsharing.command;

import com.carsharing.model.db.ConnectionPool;
import com.carsharing.model.db.entity.Role;
import com.carsharing.model.db.entity.User;
import com.carsharing.model.service.CommandService;
import com.carsharing.model.service.UserService;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

import static java.util.Objects.nonNull;

public class LoginCommand implements Command {

    private static final String PAGE_LOGIN_PAGE = "login.jsp";
    private static final String PAGE_ADMIN_BASIC = "WEB-INF/admin/adminBasic.jsp";
    private static final String PAGE_USER_BASIC = "WEB-INF/user/userBasic.jsp";
    private static final String PAGE_MANAGER_BASIC = "WEB-INF/manager/managerBasic.jsp";
    private static final String PAGE_INDEX_PAGE = "index.jsp";
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
            return PAGE_LOGIN_PAGE;

        //TEST
//        Optional<User> user = Optional.ofNullable(testmethod(login, password));
        //END TEST


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
            return PAGE_ADMIN_BASIC;
        } else if (user.get().getRole().equals(Role.USER)) {
            CommandService.setUserRole(request, Role.USER, login);
            return PAGE_USER_BASIC;
        } else if (user.get().getRole().equals(Role.MANAGER)) {
            CommandService.setUserRole(request, Role.MANAGER, login);
            return PAGE_MANAGER_BASIC;
        } else {
            logger.debug("LoginCommand finished");
            return PAGE_INDEX_PAGE;
        }
    }

    //TEST
//    static public User testmethod (String login, String password) {
//
//        try {
//            Connection connection = ConnectionPool.getInstance().getConnection();
//            PreparedStatement ps = connection.prepareStatement("SELECT * FROM users WHERE login = ? AND password = ?");
//            ps.setString(1, login);
//            ps.setString(2, password);
//            ResultSet rs = ps.executeQuery();
//            rs.next();
//            System.out.println(rs.getLong("id"));
//            System.out.println(rs.getString("login"));
//            System.out.println(rs.getString("password"));
//            System.out.println(rs.getString("email"));
//            System.out.println(rs.getString("name"));
//            System.out.println(rs.getString("surname"));
//            System.out.println(rs.getString("passport"));
//            System.out.println(rs.getString("balance"));
//            System.out.println(rs.getString("isActive"));
//        } catch (SQLException e) {
//            e.printStackTrace();
//        } return null;
//    }
}

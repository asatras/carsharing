package com.carsharing.command;

import com.carsharing.model.service.UserService;
import org.apache.log4j.Logger;

import java.util.HashMap;
import java.util.Map;

public class CommandContainer {

    private static final Logger logger = Logger.getLogger(CommandContainer.class);
    private static UserService userService = new UserService();
    private static Map<String, Command> commandContainer = new HashMap<>();

    static {
        logger.debug("Add commands to commandContainer");

        commandContainer.put("index", new IndexCommand());
        commandContainer.put("login", new LoginCommand());
        commandContainer.put("registration", new RegistrationCommand(userService));
//        commandContainer.put("user", new UserCommand());
        commandContainer.put("noCommand", new NoCommand());

        logger.debug("Command container was successfully initialized");
        logger.trace("Number of commands --> " + commandContainer.size());
    }

    public static Command getCommand(String commandName) {

        logger.debug("Getting command");
        if (!commandContainer.containsKey(commandName) || commandName == null) {
            logger.trace("Command not found, name --> " + commandName);

            return commandContainer.get("noCommand");
        }
        logger.debug("Get command -->" + commandContainer.get(commandName).toString());
        return commandContainer.get(commandName);
    }
}

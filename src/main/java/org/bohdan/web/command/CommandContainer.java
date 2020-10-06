package org.bohdan.web.command;

import org.apache.log4j.Logger;

import java.util.Map;
import java.util.TreeMap;

public class CommandContainer {

    private static final Logger logger = Logger.getLogger(CommandContainer.class);

    private static Map<String, Command> commandMap = new TreeMap<String, Command>();

    static {
        commandMap.put("viewTours", new ViewTours());
        commandMap.put("errorPage", new NoCommand());

        logger.debug("Command container was successfully initialized");
        logger.trace("Number of commands --> " + commandMap.size());
    }

    /**
     * Returns command object with the given name.
     *
     * @param commandName
     *            Name of the command.
     * @return Command object.
     */
    public static Command get(String commandName) {
        if (commandName == null || !commandMap.containsKey(commandName)) {
            logger.trace("Command not found, name --> " + commandName);
            return commandMap.get("noCommand");
        }

        return commandMap.get(commandName);
    }
}

package org.bohdan.web.command;

import org.apache.log4j.Logger;
import org.bohdan.web.command.admin.*;
import org.bohdan.web.command.manager.AccountManager;
import org.bohdan.web.command.user.AccountUser;
import org.bohdan.web.command.user.RegisterTour;
import org.bohdan.web.command.user.RegisterTourView;

import java.util.Map;
import java.util.TreeMap;

public class CommandContainer {

    private static final Logger logger = Logger.getLogger(CommandContainer.class);

    private static Map<String, Command> commandMap = new TreeMap<String, Command>();

    static {
        //common
        commandMap.put("viewTours", new ViewToursCommand());
        commandMap.put("viewTour", new ViewTourCommand());
        commandMap.put("login", new LoginCommand());
        commandMap.put("registerUser", new RegisterUser());
        commandMap.put("register", new Register());

        commandMap.put("logout", new LogoutCommand());

        //user
        commandMap.put("accountUser", new AccountUser());
        commandMap.put("registerTourView", new RegisterTourView());
        commandMap.put("registerTour", new RegisterTour());

        //admin
        commandMap.put("accountAdmin", new AccountAdmin());
        commandMap.put("accountManager", new AccountManager());
        commandMap.put("listTours", new ListTours());
        commandMap.put("createTour", new CreateTour());
        commandMap.put("getCreateTour", new GetCreateTourPage());
        commandMap.put("editTour", new EditTour());
        commandMap.put("getEditTour", new GetEditTourPage());
        commandMap.put("deleteTour", new DeleteTour());
        commandMap.put("listOrders", new ListOrders());
        commandMap.put("listUsers", new ListUsers());
        commandMap.put("searchUser", new SearchUser());
        commandMap.put("updateStatusUser", new UpdateStatusUser());
        commandMap.put("updateRole", new UpdateRoleUser());
        commandMap.put("updateStatusOrder", new UpdateStatusOrder());
        commandMap.put("deleteOrder", new DeleteOrder());
        commandMap.put("updateDiscount", new UpdateDiscountOrder());

        //error
        commandMap.put("errorPage", new NoCommand());
        commandMap.put("registerCheck", new RegisterCheck());

        logger.debug("Command container was successfully initialized");
        logger.trace("Number of commands --> " + commandMap.size());
    }

    /**
     * Returns command object with the given name.
     *
     * @param commandName Name of the command.
     * @return Command object.
     */
    public static Command get(String commandName) {
        if (commandName == null) {
            commandName = "main";
        }
        if (!commandMap.containsKey(commandName)) {
            logger.trace("Command not found, name --> " + commandName);
            return commandMap.get("noCommand");
        }

        return commandMap.get(commandName);
    }
}

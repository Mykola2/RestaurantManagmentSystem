package org.training.controller.commands.init;

import org.training.constants.URIConstants;
import org.training.controller.commands.*;
import org.training.controller.commands.admin.OpenUsersCommand;
import org.training.controller.commands.admin.SetBalanceCommand;
import org.training.controller.commands.authorization.*;
import org.training.controller.commands.holder.CommandHolder;
import org.training.controller.commands.menu.AddItemCommand;
import org.training.controller.commands.menu.OpenMenuCommand;
import org.training.controller.commands.order.*;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by nicko on 1/25/2017.
 */
public class InitAllComands {

    private InitAllComands() {

    }

    public static CommandHolder init() {
        Map<String, Command> getCommands = new HashMap<>();
        Map<String, Command> postCommands = new HashMap<>();
        getCommands.put(URIConstants.SIGNUP, new OpenSignUpCommand());
        getCommands.put(URIConstants.SIGNIN, new OpenSignInCommand());
        postCommands.put(URIConstants.SIGNUP, new SignUpCommand());
        postCommands.put(URIConstants.SIGNIN, new SignInCommand());
        getCommands.put(URIConstants.LOGOUT, new LogoutCommand());
        getCommands.put(URIConstants.MENU, new OpenMenuCommand());
        postCommands.put(URIConstants.ADD_TO_ITEM, new AddItemCommand());
        postCommands.put(URIConstants.SET_BALANCE, new SetBalanceCommand());
        getCommands.put(URIConstants.ORDER, new OpenOrderCommand());
        getCommands.put(URIConstants.USERS, new OpenUsersCommand());
        postCommands.put(URIConstants.USERS, new OpenUsersCommand());
        postCommands.put(URIConstants.ORDER, new OpenOrderCommand());
        postCommands.put(URIConstants.REMOVE, new RemoveOrderCommand());
        postCommands.put(URIConstants.MENU, new OpenMenuCommand());
        postCommands.put(URIConstants.ADD_TO_ORDER, new AddToOrderCommand());
        postCommands.put(URIConstants.CONFIRM, new ConfirmOrderCommand());
        getCommands.put(URIConstants.ORDERS, new OpenOrdersCommand());
        postCommands.put(URIConstants.ORDERS, new OpenOrdersCommand());
        postCommands.put(URIConstants.CLOSE, new CloseOrderCommand());
        getCommands.put(URIConstants.OLD, new OpenClosedOrders());
        getCommands.put(URIConstants.CLOSED, new OpenUserClosedOrdersCommand());
        getCommands.put(URIConstants.UALANG, new ChangeToUACommand());
        getCommands.put(URIConstants.ENLANG, new ChangeToENCommand());
        postCommands.put(URIConstants.CLOSED, new OpenUserClosedOrdersCommand());
        postCommands.put(URIConstants.PAY, new PayCommand());
        return new CommandHolder(postCommands, getCommands);
    }
}

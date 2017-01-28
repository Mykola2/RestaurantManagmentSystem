package org.training.controller.commands.init;

import org.training.controller.commands.*;
import org.training.controller.commands.authorization.LogoutCommand;
import org.training.controller.commands.authorization.OpenSignUpCommand;
import org.training.controller.commands.authorization.SignInCommand;
import org.training.controller.commands.authorization.SignUpCommand;
import org.training.controller.commands.holder.CommandHolder;
import org.training.controller.commands.menu.OpenMenuCommand;
import org.training.controller.commands.order.*;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by nicko on 1/25/2017.
 */
public class InitAllComands {
    public static CommandHolder init() {
        Map<String, Command> getCommands = new HashMap<>();
        Map<String, Command> postCommands = new HashMap<>();
        getCommands.put("/signup", new OpenSignUpCommand());
        postCommands.put("/signup", new SignUpCommand());
        postCommands.put("/signin", new SignInCommand());
        getCommands.put("/logout", new LogoutCommand());
        getCommands.put("/menu", new OpenMenuCommand());
        getCommands.put("/order", new OpenOrderCommand());
        postCommands.put("/menu", new OpenMenuCommand());
        postCommands.put("/addToOrder", new AddToOrderCommand());
        postCommands.put("/confirm", new ConfirmOrderCommand());
        getCommands.put("/orders", new OpenOrdersCommand());
        postCommands.put("/orders", new OpenOrdersCommand());
        postCommands.put("/close", new CloseOrderCommand());
        getCommands.put("/old", new OpenClosedOrders());
        return new CommandHolder(postCommands, getCommands);
    }
}
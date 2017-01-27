package org.training.controller.commands.Init;

import org.training.controller.commands.*;
import org.training.controller.commands.holder.CommandHolder;

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
        CommandHolder commandHolder = new CommandHolder(postCommands, getCommands);
        return commandHolder;
    }
}

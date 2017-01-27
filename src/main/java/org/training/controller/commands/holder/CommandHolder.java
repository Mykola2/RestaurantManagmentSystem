package org.training.controller.commands.holder;

import org.training.controller.commands.Command;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by nicko on 1/25/2017.
 */
public class CommandHolder {
    private Map<String, Command> getCommands;
    private Map<String, Command> postCommands;

    public CommandHolder(Map<String, Command> postCommands, Map<String, Command> getCommands) {
        this.postCommands = postCommands;
        this.getCommands = getCommands;
    }

    private Command getPostCommand(String url) {
        return postCommands.get(url);
    }

    private Command getGetCommand(String url) {
        return getCommands.get(url);
    }

    public Command getCommand(String method, String url) {
        Command command = null;
        if (method.equals("POST")) {
            command = getPostCommand(url);
        } else if (method.equals("GET")) {
            command = getGetCommand(url);
        }

        if (command != null) {
            return command;
        } else {
            return null;
        }
    }

    public Command getPageNotFoundCommand() {
        return null;
    }

}

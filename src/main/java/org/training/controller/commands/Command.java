package org.training.controller.commands;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by nicko on 1/25/2017.
 */
public interface Command {
    String execute(HttpServletRequest request, HttpServletResponse response);
}

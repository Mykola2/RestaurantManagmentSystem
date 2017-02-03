package org.training.controller;

import org.training.controller.commands.Command;
import org.training.controller.commands.holder.CommandHolder;
import org.training.controller.commands.init.InitAllComands;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by nicko on 1/25/2017.
 */
public class DispatcherServlet extends HttpServlet {
    CommandHolder commandHolder = InitAllComands.init();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doRequest(request, response);
    }

    protected void doRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String view;
        Command command;
        try {
                command = commandHolder.getCommand(request.getMethod(), request.getRequestURI());
        } catch (Exception e) {
            command = commandHolder.getPageNotFoundCommand();
        }
        view = command.execute(request, response);
        request.getRequestDispatcher(view).forward(request, response);
    }
}

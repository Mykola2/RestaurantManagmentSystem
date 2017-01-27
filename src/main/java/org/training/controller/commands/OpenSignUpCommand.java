package org.training.controller.commands;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by nicko on 1/25/2017.
 */
public class OpenSignUpCommand implements Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        if (request.getSession().getAttribute("Errors") != null) {
            request.setAttribute("Errors", request.getSession().getAttribute("Errors"));
            request.getSession().setAttribute("Errors", null);
            request.setAttribute("User", request.getSession().getAttribute("User"));
            request.getSession().setAttribute("User", null);
        }
        return "/view/signup.jsp";
    }
}

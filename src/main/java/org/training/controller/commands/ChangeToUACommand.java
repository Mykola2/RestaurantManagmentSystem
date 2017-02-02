package org.training.controller.commands;

import org.training.constants.URIConstants;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Created by nicko on 2/1/2017.
 */
public class ChangeToUACommand implements Command {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();
        session.setAttribute("locale","uk_UA");
        return URIConstants.INDEX;
    }
}

package org.training.controller.commands.authorization;

import org.training.constants.PageConstants;
import org.training.controller.commands.Command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by nicko on 2/2/2017.
 */
public class OpenSignInCommand implements Command {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        return PageConstants.VIEW_SIGNIN_JSP;
    }
}

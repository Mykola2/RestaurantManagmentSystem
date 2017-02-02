package org.training.controller.commands.order;

import org.training.constants.PageConstants;
import org.training.controller.commands.Command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Created by nicko on 1/30/2017.
 */
public class RemoveOrderCommand implements Command {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();
        session.removeAttribute("order");
        return PageConstants.VIEW_ORDER_JSP;
    }
}

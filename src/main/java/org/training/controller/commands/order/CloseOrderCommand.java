package org.training.controller.commands.order;


import org.training.constants.URIConstants;
import org.training.controller.commands.Command;
import org.training.service.OrderService;
import org.training.service.UserService;
import org.training.service.impl.OrderServiceImpl;
import org.training.service.impl.UserServiceImpl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by nicko on 1/28/2017.
 */
public class CloseOrderCommand implements Command {
    private OrderService orderService = OrderServiceImpl.getInstance();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        orderService.setClosedById(Integer.parseInt(request.getParameter("id")));
        return URIConstants.ORDERS;
    }
}

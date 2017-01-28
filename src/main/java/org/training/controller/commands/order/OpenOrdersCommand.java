package org.training.controller.commands.order;

import org.training.controller.commands.Command;
import org.training.model.entities.Order;
import org.training.service.OrderService;
import org.training.service.impl.OrderServiceImpl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * Created by nicko on 1/28/2017.
 */
public class OpenOrdersCommand implements Command {
    private OrderService orderService = OrderServiceImpl.getInstance();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        List<Order> orders = orderService.getOpened();
        request.setAttribute("orders", orders);
        return "view/orders.jsp";
    }
}


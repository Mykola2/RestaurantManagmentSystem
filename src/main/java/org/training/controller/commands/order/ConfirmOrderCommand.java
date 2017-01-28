package org.training.controller.commands.order;

import org.training.controller.commands.Command;
import org.training.model.entities.Order;
import org.training.service.OrderService;
import org.training.service.impl.OrderServiceImpl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Created by nicko on 1/27/2017.
 */
public class ConfirmOrderCommand implements Command {
    private OrderService orderService = OrderServiceImpl.getInstance();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();
        Order order = (Order) session.getAttribute("order");
        orderService.create(order);
        session.removeAttribute("order");
        return "/";
    }
}
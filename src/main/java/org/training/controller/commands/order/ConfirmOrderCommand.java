package org.training.controller.commands.order;

import org.training.controller.commands.Command;
import org.training.model.entities.Order;
import org.training.model.entities.User;
import org.training.service.OrderService;
import org.training.service.UserService;
import org.training.service.impl.OrderServiceImpl;
import org.training.service.impl.UserServiceImpl;

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
        Double currentBalance = (Double) session.getAttribute("balance");
        if (orderService.checkBalance(order.getTotalPrice(), currentBalance)) {
            orderService.create(order);
        }
        return "/";
    }
}

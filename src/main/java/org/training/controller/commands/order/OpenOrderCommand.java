package org.training.controller.commands.order;

import org.training.controller.commands.Command;
import org.training.model.entities.Order;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Created by nicko on 1/27/2017.
 */
public class OpenOrderCommand implements Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();
        Order order = (Order) session.getAttribute("order");
        if (order != null) {
            request.setAttribute("orderItems", order.getOrderItems());
            request.setAttribute("totalprice", order.getTotalPrice());
        }
        return "view/order.jsp";
    }
}

package org.training.controller.commands.order;

import org.training.constants.PageConstants;
import org.training.controller.commands.Command;
import org.training.model.entities.Order;
import org.training.service.OrderService;
import org.training.service.impl.OrderServiceImpl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * Created by nicko on 1/31/2017.
 */
public class OpenUserClosedOrdersCommand implements Command {
    private OrderService orderService = OrderServiceImpl.getInstance();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();
        Integer userId = (Integer) session.getAttribute("id");
        List<Order> closedOrder = orderService.getUserClosedOrders(userId);
        request.setAttribute("closedOrders", closedOrder);
        return PageConstants.VIEW_CLOSED_JSP;
    }

}

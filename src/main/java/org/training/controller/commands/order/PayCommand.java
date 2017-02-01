package org.training.controller.commands.order;

import org.training.controller.commands.Command;
import org.training.service.OrderService;
import org.training.service.UserService;
import org.training.service.impl.OrderServiceImpl;
import org.training.service.impl.UserServiceImpl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Created by nicko on 1/31/2017.
 */
public class PayCommand implements Command {
    private OrderService orderService = OrderServiceImpl.getInstance();
    private UserService userService = UserServiceImpl.getInstance();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();
        Integer userId = (Integer) session.getAttribute("id");
        Double totalPrice = Double.parseDouble(request.getParameter("totalprice"));
        userService.withdraw(totalPrice,userId);
        Double currentBalance = (Double) session.getAttribute("balance");
        session.setAttribute("balance",currentBalance - totalPrice);
        Integer orderId = Integer.parseInt(request.getParameter("id"));
        orderService.setOrderPaidById(orderId);
        return "/closed";
    }
}

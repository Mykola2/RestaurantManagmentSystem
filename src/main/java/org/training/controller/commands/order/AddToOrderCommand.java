package org.training.controller.commands.order;

import org.training.constants.URIConstants;
import org.training.controller.commands.Command;
import org.training.model.entities.Item;
import org.training.model.entities.Order;
import org.training.model.entities.OrderItem;
import org.training.service.ItemService;
import org.training.service.impl.ItemServiceImpl;
import org.training.service.UserService;
import org.training.service.impl.UserServiceImpl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.time.LocalDateTime;

/**
 * Created by nicko on 1/27/2017.
 */
public class AddToOrderCommand implements Command {
    private ItemService itemService = ItemServiceImpl.getInstance();
    private UserService userService = UserServiceImpl.getInstance();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();
        Item item = itemService.findById(Integer.parseInt(request.getParameter("id")));
        OrderItem orderItem = new OrderItem(item);
        int amount = Integer.parseInt(request.getParameter("quantity"));
        double price = Double.parseDouble(request.getParameter("price"));
        orderItem.setItemAmount(amount);
        orderItem.setPrice(amount * price);
        Order order;
        if (session.getAttribute("order") == null) {
            order = new Order.Builder()
                    .setUser(userService.findByLogin((String) session.getAttribute("login")))
                    .setDateCreated(LocalDateTime.now())
                    .build();
            order.addOrderItem(orderItem);
            session.setAttribute("order", order);
        } else {
            order = (Order) session.getAttribute("order");
            order.addOrderItem(orderItem);
        }
        return URIConstants.MENU;
    }
}

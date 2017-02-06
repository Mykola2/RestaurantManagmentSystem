package org.training.controller.commands.menu;

import org.training.constants.PageConstants;
import org.training.constants.URIConstants;
import org.training.controller.commands.Command;
import org.training.dao.impl.ItemDAOImpl;
import org.training.model.entities.Item;
import org.training.service.ItemService;
import org.training.service.impl.ItemServiceImpl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.NumberFormat;

/**
 * Created by nicko on 2/3/2017.
 */
public class AddItemCommand implements Command {
    ItemService itemService = ItemServiceImpl.getInstance();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        String name = request.getParameter("name");
        try {
            Double price = Double.parseDouble(request.getParameter("price"));
            Integer weight = Integer.parseInt(request.getParameter("weight"));

            if (!checkInput(price, weight, name)) {
                request.setAttribute("error", "Invalid input");
                return PageConstants.VIEW_ADDITEM_JSP;
            }
            Item item = new Item.Builder()
                    .setName(name)
                    .setPrice(price)
                    .setWeight(weight)
                    .build();
            itemService.create(item);
        } catch (NumberFormatException e) {
            request.setAttribute("error", "Format error");
        }
        return PageConstants.VIEW_MENU_JSP;
    }

    private boolean checkInput(Double price, Integer weight, String name) {
        if (price < 0 || weight < 0) {
            return false;
        }
        if (name.trim().isEmpty()) {
            return false;
        }
        return true;
    }
}

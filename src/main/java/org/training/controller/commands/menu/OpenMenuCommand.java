package org.training.controller.commands.menu;

import org.training.constants.PageConstants;
import org.training.controller.commands.Command;
import org.training.model.entities.Item;
import org.training.service.ItemService;
import org.training.service.impl.ItemServiceImpl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * Created by nicko on 1/26/2017.
 */
public class OpenMenuCommand implements Command {
    private ItemService itemService = ItemServiceImpl.getInstance();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        List<Item> menu = itemService.getAll();
        request.setAttribute("menu", menu);
        return PageConstants.VIEW_MENU_JSP;
    }
}

package org.training.controller.commands.admin;

import org.training.constants.PageConstants;
import org.training.controller.commands.Command;
import org.training.model.entities.User;
import org.training.service.UserService;
import org.training.service.impl.UserServiceImpl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * Created by nicko on 2/3/2017.
 */
public class OpenUsersCommand implements Command {
    UserService userService = UserServiceImpl.getInstance();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        List<User> users = userService.getAllUsers();
        request.setAttribute("users", users);
        return PageConstants.VIEW_USERS_JSP;
    }
}

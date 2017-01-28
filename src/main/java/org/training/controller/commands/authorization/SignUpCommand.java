package org.training.controller.commands.authorization;

import org.training.controller.commands.Command;
import org.training.model.entities.User;
import org.training.service.UserService;
import org.training.service.impl.UserServiceImpl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by nicko on 1/25/2017.
 */
public class SignUpCommand implements Command {
    private UserService userService = UserServiceImpl.getInstance();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        User user = new User(request.getParameter("login"), request.getParameter("password"), request.getParameter("email"),
                Integer.parseInt(request.getParameter("role")));
        userService.create(user);

        return "/";
    }
}

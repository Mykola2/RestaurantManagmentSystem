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
        String login = request.getParameter("login");
        String password = request.getParameter("password");
        String email = request.getParameter("email");
        if (!isInputValid(login, password, email)) {
            request.setAttribute("error", "Invalid input");
            return "/view/signup.jsp";
        }
        Integer role = Integer.parseInt(request.getParameter("role"));
        if (userService.create(login, password, email, role) != null) {
            return "/";
        }
        request.setAttribute("error", "User already exist");
        return "/view/signup.jsp";
    }

    private Boolean isInputValid(String login, String password, String email) {
        return !(login.isEmpty() || email.isEmpty() || password.isEmpty());
    }
}

package org.training.controller.commands.authorization;

import org.training.controller.commands.Command;
import org.training.model.entities.User;
import org.training.service.UserService;
import org.training.service.impl.UserServiceImpl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Created by nicko on 1/26/2017.
 */
public class SignInCommand implements Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {

        String login = request.getParameter("login");
        String password = request.getParameter("password");
        UserService userService = UserServiceImpl.getInstance();
        User existingUser = userService.login(login, password);
        if (existingUser != null) {
            HttpSession session = request.getSession();
            session.setAttribute("id", existingUser.getId());
            session.setAttribute("login", existingUser.getLogin());
            session.setAttribute("role", existingUser.getRole().toString());
            session.setAttribute("balance", existingUser.getBalance());
            return "/";
        }
        request.setAttribute("error","Incorrect credentials");
        return "/view/signin.jsp";
    }
}
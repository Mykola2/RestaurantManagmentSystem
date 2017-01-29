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
        User user = new User();
        user.setLogin(request.getParameter("login"));
        user.setPassword(request.getParameter("password"));
        UserService userService = UserServiceImpl.getInstance();

        try {
            User existingUser = userService.findByLogin(user.getLogin());
            if (existingUser != null) {
                String password = user.getPassword();
                String existingPassword = existingUser.getPassword();

                if (password.equals(existingPassword)) {
                    HttpSession session = request.getSession();
                    existingUser.setPassword(null);
                    session.setAttribute("id", existingUser.getId());
                    session.setAttribute("login", existingUser.getLogin());
                    session.setAttribute("role", existingUser.getRole().toString());
                    session.setAttribute("balance", existingUser.getBalance());
                    return "/";
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        request.getSession().setAttribute("loginError", "Incorrect credentials.");
        return "/";
    }
}
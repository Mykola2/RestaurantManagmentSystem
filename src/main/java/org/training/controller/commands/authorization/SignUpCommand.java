package org.training.controller.commands.authorization;

import org.apache.commons.validator.routines.EmailValidator;
import org.training.constants.URIConstants;
import org.training.controller.commands.Command;
import org.training.model.entities.User;
import org.training.service.UserService;
import org.training.service.impl.UserServiceImpl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static org.training.constants.PageConstants.VIEW_SIGNUP_JSP;

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
            return VIEW_SIGNUP_JSP;
        }
        Integer role = Integer.parseInt(request.getParameter("role"));
        User user = new User.Builder()
                .setLogin(login)
                .setPassword(password)
                .setEmail(email)
                .setRole(role)
                .setBalance(5000.0)
                .build();
        if (userService.create(user) != null) {
            return URIConstants.INDEX;
        }
        request.setAttribute("error", "User already exist");
        return VIEW_SIGNUP_JSP;
    }

    private Boolean isInputValid(String login, String password, String email) {
        EmailValidator validator = EmailValidator.getInstance();
        return validator.isValid(email) && !(login.isEmpty() || email.isEmpty() || password.isEmpty());
    }
}

package org.training.controller.commands.admin;

import org.training.constants.URIConstants;
import org.training.controller.commands.Command;
import org.training.service.UserService;
import org.training.service.impl.UserServiceImpl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by nicko on 2/3/2017.
 */
public class SetBalanceCommand implements Command {
    private UserService userService = UserServiceImpl.getInstance();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        if(request.getParameter("balance").isEmpty()){
            request.setAttribute("error","Invalid input");
            return URIConstants.USERS;
        }
        Double balance = Double.parseDouble(request.getParameter("balance"));
        Integer userId = Integer.parseInt(request.getParameter("id"));
        if (!checkBalance(balance)){
            request.setAttribute("error","Invalid input");
            return URIConstants.USERS;
        }
        userService.setBalance(balance,userId);
        return URIConstants.USERS;

    }

    private boolean checkBalance(Double balance) {
        if(balance<0 || balance == null){
            return false;
        }
        return true;
    }
}

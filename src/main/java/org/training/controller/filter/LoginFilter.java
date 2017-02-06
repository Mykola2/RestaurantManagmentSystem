package org.training.controller.filter;


import org.training.constants.URIConstants;
import org.training.model.entities.User;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by nicko on 2/2/2017.
 */
public class LoginFilter implements Filter {
    List<String> noAuthPermissions = new ArrayList<>();

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        noAuthPermissions.add(URIConstants.SIGNIN);
        noAuthPermissions.add(URIConstants.SIGNUP);
        noAuthPermissions.add(URIConstants.INDEX);
        noAuthPermissions.add(URIConstants.UALANG);
        noAuthPermissions.add(URIConstants.ENLANG);
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        Integer id =  (Integer) request.getSession().getAttribute("id");
        String uri = request.getRequestURI();
        if ( (id != null) && !uri.startsWith(URIConstants.SIGNIN) ){
            filterChain.doFilter(servletRequest, servletResponse);
        } else if ((id == null) && noAuthPermissions.contains(uri)){
            filterChain.doFilter(servletRequest, servletResponse);
        } else {
            request.getRequestDispatcher(URIConstants.SIGNIN).forward(servletRequest,servletResponse);
        }
    }

    @Override
    public void destroy() {

    }


}

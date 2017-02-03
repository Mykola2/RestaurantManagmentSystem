package org.training.controller;

import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.training.controller.commands.Command;
import org.training.controller.commands.holder.CommandHolder;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.io.IOException;

import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Created by nicko on 2/2/2017.
 */
public class ControllerTest {

    @Mock
    HttpServletRequest request;

    @Mock
    HttpServletResponse response;

    @Mock
    HttpSession session;

    @Mock
    Command command;

    @Mock
    RequestDispatcher requestDispatcher;

    @Mock
    CommandHolder commandHolder;

    private DispatcherServlet dispatcherServlet;

    @Captor
    ArgumentCaptor<String> stringArgumentCaptor;

    @Before
    public void before() {
        MockitoAnnotations.initMocks(this);
        dispatcherServlet = new DispatcherServlet();
        dispatcherServlet.commandHolder = commandHolder;
        when(request.getSession()).thenReturn(session);
        when(session.getAttribute(anyString())).thenReturn(1);
        when(request.getRequestDispatcher(stringArgumentCaptor.capture())).thenReturn(requestDispatcher);

    }

    @Test
    public void holderReturnsPostCommmand() throws ServletException, IOException {
        when(request.getRequestURI()).thenReturn("path");
        when(request.getMethod()).thenReturn("post");
        when(commandHolder.getCommand("post","path")).thenReturn(command);
        when(command.execute(request,response)).thenReturn("page");
        dispatcherServlet.doPost(request,response);
        verify(commandHolder,times(1)).getCommand("post","path");

    }

    @Test
    public void holderReturnsGetCommmand() throws ServletException, IOException {
        when(request.getRequestURI()).thenReturn("path");
        when(request.getMethod()).thenReturn("get");
        when(commandHolder.getCommand("get","path")).thenReturn(command);
        when(command.execute(request,response)).thenReturn("page");
        dispatcherServlet.doPost(request,response);
        verify(commandHolder,times(1)).getCommand("get","path");

    }
}

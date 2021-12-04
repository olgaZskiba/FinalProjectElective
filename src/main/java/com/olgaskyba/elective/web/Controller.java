package com.olgaskyba.elective.web;

import com.olgaskyba.elective.exception.DBException;
import com.olgaskyba.elective.logic.command.Command;
import com.olgaskyba.elective.logic.command.CommandContainer;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "controller", value = "/controller")
public class Controller extends HttpServlet {

    public static final Logger log = LogManager.getLogger(Controller.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String commandName = req.getParameter("command");
        Command command = CommandContainer.getCommand(commandName);

       String adress = "error.jsp";

       try {
           assert command != null;
           adress = command.execute(req, resp);
           log.trace("adress ==> " + adress);
       }catch (DBException ex){
           req.setAttribute("ex", ex);
       }
       req.getRequestDispatcher(adress).forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String commandName = req.getParameter("command");
        log.trace("command ==> " + commandName);

        Command command = CommandContainer.getCommand(commandName);
        log.trace("command ==> " + command);

        String adress = "error.jsp";

        try {
            assert command != null;
            adress = command.execute(req, resp);
            log.trace("adress ==> " + adress);
        }catch (DBException ex){
            req.getSession().setAttribute("ex", ex);
        }
        resp.sendRedirect(adress);
    }
}

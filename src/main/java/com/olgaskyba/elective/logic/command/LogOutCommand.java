package com.olgaskyba.elective.logic.command;

import com.olgaskyba.elective.exception.DBException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LogOutCommand implements Command {
    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws DBException {
        HttpSession session = req.getSession(false);
        if(session != null) {
            session.removeAttribute("login");
            session.removeAttribute("loggedProfile");
            session.removeAttribute("profileRole");
        }
        return "login.jsp";
    }
}

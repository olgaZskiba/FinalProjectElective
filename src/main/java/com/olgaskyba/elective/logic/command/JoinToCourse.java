package com.olgaskyba.elective.logic.command;

import com.olgaskyba.elective.exception.DBException;
import com.olgaskyba.elective.logic.UserManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class JoinToCourse implements Command {

    private static final Logger log = LogManager.getLogger(JoinToCourse.class);
    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws DBException {
        HttpSession session = req.getSession();
        boolean result;
        String message = "";

        String login = (String) req.getSession().getAttribute("login");

        String id = req.getParameter("idCourse");
        Long idCourse = Long.parseLong(id);


            if (login != null) {
                result = UserManager.joinStudentTocourse(login, idCourse);
            } else {
                log.trace("User is not logged in");
                return "login.jsp";
            }
            if (result){
                log.trace("User is logged in");
                session.setAttribute("login", login);
                session.setAttribute("idCourse", idCourse);
                return "updateSuccessful.jsp";//new StudentMainMenuCommand().execute(req, resp);//"updateSuccessful.jsp";
            }else
                log.trace("User is not logged in to gradebook or exist in db");
                message = "User is not logged in or already add to this course";
                req.setAttribute("message", message);
                return "error.jsp";

    }
}

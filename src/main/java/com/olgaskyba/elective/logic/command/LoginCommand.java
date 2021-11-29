package com.olgaskyba.elective.logic.command;

import com.olgaskyba.elective.exception.DBException;
import com.olgaskyba.elective.logic.UserManager;
import com.olgaskyba.elective.model.Profile;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.sql.SQLException;


public class LoginCommand implements Command {

    private static final Logger log = LogManager.getLogger(LoginCommand.class);

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws DBException {
        req.getSession().removeAttribute("errorMsg");
        String errorMsg = "";
        String page = null;
        String login = req.getParameter("login");
        log.trace("login ==> " + login);
        String password = req.getParameter("password");
        log.trace("password ==> " + password);
//        Profile profile = new Profile();
//        profile.setLogin(login);
//        profile.setPassword(password);
        Profile profile = null;
        try {
            profile = UserManager.checkLogic2(login, password);
        } catch (SQLException e) {
            errorMsg = "Register please!";
            log.trace("User from login form does not exist");
        }

        if (profile!=null) {
            switch (profile.getRole()){
                case "STUDENT":
                    HttpSession session = req.getSession();
                    session.setAttribute("login", login);
                    session.setAttribute("loggedProfile", profile);
                    session.setAttribute("profileRole", "STUDENT");

                    log.trace("redirect on student page");
                    page = "controller?command=studentMainMenu";
                    break;
                case "TEACHER":
                    session = req.getSession();
                    session.setAttribute("login", login);
                    session.setAttribute("loggedProfile", profile);
                    session.setAttribute("profileRole", "TEACHER");

                    log.trace("redirect on teacher page");
                    page = "controller?command=coursesForTeacherMenu";
                    break;
                case "ADMIN":
                session = req.getSession();
                session.setAttribute("login", login);
                session.setAttribute("loggedProfile", profile);
                session.setAttribute("profileRole", "ADMIN");

                log.trace("redirect on admin page");
                page = "admin.jsp";
                break;
                default:
                    break;
            }
        } else {
            HttpSession session = req.getSession();
            session.setAttribute("profile", profile);
            log.trace("cant redirect , return login page");
            errorMsg = "Register please or add correct data";
            page = "login.jsp";
        }

        req.getSession().setAttribute("errorMsg", errorMsg);
        assert page != null;
        return req.getContextPath().concat("/").concat(page);
    }

}
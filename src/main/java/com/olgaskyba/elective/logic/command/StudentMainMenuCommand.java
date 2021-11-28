package com.olgaskyba.elective.logic.command;

import com.olgaskyba.elective.exception.DBException;
import com.olgaskyba.elective.logic.UserManager;
import com.olgaskyba.elective.model.GradeBook;
import com.olgaskyba.elective.model.Profile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class StudentMainMenuCommand implements Command {
    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws DBException {
        String loginSession = (String) req.getSession().getAttribute("login");

        Profile student = UserManager.findStudentByLogin(loginSession);
        req.setAttribute("student", student);
        return "student.jsp";
    }

}

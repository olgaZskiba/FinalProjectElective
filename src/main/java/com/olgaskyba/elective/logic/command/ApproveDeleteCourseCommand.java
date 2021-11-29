package com.olgaskyba.elective.logic.command;

import com.olgaskyba.elective.exception.DBException;
import com.olgaskyba.elective.logic.CourseManager;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ApproveDeleteCourseCommand implements Command {
    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws DBException {
        String idCourse = req.getParameter("idCourse");
        Long id = Long.parseLong(idCourse);
        String message = "";

        if (CourseManager.deleteCourse(id)) {
            return "controller?command=allCourses";
        }
        message = "Delete course failed.";
        req.setAttribute("message", message);
        return "error.jsp";
    }

}

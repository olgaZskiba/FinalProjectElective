package com.olgaskyba.elective.logic.command;

import com.olgaskyba.elective.exception.DBException;
import com.olgaskyba.elective.logic.CourseManager;
import com.olgaskyba.elective.model.Course;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class DeleteCourseCommand implements Command {
    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws DBException {
        String id = req.getParameter("idCourse");
        Long idCourse = Long.parseLong(id);

        Course deleteCourse = CourseManager.findEditCourse(idCourse);
        req.setAttribute("deleteCourse", deleteCourse);
        return "deleteCoursePage.jsp";
    }
}

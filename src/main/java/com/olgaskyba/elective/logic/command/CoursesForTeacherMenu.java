package com.olgaskyba.elective.logic.command;

import com.olgaskyba.elective.exception.DBException;
import com.olgaskyba.elective.logic.CourseManager;
import com.olgaskyba.elective.logic.UserManager;
import com.olgaskyba.elective.model.ProfileCourse;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class CoursesForTeacherMenu implements Command {
    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws DBException {
        String loginSession = (String) req.getSession().getAttribute("login");

        List<ProfileCourse> profileCourses = UserManager.findCoursesForTeacher(loginSession);
        req.setAttribute("profileCourses", profileCourses);
        return "teacher.jsp";
    }
}

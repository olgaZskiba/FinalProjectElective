package com.olgaskyba.elective.logic.command;

import com.olgaskyba.elective.exception.DBException;
import com.olgaskyba.elective.logic.CourseManager;

import com.olgaskyba.elective.model.Course;
import com.olgaskyba.elective.model.ProfileCourse;
import com.olgaskyba.elective.model.Topic;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class AllTeachersForAdminMenuCommand implements Command{
    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws DBException {
        List<ProfileCourse> courses = CourseManager.selectListTeachersAssignedCourse();
        req.setAttribute("coursesList", courses);
        return "allTeachers.jsp";
    }
}

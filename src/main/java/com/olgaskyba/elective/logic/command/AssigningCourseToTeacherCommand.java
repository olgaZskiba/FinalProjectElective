package com.olgaskyba.elective.logic.command;

import com.olgaskyba.elective.exception.DBException;
import com.olgaskyba.elective.logic.CourseManager;
import com.olgaskyba.elective.model.Course;
import com.olgaskyba.elective.model.ProfileCourse;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class AssigningCourseToTeacherCommand implements Command {
    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws DBException {
        String teacherId = req.getParameter("idProfile");
        Long idTeach = Long.parseLong(teacherId);

        String courseId = req.getParameter("idCourse");
        Long idCour = Long.parseLong(courseId);

        ProfileCourse assigningTeacherCourse = CourseManager.assigningTeacherNewCourse(idTeach);
        List<Course> dropdownCourseList = CourseManager.findAllCourses();


        req.setAttribute("assigningTeacherCourse", assigningTeacherCourse);
        req.setAttribute("dropdownCourseList", dropdownCourseList);
        return "assigningTeacherToCourse.jsp";
    }
}

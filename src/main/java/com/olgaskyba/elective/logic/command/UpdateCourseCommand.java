package com.olgaskyba.elective.logic.command;

import com.olgaskyba.elective.exception.DBException;
import com.olgaskyba.elective.logic.CourseManager;
import com.olgaskyba.elective.model.Course;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class UpdateCourseCommand implements Command {
    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws DBException {
        String idCourse = req.getParameter("idCourse");
        Long id = Long.parseLong(idCourse);

        Course course = CourseManager.findEditCourse(id);

        String name = req.getParameter("courseName");
        course.setCourseName(name);
        String topicId = req.getParameter("courseTopic");
        course.setCourseTopic(Integer.parseInt(topicId));
        String duration = req.getParameter("duration");
        course.setDuration(Integer.parseInt(duration));

        CourseManager.updateEditCourse(course);
        return "controller?command=allCourses";
    }
}

package com.olgaskyba.elective.logic.command;

import com.olgaskyba.elective.exception.DBException;
import com.olgaskyba.elective.logic.CourseManager;
import com.olgaskyba.elective.model.Course;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class CreateCourseCommand implements Command {

    private static final Logger log = LogManager.getLogger(CreateCourseCommand.class);

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws DBException {
        String courseName = req.getParameter("courseName");
        String topicCourse = req.getParameter("courseTopic");
        Integer topicId = Integer.parseInt(topicCourse);
        String durationCourse = req.getParameter("duration");
        Integer duration = Integer.parseInt(durationCourse);

        Course course = new Course();
        course.setCourseName(courseName);
        course.setCourseTopic(topicId);
        course.setDuration(duration);

        if (CourseManager.createCourseForAdminMenu(course)){
            HttpSession session = req.getSession();
            session.setAttribute("createCourse", courseName);
            log.trace("create course and redirect on allCourses page");
            return req.getContextPath().concat("/").concat("controller?command=allCourses");
        }else{
            HttpSession session = req.getSession();
            session.setAttribute("course", course);
            log.trace("cant create course, return form course creation page");
            return req.getContextPath().concat("/").concat("courseCreateForm.jsp");
        }
    }
}

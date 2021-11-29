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
        String errMess = "";
        HttpSession session = req.getSession();

        String courseName = req.getParameter("courseName");
        String topicCourse = req.getParameter("courseTopic");
        Integer topicId = Integer.parseInt(topicCourse);
        String durationCourse = req.getParameter("duration");
        Integer duration = Integer.parseInt(durationCourse);

        Course course = new Course();
        if (!courseName.isEmpty()) {
            course.setCourseName(courseName);
        } else {
            errMess = "field Course name can't be empty";
            session.setAttribute("errMess", errMess);
            return req.getContextPath().concat("/").concat("courseCreateForm.jsp");
        }
        if (topicId != null) {
            course.setCourseTopic(topicId);
        } else {
            errMess = "field Topic id can't be empty";
            session.setAttribute("errMess", errMess);
            return req.getContextPath().concat("/").concat("courseCreateForm.jsp");
        }
        if (duration != null) {
            course.setDuration(duration);
        } else {
            errMess = "field duration can't be empty";
            session.setAttribute("errMess", errMess);
            return req.getContextPath().concat("/").concat("courseCreateForm.jsp");
        }

        if (CourseManager.createCourseForAdminMenu(course)) {
            session.setAttribute("createCourse", courseName);
            log.trace("create course and redirect on allCourses page");
            return req.getContextPath().concat("/").concat("controller?command=allCourses");
        } else {
            session.setAttribute("course", course);
            errMess = "cant create course";
            session.setAttribute("errMess", errMess);
            log.trace("cant create course, return form course creation page");
            return req.getContextPath().concat("/").concat("courseCreateForm.jsp");
        }
    }
}

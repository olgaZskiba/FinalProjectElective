package com.olgaskyba.elective.logic.command;

import com.olgaskyba.elective.exception.DBException;
import com.olgaskyba.elective.logic.CourseManager;
import com.olgaskyba.elective.model.Course;
import com.olgaskyba.elective.model.DescriptionCourse;
import com.olgaskyba.elective.model.Topic;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Paths;


public class CreateCourseCommand implements Command {

    private static final Logger log = LogManager.getLogger(CreateCourseCommand.class);

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws DBException {
        String errMess = "";
        HttpSession session = req.getSession();

        String courseName = req.getParameter("courseName");
        String topicCourse = req.getParameter("courseTopic");
        Long topicId = Long.parseLong(topicCourse);
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
        if(topicCourse!=null) {
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

        //add info to description course table
        String infoCourse = req.getParameter("courseInfo");

//        DescriptionCourse descriptionCourse = new DescriptionCourse();
        if (infoCourse.isEmpty()){
            errMess = "field Course Info can't be empty";
            session.setAttribute("errMess", errMess);
            return req.getContextPath().concat("/").concat("courseCreateForm.jsp");
        }

        InputStream inputStream = null;
        try {
            Part filePart = req.getPart("file");
            if (filePart!=null){
                inputStream = filePart.getInputStream();
            }
            if (inputStream==null){
                errMess = "field Course Photo can't be empty";
                session.setAttribute("errMess", errMess);
                return req.getContextPath().concat("/").concat("courseCreateForm.jsp");
            }
        } catch (IOException | ServletException e) {
            e.printStackTrace();
        }

        if (CourseManager.createCourseForAdminMenu(course, courseName,infoCourse, inputStream)) { //&& CourseManager.createDescriptionCourse(courseName,infoCourse, inputStream)
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

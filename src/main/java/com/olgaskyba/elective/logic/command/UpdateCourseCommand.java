package com.olgaskyba.elective.logic.command;

import com.olgaskyba.elective.exception.DBException;
import com.olgaskyba.elective.logic.CourseManager;
import com.olgaskyba.elective.model.Course;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.IOException;
import java.io.InputStream;

public class UpdateCourseCommand implements Command {
    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws DBException {
        String errMess = "";

        String idCourse = req.getParameter("idCourse");
        Long id = Long.parseLong(idCourse);

        Course course = CourseManager.findEditCourse(id);

        String name = req.getParameter("courseName");
        course.setCourseName(name);
        String topicId = req.getParameter("courseTopic");
        course.setCourseTopic(Long.valueOf(topicId));
        String duration = req.getParameter("duration");
        course.setDuration(Integer.parseInt(duration));

        String infoCourse = req.getParameter("infoCourse");
        course.setInfoCourse(infoCourse);

        InputStream inputStream = null;
        try {
            Part filePart = req.getPart("file");
            if (filePart!=null){
                inputStream = filePart.getInputStream();
                System.out.println(filePart.getName());
                System.out.println(filePart.getSize());
                System.out.println(filePart.getContentType());
                if (filePart.getSize()!= 0){
                    CourseManager.updateEditCourse(course, inputStream);
                }else{
                    CourseManager.updateEditCourse(course);
                }
            }
        } catch (IOException | ServletException e) {
            e.printStackTrace();
        }

        return "controller?command=allCourses";
    }
}

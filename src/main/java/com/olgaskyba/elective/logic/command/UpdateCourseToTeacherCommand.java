package com.olgaskyba.elective.logic.command;

import com.olgaskyba.elective.exception.DBException;
import com.olgaskyba.elective.logic.CourseManager;
import com.olgaskyba.elective.model.ProfileCourse;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.Date;

public class UpdateCourseToTeacherCommand implements Command{
    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws DBException {
        String teacherId = req.getParameter("idProfile");
        Long idTeach = Long.parseLong(teacherId);

        String courseId = req.getParameter("idCourse");
        Long idCour = Long.parseLong(courseId);

//        ProfileCourse teacherToCourse = CourseManager.assigningTeacherNewCourse(idTeach, idCour);

        String courseName = req.getParameter("courseName");
        String startDayCourse = req.getParameter("startDayCourse");

        ProfileCourse profileCourse = new ProfileCourse();
        profileCourse.setIdProfile(idTeach);
        profileCourse.setIdCourse(idCour);
//        profileCourse.setCourseName(courseName);
        profileCourse.setStartDayCourse(Date.valueOf(startDayCourse));

        if(CourseManager.updateCourseToTeacher(profileCourse)){
            return "controller?command=teachersForAdminMenu";
        }
        return "error.jsp";
    }
}

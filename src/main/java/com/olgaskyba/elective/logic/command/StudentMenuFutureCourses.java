package com.olgaskyba.elective.logic.command;

import com.olgaskyba.elective.exception.DBException;
import com.olgaskyba.elective.logic.UserManager;
import com.olgaskyba.elective.model.GradeBook;
import com.olgaskyba.elective.model.Profile;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.util.List;

public class StudentMenuFutureCourses implements Command {
    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws DBException {
        String login = (String) req.getSession().getAttribute("login");

        Profile profile = UserManager.findStudentByLogin(login);

        Long idStudent = profile.getIdProfile();

//        String id = req.getParameter("idProfile");
//        Long idStudent = Long.parseLong(id);

        List<GradeBook> profileCourseList = UserManager.findFutureCoursesForStudent(idStudent);
        req.setAttribute("profileCourseList", profileCourseList);

        List<GradeBook> currentCoursesList = UserManager.findCurrentCoursesForStudent(idStudent);
        req.setAttribute("currentCoursesList", currentCoursesList);


        List<GradeBook> pastCoursesList = UserManager.findPastCoursesForStudent(idStudent);
        req.setAttribute("pastCoursesList", pastCoursesList);

        return "futureCourseList.jsp";
    }
}

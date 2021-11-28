package com.olgaskyba.elective.web;

import com.olgaskyba.elective.exception.DBException;
import com.olgaskyba.elective.logic.CourseManager;
import com.olgaskyba.elective.model.Course;
import com.olgaskyba.elective.model.ProfileCourse;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

//@WebServlet("/teachersForAdminMenu")
public class AdminEditController extends HttpServlet {

    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            List<ProfileCourse> courses = CourseManager.selectListTeachersAssignedCourse();
            req.setAttribute("coursesList", courses);
        } catch (DBException e) {
            e.printStackTrace();
        }
        req.getRequestDispatcher("allTeachers.jsp").forward(req, resp);
    }
}

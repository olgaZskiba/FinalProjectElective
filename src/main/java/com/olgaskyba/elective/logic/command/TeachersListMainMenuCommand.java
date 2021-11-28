package com.olgaskyba.elective.logic.command;

import com.olgaskyba.elective.exception.DBException;
import com.olgaskyba.elective.logic.CourseManager;
import com.olgaskyba.elective.logic.UserManager;
import com.olgaskyba.elective.model.Course;
import com.olgaskyba.elective.model.Profile;
import com.olgaskyba.elective.model.ProfileCourse;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.nio.file.attribute.UserPrincipal;
import java.util.List;

public class TeachersListMainMenuCommand implements Command {
    private static final int shift = 0;
    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws DBException {
        String idProfile = req.getParameter("idProfile");
        String commandParam = req.getParameter("command");
        String paramPage = req.getParameter("page");
        String paramPageSize = req.getParameter("pageSize");

        int page = Integer.parseInt(paramPage);
        int pageSize = Integer.parseInt(paramPageSize);

        List<Profile> profileList = null;
        List<ProfileCourse> profileCourseList=null;
        int size = 0;

        if (idProfile==null){
       profileList = UserManager.findTeacherProfiles(pageSize*(page-1), pageSize);
        size = UserManager.getTeacherProfilesSize();
        }
        else {
           profileCourseList = CourseManager.findCoursesAssignedToTeacherForTeacherMainMenu(pageSize*(page-1), pageSize, Long.parseLong(idProfile));
           size = CourseManager.getCoursesSizeAssignedToTeacher(Long.parseLong(idProfile));
        }

        int minPossible = page-shift<1?1:page-shift;

        int pageCount = (int)Math.ceil(size*1.0/pageSize);
        int maxPagePossible = page + shift > pageCount ? pageCount:page + shift;

        req.setAttribute("commandParam", commandParam);
        req.setAttribute("profileList", profileList);
        req.setAttribute("pageCount", pageCount);
        req.setAttribute("page", page);
        req.setAttribute("pageSize", pageSize);
        req.setAttribute("minPossible", minPossible);
        req.setAttribute("maxPagePossible", maxPagePossible);
        req.setAttribute("profileCourseList", profileCourseList);
        return "teacherListMainMenu.jsp";
    }
}

package com.olgaskyba.elective.logic.command;

import com.olgaskyba.elective.exception.DBException;
import com.olgaskyba.elective.logic.UserManager;
import com.olgaskyba.elective.model.Profile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class EditBlockStatusToStudent implements Command {
    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws DBException {
        String idProfile = req.getParameter("idProfile");
        Long id = Long.parseLong(idProfile);

        Profile studentProfile = UserManager.findStudentProfile(id);
        req.setAttribute("studentProfile", studentProfile);
        return "editBlockStatus.jsp";
    }
}

package com.olgaskyba.elective.logic.command;

import com.olgaskyba.elective.exception.DBException;
import com.olgaskyba.elective.logic.UserManager;
import com.olgaskyba.elective.model.Profile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class UpdateBlockStatusCommand implements Command {
    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws DBException {
        String message = "";

        String id = req.getParameter("idProfile");
        Long idProfile = Long.parseLong(id);

        Profile profile = UserManager.findStudentProfile(idProfile);


        String blockStatus = req.getParameter("blockStatus");
        String blockStatus1 = req.getParameter("blockStatus1");
        System.out.println(blockStatus);
        System.out.println(blockStatus1);
        if (blockStatus!=null && blockStatus1==null){
            profile.setBlockStatus(1);
        }
        else if (blockStatus==null && blockStatus1!=null){
            profile.setBlockStatus(0);
        }

        if(UserManager.updateBlockStatusProfile(profile)){
        return "controller?command=allStudents";
        }
        else {
            message = "Can't update block status";
            req.setAttribute("message", message);
            return "error.jsp";
        }
    }
}

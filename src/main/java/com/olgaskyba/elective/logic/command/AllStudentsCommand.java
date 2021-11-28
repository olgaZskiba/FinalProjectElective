package com.olgaskyba.elective.logic.command;

import com.olgaskyba.elective.exception.DBException;
import com.olgaskyba.elective.logic.UserManager;
import com.olgaskyba.elective.model.Profile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class AllStudentsCommand implements Command {
    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws DBException {
        List<Profile> studentsList = UserManager.selectAllStudents();

        int result = 0;
        String blockAttribute ="";

        for (int i = 1; i<studentsList.size(); i++){
        result = studentsList.get(i).getBlockStatus();
        if (result == 0){
            studentsList.get(i).setBlockStringStatus("Un Block");
        } else if (result==1){
            studentsList.get(i).setBlockStringStatus("Block");
        }
        }

//        if (result == 0){
//            blockAttribute="Un Block";
//        }
//        else if (result==1){
//            blockAttribute="Block";
//        }
        req.setAttribute("studentsList", studentsList);
//        req.setAttribute("blockAttribute", blockAttribute);
        return "allStudents.jsp";
    }
}

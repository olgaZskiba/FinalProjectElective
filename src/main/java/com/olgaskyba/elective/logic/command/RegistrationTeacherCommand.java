package com.olgaskyba.elective.logic.command;

import com.olgaskyba.elective.exception.DBException;
import com.olgaskyba.elective.logic.UserManager;
import com.olgaskyba.elective.model.Profile;
import jdk.nashorn.internal.runtime.regexp.joni.exception.ErrorMessages;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Locale;


public class RegistrationTeacherCommand implements Command{

    private static final Logger log = LogManager.getLogger(RegistrationTeacherCommand.class);
//    private static final String ErrorMes = "this field cannot be empty";

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws DBException {

        req.getSession().removeAttribute("ErrorMes");
        String mess = "";


        String login = req.getParameter("login");
        String password = req.getParameter("password");
        String email = req.getParameter("email");
        String telephone = req.getParameter("telephone");
        String name = req.getParameter("name");
        String surname = req.getParameter("surname");
        String role = req.getParameter("role");
        Profile profile = new Profile();
        if (login!=null){
        profile.setLogin(login);
        }else {
            mess = "LOGIN field cannot be empty";
            req.getSession().setAttribute("ErrorMes", mess);
            return req.getContextPath().concat("/").concat("registerTeacher.jsp");
        }
        if (password!=null && !password.isEmpty()) {
            profile.setPassword(password);
        }else {
            mess = "PASSWORD field cannot be empty";
            req.getSession().setAttribute("ErrorMes", mess);
            return req.getContextPath().concat("/").concat("registerTeacher.jsp");
        }
        if (email!=null && email.contains("@")){
        profile.setEmail(email);
        }else {
            mess = "EMAIL field incorrect or cannot be empty";
            req.getSession().setAttribute("ErrorMes", mess);
            return req.getContextPath().concat("/").concat("registerTeacher.jsp");
        }
        if (telephone!=null && telephone.length()==12) {
            profile.setTelephone(telephone);
        }else {
            mess = "TELEPHONE field incorrect or cannot be empty";
            req.getSession().setAttribute("ErrorMes", mess);
            return req.getContextPath().concat("/").concat("registerTeacher.jsp");
        }
        if (name!=null && !name.isEmpty()){
            profile.setName(name);
        }else {
            mess = "NAME field cannot be empty";
            req.getSession().setAttribute("ErrorMes", mess);
            return req.getContextPath().concat("/").concat("registerTeacher.jsp");
        }
        if (surname!=null && !surname.isEmpty()){
        profile.setSurname(surname);}
        else {
            mess = "SURNAME field cannot be empty";
            req.getSession().setAttribute("ErrorMes", mess);
            return req.getContextPath().concat("/").concat("registerTeacher.jsp");
        }
        profile.setRole(role.toUpperCase(Locale.ROOT));


        if (UserManager.createProfile(profile)){
            log.trace("insert teacher and redirect on allTeachers page");
            return "controller?command=teachersForAdminMenu";//req.getContextPath().concat("/").concat("allTeachers.jsp");
        }else{
            mess = "cant insert teacher";
            req.getSession().setAttribute("ErrorMes", mess);
            log.trace("cant insert teacher, return registerTeacher page");
            return req.getContextPath().concat("/").concat("registerTeacher.jsp");
        }
    }
}

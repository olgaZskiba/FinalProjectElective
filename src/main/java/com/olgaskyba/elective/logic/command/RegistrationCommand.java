package com.olgaskyba.elective.logic.command;

import com.olgaskyba.elective.exception.DBException;
import com.olgaskyba.elective.logic.UserManager;
import com.olgaskyba.elective.model.Profile;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class RegistrationCommand implements Command{

    private static final Logger log = LogManager.getLogger(RegistrationCommand.class);
//    private static final String ErrorMes = "this field cannot be empty";

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws DBException {
        String mess = "";
        req.getSession().removeAttribute("loginMess");
        req.getSession().removeAttribute("passwordMess");
        req.getSession().removeAttribute("emailMess");
        req.getSession().removeAttribute("telMess");
        req.getSession().removeAttribute("commonMess");

        String login = req.getParameter("login");
        String password = req.getParameter("password");
        String email = req.getParameter("email");
        String telephone = req.getParameter("telephone");
        String name = req.getParameter("name");
        String surname = req.getParameter("surname");

        Profile profile = new Profile();
        if (login!=null && !login.isEmpty()){
            profile.setLogin(login);
        }else {
//            String loginMess = "login cannot be empty";
//            req.setAttribute("loginMess", loginMess);
            mess = "login cannot be empty";
            req.getSession().setAttribute("loginMess", mess);
            return req.getContextPath().concat("/").concat("registration.jsp");
        }
        if (password!=null && !password.isEmpty()) {
            profile.setPassword(password);
        }else {
//            String passwordMess = "password cannot be empty";
//            req.setAttribute("passwordMess", passwordMess);
            mess = "password cannot be empty";
            req.getSession().setAttribute("passwordMess", mess);
            return req.getContextPath().concat("/").concat("registration.jsp");
        }
        if (email!=null && email.contains("@")){
            profile.setEmail(email);
        }else {
//            String emailMess = "incorrect email";
            mess = "incorrect email";
            req.getSession().setAttribute("emailMess", mess);
            return req.getContextPath().concat("/").concat("registration.jsp");
        }
        if (telephone!=null && telephone.length()==12) {
            profile.setTelephone(telephone);
        }else {
//            String telMess = "incorrect telephone";
            mess = "incorrect telephone";
            req.getSession().setAttribute("telMess", mess);
            return req.getContextPath().concat("/").concat("registration.jsp");
        }
        profile.setName(name);
        profile.setSurname(surname);


        if (UserManager.createProfile(profile)){
            HttpSession session = req.getSession();
            session.setAttribute("login", login);
            log.trace("insert profile and redirect on login page");
            return req.getContextPath().concat("/").concat("login.jsp");
        }else{
            HttpSession session = req.getSession();
            session.setAttribute("profile", login);
            log.trace("cant insert profile , return registration page");
            mess = "can't to create profile";
            req.getSession().setAttribute("commonMess", mess);
            return req.getContextPath().concat("/").concat("registration.jsp");
        }

    }
}

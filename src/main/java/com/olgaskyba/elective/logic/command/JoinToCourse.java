package com.olgaskyba.elective.logic.command;

import com.olgaskyba.elective.exception.DBException;
import com.olgaskyba.elective.logic.CourseManager;
import com.olgaskyba.elective.logic.UserManager;
import com.olgaskyba.elective.model.Course;
import com.olgaskyba.elective.model.Profile;
import com.olgaskyba.elective.util.HtmlEmailSender;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.mail.MessagingException;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class JoinToCourse implements Command {

    private static final Logger log = LogManager.getLogger(JoinToCourse.class);

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws DBException {
        HttpSession session = req.getSession();
        boolean result;
        String message = "";

        String login = (String) req.getSession().getAttribute("login");

        String id = req.getParameter("idCourse");
        Long idCourse = Long.parseLong(id);

        Course course = CourseManager.findEditCourse(idCourse);
        String courseName = course.getCourseName();

        String host = "smtp.gmail.com";
        String port = "587";
        String user = "yavtushenkooliviya@gmail.com";
        String pass = "0909olivka";
        Profile profile = (Profile) req.getSession().getAttribute("loggedProfile");
        String toAddress = profile.getEmail();
        String subject = "Hello " + profile.getName();
        String emailMessage = "<i>Greetings!</i><br>";
        emailMessage += "<b>Thanks, for choose our education platform Some Courses!</b><br>";
        emailMessage += "<b>You was added to </b><br>" + courseName;
        emailMessage += "<b><font color=red>Some Courses</font></b>";
        HtmlEmailSender htmlEmailSender = new HtmlEmailSender();


            if (login != null) {
                result = UserManager.joinStudentTocourse(login, idCourse);
            } else {
                log.trace("User is not logged in");
                return "login.jsp";
            }
            if (result){
                //UN COMMENT BEFORE PRESENTATION!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
                try {
                    htmlEmailSender.sendHtmlEmail(host, port, user, pass, toAddress, subject, emailMessage);
                    log.trace("Email sent.");
                } catch (MessagingException e) {
                    log.trace("Failed to sent email.");
                    e.printStackTrace();
                }
                log.trace("User is logged in");
                session.setAttribute("login", login);
                session.setAttribute("idCourse", courseName);
                return "updateSuccessful.jsp";//new StudentMainMenuCommand().execute(req, resp);//"updateSuccessful.jsp";
            }else
                log.trace("User is not logged in to gradebook or exist in db");
                message = "User is not logged in or already add to this course";
                req.setAttribute("message", message);
                return "error.jsp";

    }
}

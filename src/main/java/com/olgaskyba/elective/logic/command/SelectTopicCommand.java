package com.olgaskyba.elective.logic.command;

import com.olgaskyba.elective.exception.DBException;
import com.olgaskyba.elective.logic.CourseManager;
import com.olgaskyba.elective.model.Course;
import com.olgaskyba.elective.model.Topic;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.util.List;

public class SelectTopicCommand implements Command{

    private static final int shift = 0;

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws DBException {
        String commandParam = req.getParameter("command");
        String paramPage = req.getParameter("page");
        String paramPageSize = req.getParameter("pageSize");
        String topic = req.getParameter("topic");

        int page = Integer.parseInt(paramPage);
        int pageSize = Integer.parseInt(paramPageSize);

        List<Topic> topics =null;
        List<Course> courses=null;
        int size = 0;

        if (topic==null) {
            topics = CourseManager.selectListTopics(pageSize*(page-1), pageSize);
            size = CourseManager.getTopicSize();
        }

        else if (topic.equals("java")){
            courses = CourseManager.selectAllJavaCourses(pageSize*(page-1), pageSize);
            size = CourseManager.getSizeJavaCourses();
        }

        else if (topic.equals(".net")){
            courses = CourseManager.selectAllDotNetCourses(pageSize*(page-1), pageSize);
            size = CourseManager.getSizeDotNetCourses();
        }

        else if (topic.equals("cPlus")){
            courses = CourseManager.selectAllCPlusCourses(pageSize*(page-1), pageSize);
            size = CourseManager.getSizeCPlusCourses();
        }

        else if (topic.equals("javaScript")){
            courses = CourseManager.selectAllJavaScriptCourses(pageSize*(page-1), pageSize);
            size = CourseManager.getSizeJavaScriptCourses();
        }

        int minPossible = page-shift<1?1:page-shift;

        int pageCount = (int)Math.ceil(size*1.0/pageSize);
        int maxPagePossible = page + shift > pageCount ? pageCount:page + shift;


        req.setAttribute("topics", topics);
        req.setAttribute("commandParam", commandParam);
        req.setAttribute("pageCount", pageCount);
        req.setAttribute("page", page);
        req.setAttribute("pageSize", pageSize);
        req.setAttribute("minPossible", minPossible);
        req.setAttribute("maxPagePossible", maxPagePossible);
        req.setAttribute("topic", topic);
        req.setAttribute("courses", courses);
        return "topicList.jsp";
    }
}

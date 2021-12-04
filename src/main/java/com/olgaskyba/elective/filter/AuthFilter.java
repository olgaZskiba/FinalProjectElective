package com.olgaskyba.elective.filter;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.*;


public class AuthFilter implements Filter {


    private static final Logger log = LogManager.getLogger(AuthFilter.class);

    private static Map<String, List<String>> accessMap = new HashMap<>();
    private static List<String> commons = new ArrayList<>();
    private static List<String> outOfControl = new ArrayList<>();

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        log.trace("AuthFilter ======> init");
        accessMap.put("ADMIN", asList(filterConfig.getInitParameter("ADMIN")));
        accessMap.put("TEACHER", asList(filterConfig.getInitParameter("TEACHER")));
        accessMap.put("STUDENT", asList(filterConfig.getInitParameter("STUDENT")));
        log.trace("Access map ===> " + accessMap);

        outOfControl = asList(filterConfig.getInitParameter("outOfControl"));
        log.trace("Out of control commands --> " + outOfControl);

        commons = asList(filterConfig.getInitParameter("common"));
        log.trace("Common commands ===> " + commons);

        log.trace("AuthFilter ======> init finished");
    }

    private boolean accessAllowed(ServletRequest servletRequest) {
        HttpServletRequest request = (HttpServletRequest) servletRequest;

        String commandName = request.getParameter("command");
        log.trace("AuthFilter command name======> " + commandName);

        if (commandName == null || commandName.isEmpty()) {         // ---------> ?
            return false;
        }
        if (outOfControl.contains(commandName)){
            return true;
        }
        HttpSession session = request.getSession(false); //false
        if (session == null){
            return false;
        }
        String profileRole = (String) session.getAttribute("profileRole");

        if (profileRole == null){
            return commons.contains(commandName);
        }
        return accessMap.get(profileRole).contains(commandName) || commons.contains(commandName);
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        log.trace("AuthFilter ======> doFilter start");

        if (accessAllowed(servletRequest)){
            filterChain.doFilter(servletRequest, servletResponse);
        }else {
            String message = "Access denied";
            servletRequest.setAttribute("message", message);
            servletRequest.getRequestDispatcher("error.jsp").forward(servletRequest, servletResponse);
        }
    }


    private List<String> asList(String str) {
        List<String> list = new ArrayList<>();
        StringTokenizer stringTokenizer = new StringTokenizer(str);
        while (stringTokenizer.hasMoreTokens()) {
            list.add(stringTokenizer.nextToken());
        }
        return list;
    }

    @Override
    public void destroy() {
        Filter.super.destroy();
    }
}

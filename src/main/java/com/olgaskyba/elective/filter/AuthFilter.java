package com.olgaskyba.elective.filter;

import com.olgaskyba.elective.model.Profile;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;


//@WebFilter(urlPatterns = "/*")
public class AuthFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        Filter.super.init(filterConfig);
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
        HttpSession session = httpServletRequest.getSession();

        Profile profile = (Profile) session.getAttribute("loggedProfile");
        String path = httpServletRequest.getRequestURI();

        if (profile==null && (path.contains("login.jsp") || path.contains("registration.jsp") ||
                path.contains("mainMenu.jsp") || path.contains("topicList") || path.contains("commonMainMenu")
        || path.contains("sortCoursesBy") || path.contains("teachersListMainMenu") || path.contains("/"))){
            filterChain.doFilter(servletRequest, servletResponse);
        }
        else if (profile!=null){
            filterChain.doFilter(servletRequest, servletResponse);
        }
    }

    @Override
    public void destroy() {
        Filter.super.destroy();
    }
}

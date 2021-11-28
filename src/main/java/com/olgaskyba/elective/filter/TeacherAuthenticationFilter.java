package com.olgaskyba.elective.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter(urlPatterns = {"/controller?command=coursesForTeacherMenu", "/controller?command=teacherInfoMenu",
"/controller?command=teacherMenuCoursesDetails", "/controller?command=teacherMenuUpdateGrade"})
public class TeacherAuthenticationFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        Filter.super.init(filterConfig);
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
        HttpSession session = httpServletRequest.getSession();

        boolean isLoggedIn = (session != null && session.getAttribute("profileRole").equals("TEACHER"));
        if (isLoggedIn){
//            RequestDispatcher requestDispatcher = servletRequest.getRequestDispatcher("admin.jsp");
//            requestDispatcher.forward(servletRequest, servletResponse);
            filterChain.doFilter(servletRequest, servletResponse);
        }else {
            RequestDispatcher requestDispatcher = servletRequest.getRequestDispatcher("login.jsp");
            requestDispatcher.forward(servletRequest, servletResponse);
        }
    }

    @Override
    public void destroy() {
        Filter.super.destroy();
    }
}

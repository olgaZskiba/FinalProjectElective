package com.olgaskyba.elective.filter;


import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter(urlPatterns = {"/admin.jsp", "/controller?command=adminInfoMenu", "/registerTeacher.jsp",
"/controller?command=teachersForAdminMenu", "/controller?command=editTeacherForCourse", "/courseCrateForm.jsp",
"/controller?command=allCourses", "/controller?command=editCourse", "/controller?command=deleteCourse",
"/controller?command=allStudents"})

public class AdminAuthenticationFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        Filter.super.init(filterConfig);
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
        HttpSession session = httpServletRequest.getSession();

//        if (httpServletRequest.authenticate((HttpServletResponse) servletResponse))


        boolean isLoggedInAdmin = (session != null && session.getAttribute("profileRole").equals("ADMIN"));
//        boolean isLoggedInTeacher = (session != null && session.getAttribute("profileRole").equals("TEACHER"));
//        boolean isLoggedInStudent = (session != null && session.getAttribute("profileRole").equals("STUDENT"));
//        boolean isNotLoggedInUser = (session != null && session.getAttribute("profileRole").equals("STUDENT"));

        String path = httpServletRequest.getRequestURI();
        System.out.println(path);

        if (isLoggedInAdmin){
            filterChain.doFilter(servletRequest, servletResponse);
        }
        else{
            RequestDispatcher requestDispatcher = servletRequest.getRequestDispatcher("login.jsp");
            requestDispatcher.forward(servletRequest, servletResponse);
        }
    }

    @Override
    public void destroy() {
        Filter.super.destroy();
    }
}
/*
@Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {
        if (request.getRequestURI().equals("/login") && request.getMethod().toLowerCase().equals("post")) {
            filterChain.doFilter(request, response);
            return;
        }
    }
}

 else if (isLoggedInAdmin && httpServletRequest.getRequestURI().contains("editTeacherForCourse")){
                    filterChain.doFilter(servletRequest, servletResponse);
        }
 if ((isLoggedInAdmin||isLoggedInStudent||isLoggedInTeacher||isNotLoggedInUser) && (httpServletRequest.getRequestURI().endsWith("login.jsp")||httpServletRequest.getRequestURI().endsWith("/"))){
            filterChain.doFilter(servletRequest, servletResponse);


 */

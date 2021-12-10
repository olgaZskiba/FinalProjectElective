package com.olgaskyba.elective.logic;

import com.olgaskyba.elective.ConnectionPool;
import com.olgaskyba.elective.db.DBManager;
import com.olgaskyba.elective.exception.DBException;
import com.olgaskyba.elective.model.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class CourseManager {
    private static final Logger log = LogManager.getLogger(CourseManager.class);

    private static CourseManager instance;

    public static synchronized CourseManager getInstance() {
        if (instance == null) {
            instance = new CourseManager();
        }
        return instance;
    }

    private CourseManager() {

    }

    private static DBManager dbManager = DBManager.getInstance();
    private static ConnectionPool connectionPool = ConnectionPool.getInstance();


    public static List<Course> findAllCourses() throws DBException {
        List<Course> courses;
        Connection connection = null;
        try {
            connection = connectionPool.getConnection();
            connection.setAutoCommit(true);

            courses = dbManager.getListCourses(connection);

        } catch (SQLException e) {
            log.error("Cannot select courseList ");
            throw new DBException("Cannot select courseList ");
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    log.error("Cannot close a connection.");
                }
            }
        }
        return courses;
    }

    public static List<Course> findAllCoursesForAssigning() throws DBException {
        List<Course> courses;
        Connection connection = null;
        try {
            connection = connectionPool.getConnection();
            connection.setAutoCommit(true);

            courses = dbManager.getListAvailableCourses(connection); //courses = dbManager.getListCourses(connection);

        } catch (SQLException e) {
            log.error("Cannot select courseList ");
            throw new DBException("Cannot select courseList ");
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    log.error("Cannot close a connection.");
                }
            }
        }
        return courses;
    }


    public static ProfileCourse assigningTeacherNewCourse(Long idTeach) throws DBException {
        ProfileCourse profileCourse;
        Connection connection = null;
        try {
            connection = connectionPool.getConnection();
            connection.setAutoCommit(true);


            profileCourse = dbManager.findTeacherForCourse(connection, idTeach);
            if (profileCourse == null){
               if( dbManager.createProfileCourse(connection,idTeach)){
                   profileCourse =dbManager.findTeacherForCourse(connection, idTeach);
                   return profileCourse;
               }else return null;

            }
            return profileCourse;

        } catch (SQLException e) {
            log.error("Cannot find user with that login and Password");
            throw new DBException("Cannot find user with that login and Password");
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    log.error("Cannot close a connection.");
                }
            }
        }
//        return profileCourse;
    }


    public static List<ProfileCourse> selectListTeachersAssignedCourse() throws DBException {
        List<ProfileCourse> courses;
        Connection connection = null;
        try {
            connection = connectionPool.getConnection();
            connection.setAutoCommit(true);

            courses = dbManager.getTeacherForAdminMenu(connection);

        } catch (SQLException e) {
            log.error("Cannot select topicList ");
            throw new DBException("Cannot select topicList ");
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    log.error("Cannot close a connection.");
                }
            }
        }
        return courses;
    }

    public static boolean updateCourseToTeacher(ProfileCourse profileCourse) throws DBException {
        Connection connection = null;
        try {
            connection = connectionPool.getConnection();
            connection.setAutoCommit(true);

            if (dbManager.updateProfileCourse(connection, profileCourse)) {
                return true;
            }
        } catch (SQLException e) {
            log.error("Cannot update profile_course, profile_course = " + profileCourse);
            throw new DBException("Cannot update profile_course, profile_course = " + profileCourse);
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    log.error("Cannot close a connection.");
                }
            }
        }
        return false;
    }

    public static boolean createCourseForAdminMenu(Course course, String courseName, String infoCourse, InputStream inputStream) throws DBException {
        Connection connection = null;
        try {
            connection = connectionPool.getConnection();
            connection.setAutoCommit(false);

            if (dbManager.createCourse(connection, course) && dbManager.createDescriptionCourse(connection,courseName, infoCourse, inputStream)) {
                connection.commit();
                return true;
            }
        } catch (SQLException e) {
            log.error("Cannot create course, course = " + course);
            try {
                connection.rollback();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
            throw new DBException("Cannot create profile, profile = " + course);
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    log.error("Cannot close a connection.");
                }
            }
        }
        return false;
    }

    public static Course findEditCourse(Long id) throws DBException {
        Connection connection = null;
        Course course;
        try {
            connection = connectionPool.getConnection();
            connection.setAutoCommit(true);
            course = dbManager.findCourseById(connection, id);

        } catch (SQLException e) {
            log.error("Cannot find course");
            throw new DBException("Cannot find course");
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    log.error("Cannot close a connection.");
                }
            }
        }
        return course;
    }

    public static boolean updateEditCourse(Course course) throws DBException {
        Connection connection = null;
        try {
            connection = connectionPool.getConnection();
            connection.setAutoCommit(true);
            if (dbManager.updateCourse(connection, course) && dbManager.updateDescriptionCourseOnlyInfo(connection, course)) {
                return true;
            }
        } catch (SQLException e) {
            log.error("Cannot update course = " + course);
            throw new DBException("Cannot update course = " + course);
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    log.error("Cannot close a connection.");
                }
            }
        }
        return false;
    }

    public static boolean updateEditCourse(Course course, InputStream inputStream) throws DBException {
        Connection connection = null;
        try {
            connection = connectionPool.getConnection();
            connection.setAutoCommit(true);
            if (dbManager.updateCourse(connection, course) && dbManager.updateDescriptionCourse(connection, course, inputStream)) {
                return true;
            }
        } catch (SQLException e) {
            log.error("Cannot update course = " + course);
            throw new DBException("Cannot update course = " + course);
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    log.error("Cannot close a connection.");
                }
            }
        }
        return false;
    }

    public static boolean deleteCourse(Long id) throws DBException {
        Connection connection = null;
        try {
            connection = connectionPool.getConnection();
            connection.setAutoCommit(true);
            if (dbManager.deleteCourseById(connection, id)) {
                return true;
            }
        } catch (SQLException e) {
            log.error("Cannot delete course by Id");
            throw new DBException("Cannot delete course by Id");
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    log.error("Cannot close a connection.");
                }
            }
        }
        return false;
    }

    public static List<ProfileCourse> findAllCoursesForMainMenu(int offset, int limit) throws DBException {
        List<Course> courses;
        Connection connection = null;

        List<ProfileCourse> profileCourseList;

        try {
            connection = connectionPool.getConnection();
            connection.setAutoCommit(true);

            profileCourseList = dbManager.findAllProfileCourses(connection, offset, limit);

            courses = dbManager.findAllCoursesMainMenu(connection, offset, limit);

        } catch (SQLException e) {
            log.error("Cannot select courseList for Main menu");
            throw new DBException("Cannot select courseList for Main menu");
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    log.error("Cannot close a connection.");
                }
            }
        }
//        return courses;
        return profileCourseList;
    }

    public static int getCoursesSize() throws DBException {
        int size = 0;
        Connection connection = null;
        try {
            connection = connectionPool.getConnection();
            connection.setAutoCommit(true);

           size = dbManager.findCoursesSizeMainMenu(connection);

        } catch (SQLException e) {
            log.error("Cannot get courseList size for Main menu");
            throw new DBException("Cannot get courseList size for Main menu");
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    log.error("Cannot close a connection.");
                }
            }
        }
        return size;
    }

    public static List<Course> findAllCoursesForMainMenuSortByAToZ(int offset, int limit) throws DBException {
        List<Course> courses;
        Connection connection = null;
        try {
            connection = connectionPool.getConnection();
            connection.setAutoCommit(true);

            courses = dbManager.findAllCoursesSortByAtoZMainMenu(connection, offset, limit);

        } catch (SQLException e) {
            log.error("Cannot select courseList for Main menu from A to Z");
            throw new DBException("Cannot select courseList for Main menu from A to Z");
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    log.error("Cannot close a connection.");
                }
            }
        }
        return courses;
    }

    public static List<Course> findAllCoursesForMainMenuSortByZToA(int offset, int limit) throws DBException {
        List<Course> courses;
        Connection connection = null;
        try {
            connection = connectionPool.getConnection();
            connection.setAutoCommit(true);

            courses = dbManager.findAllCoursesSortByZtoAmainMenu(connection, offset, limit);

        } catch (SQLException e) {
            log.error("Cannot select courseList for Main menu from Z to A");
            throw new DBException("Cannot select courseList for Main menu from Z to A");
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    log.error("Cannot close a connection.");
                }
            }
        }
        return courses;
    }

    public static List<Course> findAllCoursesForMainMenuSortByDuration(int offset, int limit) throws DBException {
        List<Course> courses;
        Connection connection = null;
        try {
            connection = connectionPool.getConnection();
            connection.setAutoCommit(true);

            courses = dbManager.findAllCoursesSortByDurationMainMenu(connection, offset, limit);

        } catch (SQLException e) {
            log.error("Cannot select courseList for Main menu by Duration");
            throw new DBException("Cannot select courseList for Main menu by Duration");
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    log.error("Cannot close a connection.");
                }
            }
        }
        return courses;
    }

    public static List<GradeBook> findAllCoursesForMainMenuSortByStudentCount(int offset, int limit) throws DBException {
        List<GradeBook> courses;
        Connection connection = null;
        try {
            connection = connectionPool.getConnection();
            connection.setAutoCommit(true);

            courses = dbManager.findAllCoursesSortByStudentsMainMenu(connection, offset, limit);

        } catch (SQLException e) {
            log.error("Cannot find courseList for Main menu by students count");
            throw new DBException("Cannot find courseList for Main menu by students count");
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    log.error("Cannot close a connection.");
                }
            }
        }
        return courses;
    }


    public static List<Topic> selectListTopics(int offset, int limit) throws DBException {
        List<Topic> topics;
        Connection connection = null;
        try {
            connection = connectionPool.getConnection();
            connection.setAutoCommit(true);

            topics = dbManager.getListTopic(connection, offset, limit);

        } catch (SQLException e) {
            log.error("Cannot select topicList ");
            throw new DBException("Cannot select topicList ");
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    log.error("Cannot close a connection.");
                }
            }
        }
        return topics;
    }

    public static int getTopicSize() throws DBException {
        int size = 0;
        Connection connection = null;
        try {
            connection = connectionPool.getConnection();
            connection.setAutoCommit(true);

            size = dbManager.findTopicSizeMainMenu(connection);

        } catch (SQLException e) {
            log.error("Cannot get Topic List size for Main menu");
            throw new DBException("Cannot get Topic List size for Main menu");
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    log.error("Cannot close a connection.");
                }
            }
        }
        return size;
    }

    public static List<Course> selectAllJavaCourses(int offset, int limit) throws DBException {
        List<Course> courses;
        Connection connection = null;
        try {
            connection = connectionPool.getConnection();
            connection.setAutoCommit(true);

            courses = dbManager.findAllJavaCourses(connection, offset, limit);

        } catch (SQLException e) {
            log.error("Cannot select Java courseList for Topic Main menu");
            throw new DBException("Cannot select Java courseList for Topic Main menu");
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    log.error("Cannot close a connection.");
                }
            }
        }
        return courses;
    }

    public static int getSizeJavaCourses() throws DBException {
        int size = 0;
        Connection connection = null;
        try {
            connection = connectionPool.getConnection();
            connection.setAutoCommit(true);

            size = dbManager.findCoursesSizeJavaTopic(connection);

        } catch (SQLException e) {
            log.error("Cannot get size Java courseList for Topic menu");
            throw new DBException("Cannot get size Java courseList for Topic menu");
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    log.error("Cannot close a connection.");
                }
            }
        }
        return size;
    }

    public static List<Course> selectAllDotNetCourses(int offset, int limit) throws DBException {
        List<Course> courses;
        Connection connection = null;
        try {
            connection = connectionPool.getConnection();
            connection.setAutoCommit(true);

            courses = dbManager.findAllDotNetCourses(connection, offset, limit);

        } catch (SQLException e) {
            log.error("Cannot select .Net courseList for Topic Main menu");
            throw new DBException("Cannot select .Net courseList for Topic Main menu");
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    log.error("Cannot close a connection.");
                }
            }
        }
        return courses;
    }

    public static int getSizeDotNetCourses() throws DBException {
        int size = 0;
        Connection connection = null;
        try {
            connection = connectionPool.getConnection();
            connection.setAutoCommit(true);

            size = dbManager.findCoursesSizeDotNetTopic(connection);

        } catch (SQLException e) {
            log.error("Cannot get size .Net courseList for Topic menu");
            throw new DBException("Cannot get size .Net courseList for Topic menu");
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    log.error("Cannot close a connection.");
                }
            }
        }
        return size;
    }

    public static List<Course> selectAllCPlusCourses(int offset, int limit) throws DBException {
        List<Course> courses;
        Connection connection = null;
        try {
            connection = connectionPool.getConnection();
            connection.setAutoCommit(true);

            courses = dbManager.findAllCPlusCourses(connection, offset, limit);

        } catch (SQLException e) {
            log.error("Cannot select C++ courseList for Topic Main menu");
            throw new DBException("Cannot select C++ courseList for Topic Main menu");
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    log.error("Cannot close a connection.");
                }
            }
        }
        return courses;
    }

    public static int getSizeCPlusCourses() throws DBException {
        int size = 0;
        Connection connection = null;
        try {
            connection = connectionPool.getConnection();
            connection.setAutoCommit(true);

            size = dbManager.findCoursesSizeCPlusTopic(connection);

        } catch (SQLException e) {
            log.error("Cannot get size C++ courseList for Topic menu");
            throw new DBException("Cannot get size C++ courseList for Topic menu");
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    log.error("Cannot close a connection.");
                }
            }
        }
        return size;
    }

    public static List<Course> selectAllJavaScriptCourses(int offset, int limit) throws DBException {
        List<Course> courses;
        Connection connection = null;
        try {
            connection = connectionPool.getConnection();
            connection.setAutoCommit(true);

            courses = dbManager.findAllJavaScriptCourses(connection, offset, limit);

        } catch (SQLException e) {
            log.error("Cannot select Java Script courseList for Topic Main menu");
            throw new DBException("Cannot select Java Script courseList for Topic Main menu");
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    log.error("Cannot close a connection.");
                }
            }
        }
        return courses;
    }

    public static int getSizeJavaScriptCourses() throws DBException {
        int size = 0;
        Connection connection = null;
        try {
            connection = connectionPool.getConnection();
            connection.setAutoCommit(true);

            size = dbManager.findCoursesSizeJavaScriptTopic(connection);

        } catch (SQLException e) {
            log.error("Cannot get size Java Script courseList for Topic menu");
            throw new DBException("Cannot get size Java Script courseList for Topic menu");
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    log.error("Cannot close a connection.");
                }
            }
        }
        return size;
    }

    public static List<ProfileCourse> findCoursesAssignedToTeacherForTeacherMainMenu(int offset, int limit, Long id) throws DBException {
        List<ProfileCourse> courses;
        Connection connection = null;
        try {
            connection = connectionPool.getConnection();
            connection.setAutoCommit(true);

            courses = dbManager.findAllAssignedCoursesToTeacher(connection, offset, limit, id);

        } catch (SQLException e) {
            log.error("Cannot select courseList assigned to teacher for Teacher Main menu");
            throw new DBException("Cannot select courseList assigned to teacher for Teacher Main menu");
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    log.error("Cannot close a connection.");
                }
            }
        }
        return courses;
    }

    public static int getCoursesSizeAssignedToTeacher(Long id) throws DBException {
        int size = 0;
        Connection connection = null;
        try {
            connection = connectionPool.getConnection();
            connection.setAutoCommit(true);

            size = dbManager.findCoursesSizeAssignedToTeacher(connection, id);

        } catch (SQLException e) {
            log.error("Cannot get size courseList assigned to teacher for Teacher Main menu");
            throw new DBException("Cannot get size courseList assigned to teacher for Teacher Main menu");
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    log.error("Cannot close a connection.");
                }
            }
        }
        return size;
    }

    public static Topic findTopicIdByTopicName(String topicCourse) throws DBException {
        Connection connection = null;
        Topic topic;
        try {
            connection = connectionPool.getConnection();
            connection.setAutoCommit(true);
            topic = dbManager.findTopicIdByName(connection, topicCourse);

        } catch (SQLException e) {
            log.error("Cannot find course");
            throw new DBException("Cannot find course");
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    log.error("Cannot close a connection.");
                }
            }
        }
        return topic;
    }

    public static boolean createDescriptionCourse(String courseName, String infoCourse, InputStream inputStream) {
        Connection connection = null;
        try {
            connection = connectionPool.getConnection();
            connection.setAutoCommit(true);

            if(dbManager.createDescriptionCourse(connection,courseName, infoCourse, inputStream)){
                return true;
            }

        } catch (SQLException e) {
            log.error("Can't add course description");
            e.printStackTrace();
        }
        return false;
    }
}

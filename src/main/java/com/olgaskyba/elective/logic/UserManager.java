package com.olgaskyba.elective.logic;

import com.olgaskyba.elective.ConnectionPool;
import com.olgaskyba.elective.db.DBManager;
import com.olgaskyba.elective.exception.DBException;
import com.olgaskyba.elective.model.Course;
import com.olgaskyba.elective.model.GradeBook;
import com.olgaskyba.elective.model.Profile;
import com.olgaskyba.elective.model.ProfileCourse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class UserManager {

    private static final Logger log = LogManager.getLogger(UserManager.class);

    private static UserManager instance;

    public static synchronized UserManager getInstance() {
        if (instance == null) {
            instance = new UserManager();
        }
        return instance;
    }

    private UserManager() {

    }

    private static DBManager dbManager = DBManager.getInstance();
    private static ConnectionPool connectionPool = ConnectionPool.getInstance();


    public static boolean createProfile(Profile profile) throws DBException {
        Connection connection = null;
        try {
            connection = connectionPool.getConnection();
            connection.setAutoCommit(true);

            if (dbManager.createProfile(connection, profile)) {
                return true;
            }
        } catch (SQLException e) {
            log.error("Cannot create profile, profile = " + profile);
            throw new DBException("Cannot create profile, profile = " + profile);
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

    public static boolean checkLogic(String login, String password) throws DBException {

        Connection connection = null;
        try {
            connection = connectionPool.getConnection();
            connection.setAutoCommit(true);

            Profile profile = dbManager.validateProfileByLoginAndPassword(connection, login, password);
            if (profile.getEmail() != null) {
                return true;
            }

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
        return false;
    }

    public static void createProfiles(Profile... profiles) throws DBException {
        Connection con = null;
        try {
            con = connectionPool.getConnection();
            con.setAutoCommit(false);

            for (Profile profile : profiles) {
                dbManager.createProfile(con, profile);
            }
            con.commit();
        } catch (SQLException ex) {
            // (1)
            log.error("Cannot insert profile, profile = " + profiles);

            // (2)
            try {
                con.rollback();
            } catch (SQLException e) {
                e.printStackTrace();
            }

            // (3)
            throw new DBException("Cannot insert profile");
        } finally {
            if (con != null) {
                try {
                    con.close();
                } catch (SQLException e) {
                    log.error("Cannot close a connection.");
                }
            }
        }
    }


    public static Profile checkLogic2(String login, String password) throws DBException, SQLException {

        Connection connection = null;
        try {
            connection = connectionPool.getConnection();
            connection.setAutoCommit(true);

            Profile profile = dbManager.validateProfileByLoginAndPassword(connection, login, password);
            if (profile.getEmail() != null) {
                //return profile.getRole();
                return profile;
            }

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
        return null;
    }

    public static List<Profile> selectAllStudents() throws DBException {
        Connection connection = null;
        List<Profile> studentsList;
        try {
            connection = connectionPool.getConnection();
            connection.setAutoCommit(true);
            studentsList = dbManager.findAllProfileStudents(connection);
            if (!studentsList.isEmpty()) {
                return studentsList;
            }
        } catch (SQLException e) {
            log.error("Cannot select profileList ");
            throw new DBException("Cannot select profileList ");
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    log.error("Cannot close a connection.");
                }
            }
        }
        return null;
    }

    public static Profile findStudentProfile(Long id) throws DBException {
        Connection connection = null;
        try {
            connection = connectionPool.getConnection();
            connection.setAutoCommit(true);
            Profile profile = dbManager.findProfileById(connection, id);
            if (profile != null) {
                return profile;
            }
        } catch (SQLException e) {
            log.error("Cannot select student profile");
            throw new DBException("Cannot select student profile");
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    log.error("Cannot close a connection.");
                }
            }
        }
        return null;
    }

    public static boolean updateBlockStatusProfile(Profile profile) throws DBException {
        Connection connection = null;
        try {
            connection = connectionPool.getConnection();
            connection.setAutoCommit(true);

            if (dbManager.updateBlockStatus(connection, profile)) {
                return true;
            }
        } catch (SQLException e) {
            log.error("Cannot update student's block status profile");
            throw new DBException("Cannot update student's block status profile");
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

    public static List<ProfileCourse> findCoursesForTeacher(String loginSession) throws DBException {
        Connection connection = null;
        try {
            connection = connectionPool.getConnection();
            connection.setAutoCommit(true);
            List<ProfileCourse> courseList = dbManager.findProfileCourseByLogin(connection, loginSession);
            if (!courseList.isEmpty()) {
                return courseList;
            }
        } catch (SQLException e) {
            log.error("Cannot find courses for teacher menu");
            throw new DBException("Cannot find courses for teacher menu");
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    log.error("Cannot close a connection.");
                }
            }
        }
        return null;
    }

    public static List<GradeBook> findStudentsFromGradeBook(Long id) throws DBException {
        List<GradeBook> gradeBookList;
        Connection connection = null;
        try {
            connection = connectionPool.getConnection();
            connection.setAutoCommit(true);
            gradeBookList = dbManager.findStudentsGradeBookById(connection, id);
            if (!gradeBookList.isEmpty()) {
                return gradeBookList;
            }
        } catch (SQLException e) {
            log.error("Cannot find students from gradeBook");
            throw new DBException("CCannot find students from gradeBook");
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    log.error("Cannot close a connection.");
                }
            }
        }
        return null;
    }

    public static GradeBook findStudentGradebookByIdCourseIdStudent(Long studentId, Long courseId) throws DBException {
        Connection connection = null;
        try {
            connection = connectionPool.getConnection();
            connection.setAutoCommit(true);

            GradeBook gradeBook = dbManager.findStudentGradeBookByIdCourseIdStudent(connection, studentId, courseId);
            if (gradeBook != null) {
                return gradeBook;
            }
        } catch (SQLException e) {
            log.error("Cannot find student by Id course, Id student from gradeBook");
            throw new DBException("Cannot find student by Id course, Id student from gradeBook");
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    log.error("Cannot close a connection.");
                }
            }
        }
        return null;
    }

    public static boolean updateGradeStudent(GradeBook gradeBook) throws DBException {
        Connection connection = null;
        try {
            connection = connectionPool.getConnection();
            connection.setAutoCommit(true);

            if (dbManager.updateGradeStatus(connection, gradeBook)) {
                return true;
            }
        } catch (SQLException e) {
            log.error("Cannot update student's grade");
            throw new DBException("Cannot update student's grade");
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

    public static Profile findStudentByLogin(String loginSession) throws DBException {
        Connection connection = null;
        try {
            connection = connectionPool.getConnection();
            connection.setAutoCommit(true);
            Profile profile = dbManager.findProfileByLogin(connection, loginSession);
            if (profile != null) {
                return profile;
            }
        } catch (SQLException e) {
            log.error("Cannot find student profile by login");
            throw new DBException("Cannot find student profile by login");
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    log.error("Cannot close a connection.");
                }
            }
        }
        return null;
    }

    public static List<GradeBook> findFutureCoursesForStudent(Long idStudent) throws DBException {
        List<GradeBook> profileCourseList;
        Connection connection = null;
        try {
            connection = connectionPool.getConnection();
            connection.setAutoCommit(true);
            profileCourseList = dbManager.findFutureCourse(connection, idStudent);
            if (!profileCourseList.isEmpty()) {
                return profileCourseList;
            }
        } catch (SQLException e) {
            log.error("Cannot find list future courses for student menu");
            throw new DBException("Cannot find list future courses for student menu");
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    log.error("Cannot close a connection.");
                }
            }
        }
        return null;
    }

    public static List<GradeBook> findCurrentCoursesForStudent(Long idStudent) throws DBException {
        List<GradeBook> profileCourseList;
        Connection connection = null;
        try {
            connection = connectionPool.getConnection();
            connection.setAutoCommit(true);
            profileCourseList = dbManager.findCurrentCourse(connection, idStudent);
            if (!profileCourseList.isEmpty()) {
                return profileCourseList;
            }
        } catch (SQLException e) {
            log.error("Cannot find list current courses for student menu");
            throw new DBException("Cannot find list current courses for student menu");
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    log.error("Cannot close a connection.");
                }
            }
        }
        return null;
    }

    public static List<GradeBook> findPastCoursesForStudent(Long idStudent) throws DBException {
        List<GradeBook> profileCourseList;
        Connection connection = null;
        try {
            connection = connectionPool.getConnection();
            connection.setAutoCommit(true);
            profileCourseList = dbManager.findPastCourse(connection, idStudent);
            if (!profileCourseList.isEmpty()) {
                return profileCourseList;
            }
        } catch (SQLException e) {
            log.error("Cannot find list past courses for student menu");
            throw new DBException("Cannot find list past courses for student menu");
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    log.error("Cannot close a connection.");
                }
            }
        }
        return null;
    }

    public static List<Profile> findTeacherProfiles(int offset, int limit) throws DBException {
        List<Profile> profileList;
        Connection connection = null;
        try {
            connection = connectionPool.getConnection();
            connection.setAutoCommit(true);

            profileList = dbManager.findAllTeacherProfiles(connection, offset, limit);

        } catch (SQLException e) {
            log.error("Cannot select teacher profiles TeacherList Main menu");
            throw new DBException("Cannot select teacher profiles TeacherList Main menu");
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    log.error("Cannot close a connection.");
                }
            }
        }
        return profileList;
    }

    public static int getTeacherProfilesSize() throws DBException {
        int size = 0;
        Connection connection = null;
        try {
            connection = connectionPool.getConnection();
            connection.setAutoCommit(true);

            size = dbManager.findTeacherProfileSize(connection);

        } catch (SQLException e) {
            log.error("Cannot get size teacher Profiles for teacher Main Menu");
            throw new DBException("Cannot get size teacher Profiles for teacher Main Menu");
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

    public static boolean joinStudentTocourse(String login, Long idCourse) throws DBException {
        Connection connection = null;
        Profile profile = null;
        GradeBook gradeBook = null;
        try {
            connection = connectionPool.getConnection();
            connection.setAutoCommit(false);

            profile = dbManager.findProfileByLogin(connection, login);

           if (profile!=null){
               Long idProfile = profile.getIdProfile();
               gradeBook = dbManager.findStudentGradeBookByIdCourseIdStudent(connection, idProfile, idCourse);
           }

            if (gradeBook.getIdStudent() == null) {
                if (dbManager.addStudentToCourse(connection, profile, idCourse)) {
                    connection.commit();
                    return true;
                }
            }

        } catch (SQLException ex) {
            log.error("Cannot add profile to gradebook = " + profile);
            try {
                connection.rollback();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            throw new DBException("Cannot insert profile");
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
}



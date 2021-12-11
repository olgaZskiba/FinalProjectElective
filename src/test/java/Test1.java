import com.olgaskyba.elective.db.DBManager;
import com.olgaskyba.elective.model.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Test1 {
    private static String url = "jdbc:mysql://localhost/elective_db?serverTimezone=Europe/Moscow&useSSL=false";
    private static String username = "skiba";
    private static String password = "0909";

    @Test
    public void selectUser() throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        Class.forName("com.mysql.cj.jdbc.Driver").getDeclaredConstructor().newInstance();
        DBManager dbManager = DBManager.getInstance();
        try (Connection conn = DriverManager.getConnection(url, username, password);) {
            Profile profile1 = dbManager.validateProfileByLoginAndPassword(conn, "admin", "123");
            Profile profile2 = new Profile();
            profile2.setName("Olga");
            Assertions.assertEquals(profile2, profile1);
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Test
    public void createUser() throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        Class.forName("com.mysql.cj.jdbc.Driver").getDeclaredConstructor().newInstance();
        DBManager dbManager = DBManager.getInstance();
        try (Connection conn = DriverManager.getConnection(url, username, password);) {
            Profile profile = new Profile();
            profile.setLogin("olgaz123");
            profile.setPassword("2345");
            profile.setEmail("olgaz1212@sweetheart.ua");
            profile.setTelephone("380575996397");
            profile.setName("olga12");
            profile.setSurname("skyba12");

            Assertions.assertTrue(dbManager.createProfile(conn, profile));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    @Test
    public void getListTopic() throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        Class.forName("com.mysql.cj.jdbc.Driver").getDeclaredConstructor().newInstance();
        DBManager dbManager = DBManager.getInstance();
        try (Connection conn = DriverManager.getConnection(url, username, password);) {
            List<Topic> topics = new ArrayList<>();
            topics.add(new Topic("JAVA"));
            topics.add(new Topic("C++"));
            topics.add(new Topic(".NET"));

//            Assertions.assertEquals(topics, dbManager.getListTopic(conn));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void shouldReturnTeacherAndAssignedCourses() throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        Class.forName("com.mysql.cj.jdbc.Driver").getDeclaredConstructor().newInstance();
        DBManager dbManager = DBManager.getInstance();
        try (Connection conn = DriverManager.getConnection(url, username, password);) {
            List<ProfileCourse> courses = new ArrayList<>();
            courses.add(new ProfileCourse("Dmytro", "Kolesnicov", "JAVA ADVANTAGE", Date.valueOf("2022-01-10"), 24));

            Assertions.assertEquals(courses, dbManager.getTeacherForAdminMenu(conn));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void shouldReturnTeacherById() throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        Class.forName("com.mysql.cj.jdbc.Driver").getDeclaredConstructor().newInstance();
        DBManager dbManager = DBManager.getInstance();
        try (Connection conn = DriverManager.getConnection(url, username, password);) {
            ProfileCourse course = new ProfileCourse();

            Assertions.assertEquals(course, dbManager.findTeacherForCourse(conn, 2L));


        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void shouldUpdateCourseForUser() throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver").getDeclaredConstructor().newInstance();
        DBManager dbManager = DBManager.getInstance();
        try (Connection conn = DriverManager.getConnection(url, username, password);) {
            ProfileCourse course = new ProfileCourse(2L, 7L, Date.valueOf("2022-01-12"));

            Assertions.assertTrue(dbManager.updateProfileCourse(conn, course));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void shouldChgeBlockStatusProfile() throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        Class.forName("com.mysql.cj.jdbc.Driver").getDeclaredConstructor().newInstance();
        DBManager dbManager = DBManager.getInstance();
        try (Connection conn = DriverManager.getConnection(url, username, password);) {
            Profile profile = new Profile();
            profile.setIdProfile(7L);
            profile.setLogin("student5");
            profile.setPassword("stunt5");
            profile.setEmail("student05gmail.com");
            profile.setTelephone("380509776645");
            profile.setName("Emilya");
            profile.setSurname("Yavt");
            profile.setRole("STUDENT");
            profile.setBlockStatus(1);

            Assertions.assertTrue(dbManager.updateBlockStatus(conn, profile));


        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Test
    public void shouldUpdateCourseForTeacher() throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        Class.forName("com.mysql.cj.jdbc.Driver").getDeclaredConstructor().newInstance();
        DBManager dbManager = DBManager.getInstance();
        try (Connection conn = DriverManager.getConnection(url, username, password);) {
            ProfileCourse profileCourse = new ProfileCourse();
            profileCourse.setIdProfile(2L);
            profileCourse.setIdCourse(9L);
            profileCourse.setStartDayCourse(Date.valueOf("2022-03-09"));

            Assertions.assertTrue(dbManager.updateProfileCourse(conn, profileCourse));
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Test
    public void shouldReturnListCoursesForTeacherMenu() throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        Class.forName("com.mysql.cj.jdbc.Driver").getDeclaredConstructor().newInstance();
        DBManager dbManager = DBManager.getInstance();
        try (Connection conn = DriverManager.getConnection(url, username, password);) {
            List<ProfileCourse> profileCourses = new ArrayList<>();

            Assertions.assertEquals(profileCourses, dbManager.findProfileCourseByLogin(conn, "teacher3"));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void shouldReturnListStudentsGradeForTeacherMenu() throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        Class.forName("com.mysql.cj.jdbc.Driver").getDeclaredConstructor().newInstance();
        DBManager dbManager = DBManager.getInstance();
        try (Connection conn = DriverManager.getConnection(url, username, password);) {
            List<GradeBook> gradeBookList = new ArrayList<>();
            Assertions.assertEquals(gradeBookList, dbManager.findStudentsGradeBookById(conn, 3L));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void shouldUpdateGradeForTeacherMenu() throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        Class.forName("com.mysql.cj.jdbc.Driver").getDeclaredConstructor().newInstance();
        DBManager dbManager = DBManager.getInstance();
        try (Connection conn = DriverManager.getConnection(url, username, password);) {
            GradeBook gradeBook = new GradeBook();
            gradeBook.setGrade(90);
            gradeBook.setIdStudent(7L);
            gradeBook.setIdCourse(5L);
            Assertions.assertTrue(dbManager.updateGradeStatus(conn, gradeBook));
        } catch (
                SQLException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void shouldReturnFutureCourses() throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        Class.forName("com.mysql.cj.jdbc.Driver").getDeclaredConstructor().newInstance();
        DBManager dbManager = DBManager.getInstance();
        try (Connection conn = DriverManager.getConnection(url, username, password);) {
            List<GradeBook> gradeBook = new ArrayList<>();

            Assertions.assertEquals(gradeBook, dbManager.findFutureCourse(conn, 7L));
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Test
    public void shouldInsertStuntToGradebook() throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        Class.forName("com.mysql.cj.jdbc.Driver").getDeclaredConstructor().newInstance();
        DBManager dbManager = DBManager.getInstance();
        try (Connection conn = DriverManager.getConnection(url, username, password);) {
            Profile profile = new Profile();
            profile.setIdProfile(7L);
            profile.setLogin("student25");
            profile.setPassword("student25");
            profile.setRole("STUDENT");
            profile.setEmail("student25@gmail.com");
            profile.setTelephone("380995764563");

//            Assertions.assertTrue(dbManager.addStudentToCourse(conn, profile, 4L));
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Test
    void getListAvailableCourses() throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        Class.forName("com.mysql.cj.jdbc.Driver").getDeclaredConstructor().newInstance();
        DBManager dbManager = DBManager.getInstance();
        try (Connection conn = DriverManager.getConnection(url, username, password);) {
            List<Course> courseList = new ArrayList<>();
            courseList.add(new Course());

            Assertions.assertEquals(courseList, dbManager.getListAvailableCourses(conn));
        }catch (SQLException e) {
            e.printStackTrace();
        }
    }

}

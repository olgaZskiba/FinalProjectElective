package com.olgaskyba.elective.db;

import com.olgaskyba.elective.model.GradeBook;
import com.olgaskyba.elective.model.ProfileCourse;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;
import org.mockito.Mockito;

import java.sql.*;
import java.util.List;

import static org.mockito.Mockito.*;

public class DBManagerGradeBookTest {
    private ResultSet resultSet;
    private Statement statement;
    private Connection connection;
    private DBManager dbManager;
    private PreparedStatement preparedStatement;
    private static MockedStatic<DBManager> managerMockedStatic;

    @BeforeAll
    public static void setUoGlobal() {
        managerMockedStatic = mockStatic(DBManager.class);
    }

    @AfterAll
    public static void tearDown(){
        managerMockedStatic.close();
    }

    @BeforeEach
    public void setUp() throws SQLException {
        resultSet = mock(ResultSet.class);
        when(resultSet.next()).thenReturn(true).thenReturn(true).thenReturn(false);
        when(resultSet.getLong("id_course")).thenReturn(1L).thenReturn(2L);
        when(resultSet.getString("start_day_course")).thenReturn("2022-01-09").thenReturn("2022-01-18");
        when(resultSet.getString("course_name")).thenReturn("student1").thenReturn("student12");
        when(resultSet.getString("duration")).thenReturn("student1").thenReturn("student12");

        preparedStatement = mock(PreparedStatement.class);
        statement = mock(Statement.class);
        connection = mock(Connection.class);
        dbManager = mock(DBManager.class);
        managerMockedStatic.when(DBManager::getInstance).thenReturn(dbManager);
    }


    @Test
    public void findProfileCourseByLogin() throws SQLException {
        Mockito.reset(resultSet);
        resultSet = mock(ResultSet.class);
        when(resultSet.next()).thenReturn(true).thenReturn(false);
        when(resultSet.getLong(1)).thenReturn(1L);
        when(resultSet.getDate(2)).thenReturn(Date.valueOf("2021-12-29"));
        when(resultSet.getString(3)).thenReturn("C++ Advantage");
        when(resultSet.getInt(4)).thenReturn(24);
        managerMockedStatic.when(DBManager::getInstance).thenReturn(dbManager);
        when(connection.prepareStatement("SELECT profile_course.id_course, profile_course.start_day_course, c.course_name, c.duration FROM profile_course JOIN course c on c.id_course = profile_course.id_course WHERE profile_course.id_prifile=(SELECT id_prifile FROM profile WHERE login=?)")).thenReturn(preparedStatement);
        when(preparedStatement.executeQuery()).thenReturn(resultSet);
        when(dbManager.findProfileCourseByLogin(connection, "teacher1")).thenCallRealMethod();

        List<ProfileCourse> profileCourseList = dbManager.findProfileCourseByLogin(connection, "teacher1");
        profileCourseList.forEach(System.out::println);
}

    @Test
    void findStudentsGradeBookById() throws SQLException {
        Mockito.reset(resultSet);
        resultSet = mock(ResultSet.class);
        when(resultSet.next()).thenReturn(true).thenReturn(true).thenReturn(false);
        when(resultSet.getLong(1)).thenReturn(1L).thenReturn(2L);
        when(resultSet.getString(2)).thenReturn("student1").thenReturn("student2");
        when(resultSet.getString(3)).thenReturn("student1").thenReturn("student2");
        when(resultSet.getLong(4)).thenReturn(1L).thenReturn(1L);
        when(resultSet.getString(5)).thenReturn("Java Basic").thenReturn("Java Basic");
        when(resultSet.getString(6)).thenReturn("2020-11-12").thenReturn("2020-11-12");
        when(resultSet.getInt(7)).thenReturn(100).thenReturn(100);
        when(connection.prepareStatement("SELECT gradebook.student_id_prifile, p.name, p.surname, gradebook.course_id_course, c.course_name, pc.start_day_course, gradebook.grade FROM gradebook JOIN profile p on p.id_prifile = gradebook.student_id_prifile JOIN course c on c.id_course = gradebook.course_id_course JOIN profile_course pc on c.id_course = pc.id_course where gradebook.course_id_course=?")).thenReturn(preparedStatement);
        when(preparedStatement.executeQuery()).thenReturn(resultSet);
        when(dbManager.findStudentsGradeBookById(connection, 1L)).thenCallRealMethod();

        List<GradeBook> gradeBookList = dbManager.findStudentsGradeBookById(connection, 1L);
        gradeBookList.forEach(System.out::println);
    }

    @Test
    void findStudentGradeBookByIdCourseIdStudent() throws SQLException {
        Mockito.reset(resultSet);
        resultSet = mock(ResultSet.class);
        when(resultSet.next()).thenReturn(true).thenReturn(true).thenReturn(false);
        when(resultSet.getInt(3)).thenReturn(100).thenReturn(100);
        when(resultSet.getString(1)).thenReturn("student1").thenReturn("student2");
        when(resultSet.getString(2)).thenReturn("student1").thenReturn("student2");
        when(connection.prepareStatement("SELECT p.name, p.surname, gradebook.grade FROM gradebook JOIN profile p on p.id_prifile = gradebook.student_id_prifile where gradebook.course_id_course=? AND gradebook.student_id_prifile=?")).thenReturn(preparedStatement);
        when(preparedStatement.executeQuery()).thenReturn(resultSet);
        when(dbManager.findStudentGradeBookByIdCourseIdStudent(connection, 1L, 1L)).thenCallRealMethod();

        GradeBook gradeBook = dbManager.findStudentGradeBookByIdCourseIdStudent(connection, 1L, 1L);
        System.out.println(gradeBook);
    }
}

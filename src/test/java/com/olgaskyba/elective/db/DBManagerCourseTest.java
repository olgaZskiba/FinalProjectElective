package com.olgaskyba.elective.db;

import com.olgaskyba.elective.model.Course;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;
import org.mockito.Mockito;

import java.sql.*;
import java.util.List;

import static org.mockito.Mockito.*;

public class DBManagerCourseTest {
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
        when(resultSet.getString("course_name")).thenReturn("JAVA Basic").thenReturn("C++ Basic");
        when(resultSet.getLong("course_topic")).thenReturn(1L).thenReturn(2L);
        when(resultSet.getInt("duration")).thenReturn(12).thenReturn(12);

        statement = mock(Statement.class);
        preparedStatement = mock(PreparedStatement.class);
        connection = mock(Connection.class);
        dbManager = mock(DBManager.class);
    }

    @Test
    void shouldGetListCourses() throws SQLException {
        when(statement.executeQuery("SELECT * FROM course")).thenReturn(resultSet);
        when(connection.createStatement()).thenReturn(statement);
        when(dbManager.getListCourses(connection)).thenCallRealMethod();

        List<Course> course = dbManager.getListCourses(connection);
        course.forEach(System.out::println);

    }

    @Test
    void findCourseById() throws SQLException {
        when(preparedStatement.executeQuery()).thenReturn(resultSet);
        when(connection.prepareStatement("SELECT * FROM course WHERE id_course = ?")).thenReturn(preparedStatement);
        when(dbManager.findCourseById(connection, 1L)).thenCallRealMethod();

        Course course = dbManager.findCourseById(connection, 1L);
        System.out.println(course);

    }

    @Test
    void findAllCoursesMainMenu() throws SQLException {
        managerMockedStatic.when(DBManager::getInstance).thenReturn(dbManager);
        when(connection.prepareStatement("SELECT * FROM course LIMIT ? OFFSET ?")).thenReturn(preparedStatement);
        when(preparedStatement.executeQuery()).thenReturn(resultSet);
        when(dbManager.findAllCoursesMainMenu(connection, 2,1)).thenCallRealMethod();

        List<Course> listCourses = dbManager.findAllCoursesMainMenu(connection, 2,1);
        listCourses.forEach(System.out::println);
    }

    @Test
    void findAllJavaCourses() throws SQLException {
        Mockito.reset(resultSet);
        resultSet = mock(ResultSet.class);
        when(resultSet.next()).thenReturn(true).thenReturn(false);
        when(resultSet.getLong("id_course")).thenReturn(1L);
        when(resultSet.getString("course_name")).thenReturn("JAVA Basic");
        when(resultSet.getLong("course_topic")).thenReturn(1L);
        when(resultSet.getInt("duration")).thenReturn(12);

        managerMockedStatic.when(DBManager::getInstance).thenReturn(dbManager);
        when(connection.prepareStatement( "SELECT * FROM course WHERE course_topic=1 LIMIT ? OFFSET ?")).thenReturn(preparedStatement);
        when(preparedStatement.executeQuery()).thenReturn(resultSet);
        when(dbManager.findAllJavaCourses(connection, 2,1)).thenCallRealMethod();

        List<Course> listCourses = dbManager.findAllJavaCourses(connection, 2,1);
        listCourses.forEach(System.out::println);

    }
}

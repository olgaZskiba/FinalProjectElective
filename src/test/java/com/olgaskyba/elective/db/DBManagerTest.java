package com.olgaskyba.elective.db;

import com.olgaskyba.elective.model.ProfileCourse;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.sql.*;
import java.util.List;

import static org.mockito.Mockito.*;


class DBManagerTest {
    private ResultSet resultSet;
    private Statement statement;
    private Connection connection;
    private DBManager dbManager;
    private PreparedStatement preparedStatement;


    @Test
    void findTeacherForCourse() throws SQLException {
        Long id = 2L;

        resultSet = mock(ResultSet.class);
        when(resultSet.next()).thenReturn(true).thenReturn(true).thenReturn(false);
        when(resultSet.getLong(1)).thenReturn(1L).thenReturn(2L);
        when(resultSet.getString("profile.name")).thenReturn("Teacher1").thenReturn("Teacher2");
        when(resultSet.getString("profile.surname")).thenReturn("Teacher1").thenReturn("Teacher2");
        when(resultSet.getString("c.course_name")).thenReturn("JAVA Basic").thenReturn("C++ Basic");
        when(resultSet.getDate("pc.start_day_course")).thenReturn(Date.valueOf("2021-11-11")).thenReturn(Date.valueOf("2021-10-11"));
        when(resultSet.getInt("c.duration")).thenReturn(12).thenReturn(12);

        preparedStatement = mock(PreparedStatement.class);
        when(preparedStatement.executeQuery()).thenReturn(resultSet);

        connection = mock(Connection.class);
        when(connection.prepareStatement("SELECT profile.name, profile.surname,c.course_name,pc.start_day_course, c.duration from profile LEFT JOIN profile_course pc on profile.id_prifile = pc.id_prifile LEFT JOIN course c on c.id_course = pc.id_course WHERE pc.id_prifile=?")).thenReturn(preparedStatement);
        System.out.println(connection);

        dbManager = mock(DBManager.class);
        when(dbManager.findTeacherForCourse(connection, 2L)).thenCallRealMethod();

        ProfileCourse profileCourse = dbManager.findTeacherForCourse(connection, 2L);
        System.out.println(profileCourse);
    }


    @Test
    void getTeacherForAdminMenu() throws SQLException {
        resultSet = mock(ResultSet.class);
        when(resultSet.next()).thenReturn(true).thenReturn(true).thenReturn(false);
        when(resultSet.getLong(1)).thenReturn(1L).thenReturn(2L);
        when(resultSet.getLong(2)).thenReturn(1L).thenReturn(2L);
        when(resultSet.getString(3)).thenReturn("Teacher1").thenReturn("Teacher2");
        when(resultSet.getString(4)).thenReturn("Teacher1").thenReturn("Teacher2");
        when(resultSet.getString(5)).thenReturn("JAVA Basic").thenReturn("C++ Basic");
        when(resultSet.getDate(6)).thenReturn(Date.valueOf("2021-11-11")).thenReturn(Date.valueOf("2021-10-11"));
        when(resultSet.getInt(7)).thenReturn(12).thenReturn(12);

        statement = mock(Statement.class);
        when(statement.executeQuery("SELECT p.id_prifile, profile_course.id_course, p.name, p.surname, c.course_name, profile_course.start_day_course, c.duration from profile p " +
                "LEFT JOIN profile_course on p.id_prifile = profile_course.id_prifile " +
                        "LEFT JOIN course c on c.id_course = profile_course.id_course WHERE p.role='TEACHER'")).thenReturn(resultSet);

        connection = mock(Connection.class);
        when(connection.createStatement()).thenReturn(statement);

        dbManager = mock(DBManager.class);
        when(dbManager.getTeacherForAdminMenu(connection)).thenCallRealMethod();

        List<ProfileCourse> profileCourseList = dbManager.getTeacherForAdminMenu(connection);
        profileCourseList.forEach(System.out::println);
        Mockito.verify(dbManager, Mockito.times(1))
                .getTeacherForAdminMenu(Mockito.any());
    }


}
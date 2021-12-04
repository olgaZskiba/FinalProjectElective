package com.olgaskyba.elective.db;

import com.olgaskyba.elective.model.Profile;
import org.junit.jupiter.api.*;
import org.mockito.MockedStatic;
import org.mockito.Mockito;

import java.sql.*;
import java.util.List;

import static org.mockito.Mockito.*;

public class DBManagerProfileTest {
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
        when(resultSet.next()).thenReturn(true).thenReturn(true).thenReturn(true).thenReturn(true).thenReturn(false);
        when(resultSet.getLong("id_prifile")).thenReturn(1L).thenReturn(2L).thenReturn(3L).thenReturn(4L);
        when(resultSet.getString("login")).thenReturn("teacher1").thenReturn("teacher2").thenReturn("student1").thenReturn("student2");
        when(resultSet.getString("password")).thenReturn("teacher1").thenReturn("teacher2").thenReturn("student1").thenReturn("student2");
        when(resultSet.getString("email")).thenReturn("teacher1@gmail.com").thenReturn("teacher2@gmail.com").thenReturn("student1@gmail.com").thenReturn("student2@gmail.com");
        when(resultSet.getString("telephone")).thenReturn("380509834652").thenReturn("380509878652").thenReturn("380502134652").thenReturn("380503478652");
        when(resultSet.getString("name")).thenReturn("Teacher1").thenReturn("Teacher2").thenReturn("Student1").thenReturn("Student2");
        when(resultSet.getString("surname")).thenReturn("Teacher1").thenReturn("Teacher2").thenReturn("Student1").thenReturn("Student2");
        when(resultSet.getString("role")).thenReturn("TEACHER").thenReturn("TEACHER").thenReturn("STUDENT").thenReturn("STUDENT");
        when(resultSet.getInt("block_status")).thenReturn(0).thenReturn(0);

        preparedStatement = mock(PreparedStatement.class);
        statement = mock(Statement.class);
        connection = mock(Connection.class);
        dbManager = mock(DBManager.class);

    }


    @Test
    void validateProfileByLoginAndPassword() throws SQLException {
        when(preparedStatement.executeQuery()).thenReturn(resultSet);
        when(connection.prepareStatement("SELECT * FROM profile WHERE login = ? AND password = ?")).thenReturn(preparedStatement);
        when(dbManager.validateProfileByLoginAndPassword(connection, "teacher1", "teacher1")).thenCallRealMethod();

        Profile profile = dbManager.validateProfileByLoginAndPassword(connection, "teacher1", "teacher1");
        System.out.println(profile);
    }

    @Test
    void findAllProfileStudents() throws SQLException {
        Mockito.reset(resultSet);

        when(resultSet.next()).thenReturn(true).thenReturn(true).thenReturn(false);
        when(resultSet.getLong("id_prifile")).thenReturn(3L).thenReturn(4L);
        when(resultSet.getString("login")).thenReturn("student1").thenReturn("student2");
        when(resultSet.getString("password")).thenReturn("student1").thenReturn("student2");
        when(resultSet.getString("email")).thenReturn("student1@gmail.com").thenReturn("student2@gmail.com");
        when(resultSet.getString("telephone")).thenReturn("380502134652").thenReturn("380503478652");
        when(resultSet.getString("name")).thenReturn("Student1").thenReturn("Student2");
        when(resultSet.getString("surname")).thenReturn("Student1").thenReturn("Student2");
        when(resultSet.getString("role")).thenReturn("STUDENT").thenReturn("STUDENT");
        when(resultSet.getInt("block_status")).thenReturn(0).thenReturn(0);

        when(connection.createStatement()).thenReturn(statement);
        when(statement.executeQuery("SELECT * FROM profile WHERE role ='STUDENT'")).thenReturn(resultSet);
        when(dbManager.findAllProfileStudents(connection)).thenCallRealMethod();

        List<Profile> profileList = dbManager.findAllProfileStudents(connection);
        profileList.forEach(System.out::println);

    }

    @Test
    void findProfileById() throws SQLException {
        Mockito.reset(resultSet);
        resultSet = mock(ResultSet.class);
        when(resultSet.next()).thenReturn(true).thenReturn(true).thenReturn(true).thenReturn(true).thenReturn(false);
        when(resultSet.getLong("id_prifile")).thenReturn(1L).thenReturn(2L).thenReturn(3L).thenReturn(4L);
        when(resultSet.getString("login")).thenReturn("teacher1").thenReturn("teacher2").thenReturn("student1").thenReturn("student2");
        when(resultSet.getString("password")).thenReturn("teacher1").thenReturn("teacher2").thenReturn("student1").thenReturn("student2");
        when(resultSet.getString("email")).thenReturn("teacher1@gmail.com").thenReturn("teacher2@gmail.com").thenReturn("student1@gmail.com").thenReturn("student2@gmail.com");
        when(resultSet.getString("telephone")).thenReturn("380509834652").thenReturn("380509878652").thenReturn("380502134652").thenReturn("380503478652");
        when(resultSet.getString("name")).thenReturn("Teacher1").thenReturn("Teacher2").thenReturn("Student1").thenReturn("Student2");
        when(resultSet.getString("surname")).thenReturn("Teacher1").thenReturn("Teacher2").thenReturn("Student1").thenReturn("Student2");
        when(resultSet.getString("role")).thenReturn("TEACHER").thenReturn("TEACHER").thenReturn("STUDENT").thenReturn("STUDENT");
        when(resultSet.getInt("block_status")).thenReturn(0).thenReturn(0);

        when(preparedStatement.executeQuery()).thenReturn(resultSet);
        when(connection.prepareStatement("SELECT * FROM profile WHERE id_prifile=?")).thenReturn(preparedStatement);
        when(dbManager.findProfileById(connection, 1L)).thenCallRealMethod();

        Profile profile = dbManager.findProfileById(connection, 1L);
        System.out.println(profile);

    }

    @Test
    void findProfileByLogin() throws SQLException {
        managerMockedStatic.when(()->DBManager.getInstance()).thenReturn(dbManager);

        when(connection.prepareStatement("SELECT * FROM profile WHERE login=?")).thenReturn(preparedStatement);
        when(preparedStatement.executeQuery()).thenReturn(resultSet);
        when(dbManager.findProfileByLogin(connection, "teacher1")).thenCallRealMethod();

        Profile profile = dbManager.findProfileByLogin(connection, "teacher1");
        System.out.println(profile);
    }

    @Test
    void findAllTeacherProfiles() throws SQLException {
        Mockito.reset(resultSet);
        resultSet = mock(ResultSet.class);
        when(resultSet.next()).thenReturn(true).thenReturn(true).thenReturn(false);
        when(resultSet.getLong("id_prifile")).thenReturn(1L).thenReturn(2L);
        when(resultSet.getString("login")).thenReturn("teacher1").thenReturn("teacher2");
        when(resultSet.getString("password")).thenReturn("teacher1").thenReturn("teacher2");
        when(resultSet.getString("email")).thenReturn("teacher1@gmail.com").thenReturn("teacher2@gmail.com");
        when(resultSet.getString("telephone")).thenReturn("380509834652").thenReturn("380509878652");
        when(resultSet.getString("name")).thenReturn("Teacher1").thenReturn("Teacher2");
        when(resultSet.getString("surname")).thenReturn("Teacher1").thenReturn("Teacher2");
        when(resultSet.getString("role")).thenReturn("TEACHER").thenReturn("TEACHER");
        when(resultSet.getInt("block_status")).thenReturn(0).thenReturn(0);
        when(connection.prepareStatement("SELECT * FROM profile WHERE role='TEACHER' LIMIT ? OFFSET ?")).thenReturn(preparedStatement);
        when(preparedStatement.executeQuery()).thenReturn(resultSet);
        when(dbManager.findAllTeacherProfiles(connection, 2,1)).thenCallRealMethod();

        List<Profile> profileList = dbManager.findAllTeacherProfiles(connection, 2,1);
        profileList.forEach(System.out::println);
    }




}

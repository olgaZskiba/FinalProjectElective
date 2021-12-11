package com.olgaskyba.elective.db;

import com.olgaskyba.elective.model.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Collections;
import java.util.List;

public class DBManager {

    private static final Logger log = LogManager.getLogger(DBManager.class);

    String errMess = "";

    private static final String SQL_INSERT_PROFILE = "INSERT INTO profile (id_prifile, login , password, email, telephone, name, surname, role, block_status) VALUES (DEFAULT,?,?,?,?,?,?,?,DEFAULT)";
    private static final String SQL_GET_PROFILE_BY_LOGIN_PASSWORD = "SELECT * FROM profile WHERE login = ? AND password = ?";
    private static final String SQL_GET_LIST_TOPIC = "SELECT name FROM topic LIMIT ? OFFSET ?";
    private static final String SQL_SELECT_TEACHER_AND_ASSIGNED_COURSES =
            "SELECT p.id_prifile, profile_course.id_course, p.name, p.surname, c.course_name, profile_course.start_day_course, c.duration from profile p " +
                    "LEFT JOIN profile_course on p.id_prifile = profile_course.id_prifile " +
                    "LEFT JOIN course c on c.id_course = profile_course.id_course WHERE p.role='TEACHER'";
    //    SELECT profile.id_prifile, profile.name, profile.surname, pc.id_course, pc.start_day_course, c.course_name
//    FROM profile LEFT JOIN profile_course pc on profile.id_prifile = pc.id_prifile left join  course c on pc.id_course = c.id_course where profile.role = 'TEACHER';
    private static final String SQL_FIND_TEACHER_FOR_NEW_COURSE = "SELECT profile.name, profile.surname,c.course_name,pc.start_day_course, c.duration from profile LEFT JOIN profile_course pc on profile.id_prifile = pc.id_prifile LEFT JOIN course c on c.id_course = pc.id_course WHERE pc.id_prifile=?";
    private static final String SQL_UPDATE_COURSE_FOR_TEACHER = "UPDATE profile_course SET id_course=?, start_day_course=? WHERE id_prifile=?";
    private static final String SQL_INSERT_COURSE = "INSERT INTO course (id_course, course_name, course_topic, duration) VALUES (DEFAULT, ?, ?, ?)";
    private static final String SQL_SELECT_ALL_COURSES = "SELECT * FROM course";
    private static final String SQL_SELECT_ALL_AVAILABLE_COURSES = "SELECT * FROM course WHERE id_course NOT IN(SELECT id_course FROM profile_course WHERE id_course IS NOT NULL);";
    private static final String SQL_SELECT_COURSE_BY_ID = "SELECT course.id_course, course.course_name, course.course_topic, course.duration, cd.course_info, cd.course_image FROM course LEFT JOIN course_description cd on course.id_course = cd.course_description_id_course WHERE id_course = ?";
    //"SELECT * FROM course WHERE id_course = ?";
    private static final String SQL_UPDATE_COURSE_DESCRIPTION_ONLY_INFO = "UPDATE course_description SET course_info =? WHERE course_description_id_course = ?";
    private static final String SQL_UPDATE_COURSE_WITH_IMAGE = "UPDATE course SET course_name = ?, course_topic = ?, duration = ? WHERE id_course =?";
    private static final String SQL_UPDATE_DESCRIPTION_COURSE_WITH_IMAGE = "UPDATE course_description SET course_info =?, course_image =? WHERE course_description_id_course = ?";
    private static final String SQL_DELETE_COURSE = "DELETE FROM course WHERE id_course=?";
    private static final String SQL_FIND_ALL_STUDENTS = "SELECT * FROM profile WHERE role ='STUDENT'";
    private static final String SQL_GET_PROFILE_BY_ID = "SELECT * FROM profile WHERE id_prifile=?";
    private static final String SQL_UPDATE_BLOCK_STATUS_PROFILE = "UPDATE profile SET block_status=? WHERE id_prifile=?";
    private static final String SQL_FIND_COURSES_FOR_TEACHER_MENU = "SELECT profile_course.id_course, profile_course.start_day_course, c.course_name, c.duration FROM profile_course JOIN course c on c.id_course = profile_course.id_course WHERE profile_course.id_prifile=(SELECT id_prifile FROM profile WHERE login=?)";
    private static final String SQL_FIND_STUDENTS_GRADE_FROM_GREADEBOOK = "SELECT gradebook.student_id_prifile, p.name, p.surname, gradebook.course_id_course, c.course_name, pc.start_day_course, gradebook.grade FROM gradebook JOIN profile p on p.id_prifile = gradebook.student_id_prifile JOIN course c on c.id_course = gradebook.course_id_course JOIN profile_course pc on c.id_course = pc.id_course where gradebook.course_id_course=?";
//            "SELECT gradebook.student_id_prifile, p.name, p.surname, gradebook.course_id_course, gradebook.grade FROM gradebook JOIN profile p on p.id_prifile = gradebook.student_id_prifile where gradebook.course_id_course=?";
    private static final String SQL_FIND_STUDENT_FROM_GRADEBOOK_BY_ID_COURSE_ID_STUDENT = "SELECT p.name, p.surname, gradebook.grade FROM gradebook JOIN profile p on p.id_prifile = gradebook.student_id_prifile where gradebook.course_id_course=? AND gradebook.student_id_prifile=?";
    private static final String SQL_UPDATE_GRADE_IN_GRADEBOOK = "UPDATE gradebook SET grade=? WHERE course_id_course=? AND student_id_prifile =?";
    private static final String SQL_GET_PROFILE_BY_LOGIN = "SELECT * FROM profile WHERE login=?";
    private static final String SQL_FIND_FUTURE_COURSE = "SELECT gradebook.course_id_course, c.course_name, pc.start_day_course, c.duration, gradebook.grade FROM gradebook JOIN course c on c.id_course = gradebook.course_id_course JOIN profile_course pc on c.id_course = pc.id_course WHERE gradebook.student_id_prifile=? AND pc.start_day_course > CURRENT_DATE";
    private static final String SQL_FIND_CURRENT_COURSE = "SELECT gradebook.course_id_course, c.course_name, pc.start_day_course, c.duration, gradebook.grade FROM gradebook JOIN course c on c.id_course = gradebook.course_id_course JOIN profile_course pc on c.id_course = pc.id_course WHERE gradebook.student_id_prifile=? AND CURRENT_DATE BETWEEN pc.start_day_course AND pc.start_day_course + INTERVAL c.duration*7 DAY";
    private static final String SQL_FIND_PAST_COURSE = "SELECT gradebook.course_id_course, c.course_name, pc.start_day_course, c.duration, gradebook.grade FROM gradebook JOIN course c on c.id_course = gradebook.course_id_course JOIN profile_course pc on c.id_course = pc.id_course WHERE gradebook.student_id_prifile=? AND CURRENT_DATE > pc.start_day_course + INTERVAL c.duration*7 DAY";
    private static final String SQL_FIND_ALL_COURSES_FOR_MAIN_MENU = "SELECT * FROM course LIMIT ? OFFSET ?";
    private static final String SQL_FIND_ALL_PROFILE_COURSES_FOR_MAIN_MENU = "SELECT profile_course.id_course, profile_course.start_day_course, c.course_name FROM profile_course JOIN course c on c.id_course = profile_course.id_course WHERE profile_course.start_day_course > CURRENT_DATE LIMIT ? OFFSET ?";
    private static final String SQL_GET_SIZE_COURSES = "SELECT COUNT(*) FROM course";
    private static final String SQL_FIND_ALL_COURSES_BY_A_TO_Z = "SELECT * FROM course ORDER BY course_name LIMIT ? OFFSET ?";
    private static final String SQL_FIND_ALL_COURSES_BY_Z_TO_A = "SELECT * FROM course ORDER BY course_name DESC LIMIT ? OFFSET ?";
    private static final String SQL_FIND_ALL_COURSES_BY_DURATION = "SELECT course.id_course, course.course_name, course.course_topic, course.duration, cd.course_info, cd.course_image FROM course LEFT JOIN course_description cd on course.id_course = cd.course_description_id_course ORDER BY course.duration LIMIT ? OFFSET ?";
    //"SELECT * FROM course ORDER BY duration LIMIT ? OFFSET ?";
    private static final String SQL_FIND_ALL_COURSES_BY_STUDENTS_COUNT = "SELECT course.id_course, course.course_name, COUNT(g.student_id_prifile) FROM course JOIN gradebook g on course.id_course = g.course_id_course group by g.course_id_course LIMIT ? OFFSET ?";
    private static final String SQL_GET_SIZE_TOPIC = "SELECT COUNT(*) FROM topic";
    private static final String SQL_FIND_ALL_JAVA_COURSES = "SELECT * FROM course WHERE course_topic=1 LIMIT ? OFFSET ?";
    private static final String SQL_GET_SIZE_COURSES_JAVA_TOPIC = "SELECT COUNT(*) FROM course WHERE course_topic=1";
    private static final String SQL_FIND_ALL_DOT_NET_COURSES = "SELECT * FROM course WHERE course_topic=3 LIMIT ? OFFSET ?";
    private static final String SQL_GET_SIZE_COURSES_DOT_NET_TOPIC = "SELECT COUNT(*) FROM course WHERE course_topic=3";
    private static final String SQL_FIND_ALL_CPLUS_COURSES = "SELECT * FROM course WHERE course_topic=2 LIMIT ? OFFSET ?";
    private static final String SQL_GET_SIZE_COURSES_CPLUS_TOPIC = "SELECT COUNT(*) FROM course WHERE course_topic=2";
    private static final String SQL_FIND_ALL_JAVASCRIPT_COURSES = "SELECT * FROM course WHERE course_topic=4 LIMIT ? OFFSET ?";
    private static final String SQL_GET_SIZE_COURSES_JAVASCRIPT_TOPIC = "SELECT COUNT(*) FROM course WHERE course_topic=4";
    private static final String SQL_FIND_ALL_TEACHER_PROFILE = "SELECT * FROM profile WHERE role='TEACHER' LIMIT ? OFFSET ?";
    private static final String SQL_GET_SIZE_TEACHER_PROFILE = "SELECT COUNT(*) FROM profile WHERE role='TEACHER'";
    private static final String SQL_FIND_ALL_ASSIGNED_COURSES_TO_TEACHER = "SELECT profile_course.id_course, p.name, p.surname, c.course_name, profile_course.start_day_course, c.duration FROM profile_course Join profile p on p.id_prifile = profile_course.id_prifile JOIN course c on c.id_course = profile_course.id_course where profile_course.id_prifile=? LIMIT ? OFFSET ?";
    private static final String SQL_GET_SIZE_COURSES_ASSIGNED_TO_TEACHER = "SELECT COUNT(*) FROM profile_course WHERE id_prifile=?";
    private static final String SQL_INSERT_PROFILE_COURSE = "INSERT INTO profile_course(id_prifile) VALUES (?)";
    private static final String SQL_INSERT_PROFILE_TO_GRADEBOOK = "INSERT INTO gradebook SET student_id_prifile=(SELECT id_prifile FROM profile WHERE id_prifile=?), course_id_course=(SELECT id_course FROM course WHERE id_course=?)";
    private static final String SQL_GET_TOPIC_ID_BY_TOPIC_NAME = "SELECT * FROM topic WHERE name=?";
    private static final String SQL_SELECT_COURSE_BY_COURSE_NAME = "SELECT  * FROM course WHERE course_name=?";
    private static final String SQL_INSERT_COURSE_DESCRIPTION = "INSERT INTO course_description(course_description_id_course, course_info, course_image) VALUES ((SELECT id_course FROM course WHERE course_name=?),?,?)";
    private static final String SQL_FIND_DESCRIPTION_COURSE_BY_NAME ="SELECT * FROM course_description WHERE course_description_id_course=(SELECT id_course FROM course WHERE course_name=?)";


    private static DBManager instance;

    public static synchronized DBManager getInstance() {
        if (instance == null) {
            instance = new DBManager();
        }
        return instance;
    }

    private DBManager() {
    }

    public List<Course> getListCourses(Connection connection) {
        List<Course> courseList = new ArrayList<>();
        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(SQL_SELECT_ALL_COURSES)) {
            while (resultSet.next()) {
                Course course = new Course();
                course.setIdCourse(resultSet.getLong(1));
                course.setCourseName(resultSet.getString(2));
                course.setCourseTopic((resultSet.getLong(3)));
                course.setDuration((resultSet.getInt(4)));
                courseList.add(course);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return Collections.emptyList();
        }
        return courseList;
    }

    public List<Course> getListAvailableCourses(Connection connection) {
        List<Course> courseList = new ArrayList<>();
        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(SQL_SELECT_ALL_AVAILABLE_COURSES)) {
            while (resultSet.next()) {
                Course course = new Course();
                course.setIdCourse(resultSet.getLong(1));
                course.setCourseName(resultSet.getString(2));
                course.setCourseTopic((resultSet.getLong(3)));
                course.setDuration((resultSet.getInt(4)));
                courseList.add(course);
            }

        } catch (SQLException e) {
            e.printStackTrace();
            return Collections.emptyList();
        }
        return courseList;
    }


    public boolean createCourse(Connection connection, Course course) throws SQLException {
        try (PreparedStatement preparedStatement = connection.prepareStatement(SQL_INSERT_COURSE, Statement.RETURN_GENERATED_KEYS)) {

            preparedStatement.setString(1, course.getCourseName());
            preparedStatement.setLong(2, course.getCourseTopic());
            preparedStatement.setInt(3, course.getDuration());

            if (preparedStatement.executeUpdate() != 1) {
                return false;
            }
            try (ResultSet resultSet = preparedStatement.getGeneratedKeys()) {
                if (resultSet.next()) {
                    course.setIdCourse(resultSet.getLong(1));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }


    public boolean updateProfileCourse(Connection connection, ProfileCourse profileCourse) {
        try (PreparedStatement ps = connection.prepareStatement(SQL_UPDATE_COURSE_FOR_TEACHER)) {
            ps.setLong(1, profileCourse.getIdCourse());
            ps.setDate(2, profileCourse.getStartDayCourse());
            ps.setLong(3, profileCourse.getIdProfile());
            if (ps.executeUpdate() != 1) {
                return false;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }


    public ProfileCourse findTeacherForCourse(Connection conn, Long idTeach) {
        ProfileCourse profileCourse = new ProfileCourse();
        try (PreparedStatement ps = conn.prepareStatement(SQL_FIND_TEACHER_FOR_NEW_COURSE)) {
            ps.setLong(1, idTeach);
//            ps.setLong(2, idCour);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    profileCourse.setIdProfile(idTeach);
//                    profileCourse.setIdCourse(idCour);
                    profileCourse.setProfileName(rs.getString("profile.name"));
                    profileCourse.setProfileSurname(rs.getString("profile.surname"));
                    profileCourse.setCourseName(rs.getString("c.course_name"));
                    profileCourse.setStartDayCourse(rs.getDate("pc.start_day_course"));
                    profileCourse.setDuration(rs.getInt("c.duration"));
                }
                else return null;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return profileCourse;
    }

    public List<ProfileCourse> getTeacherForAdminMenu(Connection conn) {
        List<ProfileCourse> courses = new ArrayList<>();
        try (Statement statement = conn.createStatement();
             ResultSet resultSet = statement.executeQuery(SQL_SELECT_TEACHER_AND_ASSIGNED_COURSES)) {
            while (resultSet.next()) {
                ProfileCourse course = new ProfileCourse();
                course.setIdProfile(resultSet.getLong(1));
                course.setIdCourse(resultSet.getLong(2));
                course.setProfileName(resultSet.getString(3));
                course.setProfileSurname(resultSet.getString(4));
                course.setCourseName(resultSet.getString(5));
                course.setStartDayCourse(resultSet.getDate(6));
                course.setDuration(resultSet.getInt(7));
                courses.add(course);
            }
        } catch (SQLException e) {
            return Collections.emptyList();
        }
        return courses;
    }

    public List<Topic> getListTopic(Connection conn, int offset, int limit) {
        List<Topic> topics = new ArrayList<>();
        try (PreparedStatement preparedStatement = conn.prepareStatement(SQL_GET_LIST_TOPIC)) {
            preparedStatement.setInt(1, limit);
            preparedStatement.setInt(2, offset);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    Topic topic = new Topic();
                    topic.setNameTopic(resultSet.getString("name"));
                    topics.add(topic);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return Collections.emptyList();
        }
        return topics;
    }


    public boolean createProfile(Connection con, Profile profile) throws SQLException {
        try (PreparedStatement ps = con.prepareStatement(SQL_INSERT_PROFILE, Statement.RETURN_GENERATED_KEYS);) {
            ps.setString(1, profile.getLogin());
            ps.setString(2, profile.getPassword());
            ps.setString(3, profile.getEmail());
            ps.setString(4, profile.getTelephone());
            ps.setString(5, profile.getName());
            ps.setString(6, profile.getSurname());
            if (profile.getRole() == null || profile.getRole().isEmpty()) {
                ps.setString(7, "STUDENT");
            } else {
                ps.setString(7, profile.getRole());
            }
            if (ps.executeUpdate() != 1) {
                return false;
            }
            try (ResultSet rs = ps.getGeneratedKeys()) {
                if (rs.next()) {
                    profile.setIdProfile(rs.getLong(1));
                } else {
                    throw new SQLException();
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public Profile validateProfileByLoginAndPassword(Connection con, String login, String password) {
        Profile profile = new Profile();
        try (PreparedStatement preparedStatement = con.prepareStatement(SQL_GET_PROFILE_BY_LOGIN_PASSWORD)) {
            preparedStatement.setString(1, login);
            preparedStatement.setString(2, password);
            try (ResultSet rs = preparedStatement.executeQuery();) {
                if (rs.next()) {
                    profile = extractProfile(rs);
//                    profile.setIdProfile(rs.getLong(1));
//                    profile.setLogin(rs.getString(2));
//                    profile.setPassword(rs.getString(3));
//                    profile.setEmail(rs.getString(4));
//                    profile.setTelephone(rs.getString(5));
//                    profile.setName(rs.getString(6));
//                    profile.setSurname(rs.getString(7));
//                    profile.setRole(rs.getString(8));
//                    profile.setBlockStatus(rs.getInt(9));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return profile;
    }


    public Course findCourseById(Connection connection, Long id) {
        Course course = new Course();
        try (PreparedStatement preparedStatement = connection.prepareStatement(SQL_SELECT_COURSE_BY_ID)) {
            preparedStatement.setLong(1, id);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    course.setIdCourse(resultSet.getLong(1));
                    course.setCourseName(resultSet.getString(2));
                    course.setCourseTopic(resultSet.getLong(3));
                    course.setDuration(resultSet.getInt(4));

                    course.setInfoCourse(resultSet.getString(5));

                    if (resultSet.getBlob(6)!=null) {
                        InputStream inputStream = resultSet.getBlob(6).getBinaryStream(); //"course_image"
                        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
                        byte[] buffer = new byte[4096];
                        int bytesRead = -1;
                        while ((bytesRead = inputStream.read(buffer)) != -1) {
                            outputStream.write(buffer, 0, bytesRead);
                        }
                        byte[] imageBytes = outputStream.toByteArray();
                        String base64Image = Base64.getEncoder().encodeToString(imageBytes);
                        try {
                            inputStream.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        try {
                            outputStream.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }

                        course.setBase64Image(base64Image);
                        return course;
                    }else {
                        errMess = "This course does not have a image";
                        return course;
                    }
                }
            }
        } catch (SQLException | IOException e) {
            e.printStackTrace();
        }
        return null;
    }
//"UPDATE course, course_description SET course.course_name = ?, course.course_topic = ?, course.duration = ?, course_description.course_info =? WHERE course.id_course = course_description.course_description_id_course = ?"
    public boolean updateDescriptionCourseOnlyInfo(Connection connection, Course course) {
        try (PreparedStatement preparedStatement = connection.prepareStatement(SQL_UPDATE_COURSE_DESCRIPTION_ONLY_INFO)) {
            preparedStatement.setString(1, course.getInfoCourse());
            preparedStatement.setLong(2, course.getIdCourse());

            if (preparedStatement.executeUpdate() > 0) { //if (preparedStatement.executeUpdate() > 0) {
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return false;
    }
//    Profile profile = new Profile();
//        try (PreparedStatement preparedStatement = connection.prepareStatement(SQL_GET_PROFILE_BY_ID)) {
//        preparedStatement.setLong(1, id);
//        try (ResultSet resultSet = preparedStatement.executeQuery()) {
//            if (resultSet.next()) {
//                profile = extractProfile(resultSet);

    public DescriptionCourse findDescriptionCourseByName(Connection connection, Course course) throws SQLException {
        DescriptionCourse descriptionCourse = new DescriptionCourse();
        try (PreparedStatement preparedStatement = connection.prepareStatement(SQL_FIND_DESCRIPTION_COURSE_BY_NAME)) {
            preparedStatement.setString(1, course.getCourseName());
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    descriptionCourse.setIdCourseDescription(resultSet.getLong(1));
                    descriptionCourse.setCourseInfo(resultSet.getString(2));
                    if (resultSet.getBlob(3)!=null) {
                        InputStream inputStream = resultSet.getBlob(3).getBinaryStream(); //"course_image"
                        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
                        byte[] buffer = new byte[4096];
                        int bytesRead = -1;
                        while ((bytesRead = inputStream.read(buffer)) != -1) {
                            outputStream.write(buffer, 0, bytesRead);
                        }
                        byte[] imageBytes = outputStream.toByteArray();
                        String base64Image = Base64.getEncoder().encodeToString(imageBytes);
                        try {
                            inputStream.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        try {
                            outputStream.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        descriptionCourse.setCourseImg(base64Image);
                        return descriptionCourse;
                    }else {
                        errMess = "This course does not have a image";
                        return descriptionCourse;
                    }
                }
            }
        } catch (SQLException | IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public boolean updateCourse(Connection connection, Course course) throws SQLException {
            //course.course_name = ?, course.course_topic = ?, course.duration = ?, course_description.course_info =?, course_description.course_image =? WHERE course.id_course = ?"
            try (PreparedStatement preparedStatement = connection.prepareStatement(SQL_UPDATE_COURSE_WITH_IMAGE)) {
                preparedStatement.setString(1, course.getCourseName());
                preparedStatement.setLong(2, course.getCourseTopic());
                preparedStatement.setInt(3, course.getDuration());
                preparedStatement.setLong(4, course.getIdCourse());

                if (preparedStatement.executeUpdate() > 0) {
                    return true;
                }
            } catch (SQLException e) {
                e.printStackTrace();
                return false;
            }
        return false;
    }

    public boolean deleteCourseById(Connection connection, Long id) {
        try (PreparedStatement preparedStatement = connection.prepareStatement(SQL_DELETE_COURSE)) {
            preparedStatement.setLong(1, id);
            if (preparedStatement.executeUpdate() != 1) {
                return false;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public List<Profile> findAllProfileStudents(Connection connection) {
        List<Profile> profileList = new ArrayList<>();
        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(SQL_FIND_ALL_STUDENTS)) {
            while (resultSet.next()) {
                Profile profile = new Profile();
                profile = extractProfile(resultSet);
//                profile.setIdProfile(resultSet.getLong(1));
//                profile.setLogin(resultSet.getString(2));
//                profile.setPassword(resultSet.getString(3));
//                profile.setEmail(resultSet.getString(4));
//                profile.setTelephone(resultSet.getString(5));
//                profile.setName(resultSet.getString(6));
//                profile.setSurname(resultSet.getString(7));
//                profile.setRole(resultSet.getString(8));
//                profile.setBlockStatus(resultSet.getInt(9));
                profileList.add(profile);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return Collections.emptyList();
        }
        return profileList;
    }

    public Profile findProfileById(Connection connection, Long id) {
        Profile profile = new Profile();
        try (PreparedStatement preparedStatement = connection.prepareStatement(SQL_GET_PROFILE_BY_ID)) {
            preparedStatement.setLong(1, id);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    profile = extractProfile(resultSet);
//                    profile.setIdProfile(resultSet.getLong(1));
//                    profile.setLogin(resultSet.getString(2));
//                    profile.setPassword(resultSet.getString(3));
//                    profile.setEmail(resultSet.getString(4));
//                    profile.setTelephone(resultSet.getString(5));
//                    profile.setName(resultSet.getString(6));
//                    profile.setSurname(resultSet.getString(7));
//                    profile.setRole(resultSet.getString(8));
//                    profile.setBlockStatus(resultSet.getInt(9));
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return profile;
    }

    public boolean updateBlockStatus(Connection connection, Profile profile) {
        try (PreparedStatement preparedStatement = connection.prepareStatement(SQL_UPDATE_BLOCK_STATUS_PROFILE)) {
            preparedStatement.setInt(1, Integer.parseInt(String.valueOf(profile.getBlockStatus())));
            preparedStatement.setLong(2, profile.getIdProfile());
            if (preparedStatement.executeUpdate() != 1) {
                return false;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public List<ProfileCourse> findProfileCourseByLogin(Connection connection, String loginSession) {
        List<ProfileCourse> profileCourseList = new ArrayList<>();
        try (PreparedStatement preparedStatement = connection.prepareStatement(SQL_FIND_COURSES_FOR_TEACHER_MENU)) {
            preparedStatement.setString(1, loginSession);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    ProfileCourse profileCourse = new ProfileCourse();
                    profileCourse.setIdCourse(resultSet.getLong(1));
                    profileCourse.setStartDayCourse(resultSet.getDate(2));
                    profileCourse.setCourseName(resultSet.getString(3));
                    profileCourse.setDuration(resultSet.getInt(4));
                    profileCourseList.add(profileCourse);
                }

            } catch (SQLException e) {
                e.printStackTrace();
                return Collections.emptyList();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return profileCourseList;
    }

//   gradebook.student_id_prifile, p.name, p.surname, gradebook.course_id_course, c.course_name, pc.start_day_course, gradebook.grade
    public List<GradeBook> findStudentsGradeBookById(Connection connection, Long id) {
        List<GradeBook> gradeBookList = new ArrayList<>();
        try (PreparedStatement preparedStatement = connection.prepareStatement(SQL_FIND_STUDENTS_GRADE_FROM_GREADEBOOK)) {
            preparedStatement.setLong(1, id);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    GradeBook gradeBook = new GradeBook();
                    gradeBook.setIdStudent(resultSet.getLong(1));
                    gradeBook.setNameStudent(resultSet.getString(2));
                    gradeBook.setSurnameStudent(resultSet.getString(3));
                    gradeBook.setIdCourse(resultSet.getLong(4));
                    gradeBook.setCourseName(resultSet.getString(5));
                    gradeBook.setStartDayCourse(resultSet.getDate(6));
                    gradeBook.setGrade(resultSet.getInt(7));
                    gradeBookList.add(gradeBook);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return Collections.emptyList();
        }
        return gradeBookList;
    }

    //    p.name, p.surname, gradebook.grade FROM gradebook JOIN profile p on p.id_prifile = gradebook.student_id_prifile where gradebook.course_id_course=? AND gradebook.student_id_prifile=?
    public GradeBook findStudentGradeBookByIdCourseIdStudent(Connection connection, Long studentId, Long courseId) {
        GradeBook gradeBook = new GradeBook();
        try (PreparedStatement preparedStatement = connection.prepareStatement(SQL_FIND_STUDENT_FROM_GRADEBOOK_BY_ID_COURSE_ID_STUDENT)) {
            preparedStatement.setLong(1, courseId);
            preparedStatement.setLong(2, studentId);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    gradeBook.setIdCourse(courseId);
                    gradeBook.setIdStudent(studentId);
                    gradeBook.setNameStudent(resultSet.getString(1));
                    gradeBook.setSurnameStudent(resultSet.getString(2));
                    gradeBook.setGrade(resultSet.getInt(3));
                }
            } catch (SQLException e) {
                e.printStackTrace();
                return null;
            }
        }catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
        return gradeBook;
    }


    public boolean updateGradeStatus(Connection connection, GradeBook gradeBook) {
        try (PreparedStatement preparedStatement = connection.prepareStatement(SQL_UPDATE_GRADE_IN_GRADEBOOK)) {
            preparedStatement.setInt(1, gradeBook.getGrade());
            preparedStatement.setLong(2, gradeBook.getIdCourse());
            preparedStatement.setLong(3, gradeBook.getIdStudent());
            if (preparedStatement.executeUpdate() != 1) {
                return false;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public Profile findProfileByLogin(Connection connection, String loginSession) {
        Profile profile = new Profile();
        try (PreparedStatement preparedStatement = connection.prepareStatement(SQL_GET_PROFILE_BY_LOGIN)) {
            preparedStatement.setString(1, loginSession);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    profile=extractProfile(resultSet);
//                    profile.setIdProfile(resultSet.getLong(1));
//                    profile.setLogin(resultSet.getString(2));
//                    profile.setPassword(resultSet.getString(3));
//                    profile.setEmail(resultSet.getString(4));
//                    profile.setTelephone(resultSet.getString(5));
//                    profile.setName(resultSet.getString(6));
//                    profile.setSurname(resultSet.getString(7));
//                    profile.setRole(resultSet.getString(8));
//                    profile.setBlockStatus(resultSet.getInt(9));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return profile;
    }

    //    gradebook.course_id_course, c.course_name, pc.start_day_course, c.duration, gradebook.grade
    public List<GradeBook> findFutureCourse(Connection connection, Long idStudent) {
        List<GradeBook> profileCourseList = new ArrayList<>();
        try (PreparedStatement preparedStatement = connection.prepareStatement(SQL_FIND_FUTURE_COURSE);) {
            preparedStatement.setLong(1, idStudent);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    GradeBook gradeBook = new GradeBook();
                    gradeBook.setIdCourse(resultSet.getLong(1));
                    gradeBook.setCourseName(resultSet.getString(2));
                    gradeBook.setStartDayCourse(resultSet.getDate(3));
                    gradeBook.setDuration(resultSet.getInt(4));
                    gradeBook.setGrade(resultSet.getInt(5));
                    profileCourseList.add(gradeBook);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return Collections.emptyList();
        }
        return profileCourseList;
    }

    public List<GradeBook> findCurrentCourse(Connection connection, Long idStudent) {
        List<GradeBook> profileCourseList = new ArrayList<>();
        try (PreparedStatement preparedStatement = connection.prepareStatement(SQL_FIND_CURRENT_COURSE);) {
            preparedStatement.setLong(1, idStudent);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    GradeBook gradeBook = new GradeBook();
                    gradeBook.setIdCourse(resultSet.getLong(1));
                    gradeBook.setCourseName(resultSet.getString(2));
                    gradeBook.setStartDayCourse(resultSet.getDate(3));
                    gradeBook.setDuration(resultSet.getInt(4));
                    gradeBook.setGrade(resultSet.getInt(5));
                    profileCourseList.add(gradeBook);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return Collections.emptyList();
        }
        return profileCourseList;
    }

    public List<GradeBook> findPastCourse(Connection connection, Long idStudent) {
        List<GradeBook> profileCourseList = new ArrayList<>();
        try (PreparedStatement preparedStatement = connection.prepareStatement(SQL_FIND_PAST_COURSE)) {
            preparedStatement.setLong(1, idStudent);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    GradeBook gradeBook = new GradeBook();
                    gradeBook.setIdCourse(resultSet.getLong(1));
                    gradeBook.setCourseName(resultSet.getString(2));
                    gradeBook.setStartDayCourse(resultSet.getDate(3));
                    gradeBook.setDuration(resultSet.getInt(4));
                    gradeBook.setGrade(resultSet.getInt(5));
                    profileCourseList.add(gradeBook);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return Collections.emptyList();
        }
        return profileCourseList;
    }

    public List<Course> findAllCoursesMainMenu(Connection connection, int offset, int limit) {
        List<Course> courseList = new ArrayList<>();
        try (PreparedStatement preparedStatement = connection.prepareStatement(SQL_FIND_ALL_COURSES_FOR_MAIN_MENU)) {
            preparedStatement.setInt(1, limit);
            preparedStatement.setInt(2, offset);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    courseList.add(extractCourse(resultSet));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return courseList;
    }

    private Course extractCourse(ResultSet resultSet) throws SQLException {
        Course course = new Course();
        course.setIdCourse(resultSet.getLong("id_course"));
        course.setCourseName(resultSet.getString("course_name"));
        course.setCourseTopic(resultSet.getLong("course_topic"));
        course.setDuration(resultSet.getInt("duration"));
        return course;
    }

    public int findCoursesSizeMainMenu(Connection connection) {
        int size = 0;
        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(SQL_GET_SIZE_COURSES)) {
            if (resultSet.next()) {
                size = resultSet.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return size;
    }

    public List<Course> findAllCoursesSortByAtoZMainMenu(Connection connection, int offset, int limit) {
        List<Course> courseList = new ArrayList<>();
        try (PreparedStatement preparedStatement = connection.prepareStatement(SQL_FIND_ALL_COURSES_BY_A_TO_Z)) {
            preparedStatement.setInt(1, limit);
            preparedStatement.setInt(2, offset);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    courseList.add(extractCourse(resultSet));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return courseList;
    }

    public List<Course> findAllCoursesSortByZtoAmainMenu(Connection connection, int offset, int limit) {
        List<Course> courseList = new ArrayList<>();
        try (PreparedStatement preparedStatement = connection.prepareStatement(SQL_FIND_ALL_COURSES_BY_Z_TO_A)) {
            preparedStatement.setInt(1, limit);
            preparedStatement.setInt(2, offset);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    courseList.add(extractCourse(resultSet));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return courseList;
    }
//course.id_course, course.course_name, course.course_topic, course.duration, cd.course_info, cd.course_image
    public List<Course> findAllCoursesSortByDurationMainMenu(Connection connection, int offset, int limit) {
        List<Course> courseList = new ArrayList<>();
        try (PreparedStatement preparedStatement = connection.prepareStatement(SQL_FIND_ALL_COURSES_BY_DURATION)) {
            preparedStatement.setInt(1, limit);
            preparedStatement.setInt(2, offset);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
//                    courseList.add(extractCourse(resultSet));
                    Course course = new Course();
                    course.setIdCourse(resultSet.getLong(1));
                    course.setCourseName(resultSet.getString(2));
                    course.setCourseTopic(resultSet.getLong(3));
                    course.setDuration(resultSet.getInt(4));
                    course.setInfoCourse(resultSet.getString(5));

                    if (resultSet.getBlob(6)!=null) {
                        InputStream inputStream = resultSet.getBlob(6).getBinaryStream(); //"course_image"
                        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
                        byte[] buffer = new byte[4096];
                        int bytesRead = -1;
                        while ((bytesRead = inputStream.read(buffer)) != -1) {
                            outputStream.write(buffer, 0, bytesRead);
                        }
                        byte[] imageBytes = outputStream.toByteArray();
                        String base64Image = Base64.getEncoder().encodeToString(imageBytes);
                        inputStream.close();
                        outputStream.close();

                        course.setBase64Image(base64Image);
                        courseList.add(course);
                    }else {
                        course.setBase64Image(null);
                        courseList.add(course);
                    }
                }
            }
        } catch (SQLException | IOException e) {
            e.printStackTrace();
        }
        return courseList;
    }

    public List<GradeBook> findAllCoursesSortByStudentsMainMenu(Connection connection, int offset, int limit) {
        List<GradeBook> courseList = new ArrayList<>();
        try (PreparedStatement preparedStatement = connection.prepareStatement(SQL_FIND_ALL_COURSES_BY_STUDENTS_COUNT)) {
            preparedStatement.setInt(1, limit);
            preparedStatement.setInt(2, offset);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    GradeBook gradeBook = new GradeBook();
                    gradeBook.setIdCourse(resultSet.getLong(1));
                    gradeBook.setCourseName(resultSet.getString(2));
                    gradeBook.setStudentCount(resultSet.getInt(3));
                    courseList.add(gradeBook);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return courseList;
    }

    public int findTopicSizeMainMenu(Connection connection) {
        int size = 0;
        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(SQL_GET_SIZE_TOPIC)) {
            if (resultSet.next()) {
                size = resultSet.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return size;
    }

    public List<Course> findAllJavaCourses(Connection connection, int offset, int limit) {
        List<Course> courseList = new ArrayList<>();
        try (PreparedStatement preparedStatement = connection.prepareStatement(SQL_FIND_ALL_JAVA_COURSES)) {
            preparedStatement.setInt(1, limit);
            preparedStatement.setInt(2, offset);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    courseList.add(extractCourse(resultSet));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return courseList;
    }


    public int findCoursesSizeJavaTopic(Connection connection) {
        int size = 0;
        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(SQL_GET_SIZE_COURSES_JAVA_TOPIC)) {
            if (resultSet.next()) {
                size = resultSet.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return size;
    }

    public List<Course> findAllDotNetCourses(Connection connection, int offset, int limit) {
        List<Course> courseList = new ArrayList<>();
        try (PreparedStatement preparedStatement = connection.prepareStatement(SQL_FIND_ALL_DOT_NET_COURSES)) {
            preparedStatement.setInt(1, limit);
            preparedStatement.setInt(2, offset);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    courseList.add(extractCourse(resultSet));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return courseList;
    }

    public int findCoursesSizeDotNetTopic(Connection connection) {
        int size = 0;
        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(SQL_GET_SIZE_COURSES_DOT_NET_TOPIC)) {
            if (resultSet.next()) {
                size = resultSet.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return size;
    }

    public List<Course> findAllCPlusCourses(Connection connection, int offset, int limit) {
        List<Course> courseList = new ArrayList<>();
        try (PreparedStatement preparedStatement = connection.prepareStatement(SQL_FIND_ALL_CPLUS_COURSES)) {
            preparedStatement.setInt(1, limit);
            preparedStatement.setInt(2, offset);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    courseList.add(extractCourse(resultSet));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return courseList;
    }

    public int findCoursesSizeCPlusTopic(Connection connection) {
        int size = 0;
        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(SQL_GET_SIZE_COURSES_CPLUS_TOPIC)) {
            if (resultSet.next()) {
                size = resultSet.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return size;
    }

    public List<Course> findAllJavaScriptCourses(Connection connection, int offset, int limit) {
        List<Course> courseList = new ArrayList<>();
        try (PreparedStatement preparedStatement = connection.prepareStatement(SQL_FIND_ALL_JAVASCRIPT_COURSES)) {
            preparedStatement.setInt(1, limit);
            preparedStatement.setInt(2, offset);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    courseList.add(extractCourse(resultSet));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return courseList;
    }

    public int findCoursesSizeJavaScriptTopic(Connection connection) {
        int size = 0;
        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(SQL_GET_SIZE_COURSES_JAVASCRIPT_TOPIC)) {
            if (resultSet.next()) {
                size = resultSet.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return size;
    }


    public List<Profile> findAllTeacherProfiles(Connection connection, int offset, int limit) {
        List<Profile> profileList = new ArrayList<>();
        try (PreparedStatement preparedStatement = connection.prepareStatement(SQL_FIND_ALL_TEACHER_PROFILE)) {
            preparedStatement.setInt(1, limit);
            preparedStatement.setInt(2, offset);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    profileList.add(extractProfile(resultSet));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return profileList;
    }

    private Profile extractProfile(ResultSet resultSet) throws SQLException {
        Profile profile = new Profile();
        profile.setIdProfile(resultSet.getLong("id_prifile"));
        profile.setLogin(resultSet.getString("login"));
        profile.setPassword(resultSet.getString("password"));
        profile.setEmail(resultSet.getString("email"));
        profile.setTelephone(resultSet.getString("telephone"));
        profile.setName(resultSet.getString("name"));
        profile.setSurname(resultSet.getString("surname"));
        profile.setRole(resultSet.getString("role"));
        profile.setBlockStatus(resultSet.getInt("block_status"));
        return profile;
    }

    public int findTeacherProfileSize(Connection connection) {
        int size = 0;
        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(SQL_GET_SIZE_TEACHER_PROFILE)) {
            if (resultSet.next()) {
                size = resultSet.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return size;
    }

    public List<ProfileCourse> findAllAssignedCoursesToTeacher(Connection connection, int offset, int limit, Long id) {
        List<ProfileCourse> profileCourseList = new ArrayList<>();
        try (PreparedStatement preparedStatement = connection.prepareStatement(SQL_FIND_ALL_ASSIGNED_COURSES_TO_TEACHER)) {
            preparedStatement.setLong(1, id);
            preparedStatement.setInt(2, limit);
            preparedStatement.setInt(3, offset);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    ProfileCourse profileCourse = new ProfileCourse();
                    profileCourse.setIdProfile(id);
                    profileCourse.setIdCourse(resultSet.getLong(1));
                    profileCourse.setProfileName(resultSet.getString(2));
                    profileCourse.setProfileSurname(resultSet.getString(3));
                    profileCourse.setCourseName(resultSet.getString(4));
                    profileCourse.setStartDayCourse(resultSet.getDate(5));
                    profileCourse.setDuration(resultSet.getInt(6));
                    profileCourseList.add(profileCourse);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return profileCourseList;
    }

    public int findCoursesSizeAssignedToTeacher(Connection connection, Long id) {
        int size = 0;
        try (PreparedStatement preparedStatement = connection.prepareStatement(SQL_GET_SIZE_COURSES_ASSIGNED_TO_TEACHER)) {
            preparedStatement.setLong(1, id);
            try (ResultSet resultSet = preparedStatement.executeQuery()){
                if (resultSet.next()) {
                    size = resultSet.getInt(1);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return size;
    }

    public boolean createProfileCourse(Connection connection, Long idTeach) {
        try (PreparedStatement preparedStatement = connection.prepareStatement(SQL_INSERT_PROFILE_COURSE)){
            preparedStatement.setLong(1, idTeach);
            if (preparedStatement.executeUpdate() != 1){
                return false;
            }

    } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true;
}

    public boolean addStudentToCourse(Connection connection, Profile profile, Long idCourse) {
        try (PreparedStatement preparedStatement = connection.prepareStatement(SQL_INSERT_PROFILE_TO_GRADEBOOK)){
            preparedStatement.setLong(1, profile.getIdProfile());
            preparedStatement.setLong(2, idCourse);
            if (preparedStatement.executeUpdate() != 1) {
                return false;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public List<ProfileCourse> findAllProfileCourses(Connection connection, int offset, int limit) {
        List<ProfileCourse> courseList = new ArrayList<>();
        try (PreparedStatement preparedStatement = connection.prepareStatement(SQL_FIND_ALL_PROFILE_COURSES_FOR_MAIN_MENU)) {
            preparedStatement.setInt(1, limit);
            preparedStatement.setInt(2, offset);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    courseList.add(extractProfileCourse(resultSet));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return courseList;
    }

    private ProfileCourse extractProfileCourse(ResultSet resultSet) throws SQLException {
        ProfileCourse profileCourse = new ProfileCourse();
        profileCourse.setIdCourse(resultSet.getLong(1));
        profileCourse.setStartDayCourse(resultSet.getDate(2));
        profileCourse.setCourseName(resultSet.getString(3));
        return profileCourse;
    }

    public Topic findTopicIdByName(Connection connection, String topicCourse) {
        Topic topic = new Topic();
        try(PreparedStatement preparedStatement = connection.prepareStatement(SQL_GET_TOPIC_ID_BY_TOPIC_NAME)) {
            preparedStatement.setString(1, topicCourse);
            try (ResultSet resultSet = preparedStatement.executeQuery()){
                while (resultSet.next()){
                    topic.setTopicId(resultSet.getLong(1));
                    topic.setNameTopic(resultSet.getString(2));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return topic;
    }

    public Course findCourseByName(Connection connection, String courseName) {
        Course course=null;
        try (PreparedStatement preparedStatement = connection.prepareStatement(SQL_SELECT_COURSE_BY_COURSE_NAME)) {
            preparedStatement.setString(1, courseName);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    course.setIdCourse(resultSet.getLong("id_course"));
                    course.setCourseName(resultSet.getString("course_name"));
                    course.setCourseTopic(resultSet.getLong("course_topic"));
                    course.setDuration(resultSet.getInt("duration"));
                    return course;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public boolean createDescriptionCourse(Connection connection, String courseName, String infoCourse, InputStream inputStream) {
        try (PreparedStatement preparedStatement = connection.prepareStatement(SQL_INSERT_COURSE_DESCRIPTION)) {

            preparedStatement.setString(1, courseName);
            preparedStatement.setString(2, infoCourse);
            System.out.println(inputStream);
            preparedStatement.setBlob(3, inputStream);

            if (preparedStatement.executeUpdate() > 0) {
                return true;
            }
        } catch (SQLException e) {
            String errMess = "ERROR: " + e.getMessage();
            e.printStackTrace();
            return false;
        }
        return false;
    }


    public boolean updateDescriptionCourse(Connection connection, Course course, InputStream inputStream) throws SQLException {
        if (findDescriptionCourseByName(connection,course)==null) {
            if (createDescriptionCourse(connection, course.getCourseName(), course.getInfoCourse(), inputStream)){
                return true;
            }
        }else {
            //course.course_name = ?, course.course_topic = ?, course.duration = ?, course_description.course_info =?, course_description.course_image =? WHERE course.id_course = ?"
            try (PreparedStatement preparedStatement = connection.prepareStatement(SQL_UPDATE_DESCRIPTION_COURSE_WITH_IMAGE)) {
                preparedStatement.setString(1, course.getInfoCourse());
                preparedStatement.setBlob(2, inputStream);
                preparedStatement.setLong(3, course.getIdCourse());

                if (preparedStatement.executeUpdate() > 0) {
                    return true;
                }

            } catch (SQLException e) {
                e.printStackTrace();
                return false;
            }
        }
        return false;
    }
}

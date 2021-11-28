package com.olgaskyba.elective.logic.command;

import java.util.HashMap;
import java.util.Map;

public class CommandContainer {

    private static Map<String, Command> commands;

    static {
        commands = new HashMap<>();
        commands.put("login", new LoginCommand());
        commands.put("registration", new RegistrationCommand());
        commands.put("topicList", new SelectTopicCommand());
        commands.put("registrationTeacher", new RegistrationTeacherCommand());
        commands.put("teachersForAdminMenu", new AllTeachersForAdminMenuCommand());
        commands.put("editTeacherForCourse", new AssigningCourseToTeacherCommand());
        commands.put("updateCourseToTeacher", new UpdateCourseToTeacherCommand());
        commands.put("createCourse", new CreateCourseCommand());
        commands.put("allCourses", new AllCoursesCommand());
        commands.put("editCourse", new EditCourseCommand());
        commands.put("updateCourse", new UpdateCourseCommand());
        commands.put("deleteCourse", new DeleteCourseCommand());
        commands.put("approveDeleteCourse", new ApproveDeleteCourseCommand());
        commands.put("allStudents", new AllStudentsCommand());
        commands.put("editStudentBlockStatus", new EditBlockStatusToStudent());
        commands.put("updateBlockStatusProfile", new UpdateBlockStatusCommand());
        commands.put("coursesForTeacherMenu", new CoursesForTeacherMenu());
        commands.put("teacherMenuCoursesDetails", new TeacherMenuCourseDetails());
        commands.put("teacherMenuUpdateGrade", new TeacherMenuUpdateGrade());
        commands.put("studentMainMenu", new StudentMainMenuCommand());
        commands.put("studentMenuFutureCourses", new StudentMenuFutureCourses());
        commands.put("commonMainMenu", new CommonMainMenuCommand());
        commands.put("sortCoursesByAtoZ", new CommonMainMenuSortCoursesByAtoZCommand());
        commands.put("sortCoursesByZtoA", new CommonMainMenuSortCoursesByZtoACommand());
        commands.put("sortCoursesByDuration", new CommonMainMenuSortByDurationCommand());
        commands.put("sortCoursesByStudentsCount", new CommonMainMenuSortByStudentCountCommand());
        commands.put("teachersListMainMenu", new TeachersListMainMenuCommand());
        commands.put("teacherInfoMenu", new TeacherInfoMenuCommand());
        commands.put("adminInfoMenu", new AdminInfoMenuCommand());
        commands.put("teacherMenuApproveUpdateGrade", new TeacherMenuApproveUpdateGrade());
        commands.put("logout", new LogOutCommand());
        commands.put("joinToCourse", new JoinToCourse());
    }

    public static Command getCommand(String commandName){
        return commands.get(commandName);
    }

    private CommandContainer(){}

}

package com.olgaskyba.elective.model;

import java.io.Serializable;
import java.sql.Date;
import java.util.Objects;

public class Course implements Serializable {
    private Long idCourse;
    private String courseName;
    private int courseTopic;
//    private String startDayCourse;
    private int duration;
//    private Long teacherId;
//    private String teacherName;
//    private String teacherSurname;


    public Course(){ }


    public Long getIdCourse() {
        return idCourse;
    }

    public void setIdCourse(Long idCourse) {
        this.idCourse = idCourse;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public int getCourseTopic() {
        return courseTopic;
    }

    public void setCourseTopic(int courseTopic) {
        this.courseTopic = courseTopic;
    }

//    public String getStartDayCourse() {
//        return startDayCourse;
//    }
//
//    public void setStartDayCourse(String startDayCourse) {
//        this.startDayCourse = startDayCourse;
//    }


    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

//    public Long getTeacherId() {
//        return teacherId;
//    }
//
//    public void setTeacherId(Long teacherId) {
//        this.teacherId = teacherId;
//    }
//
//    public String getTeacherName() {
//        return teacherName;
//    }
//
//    public void setTeacherName(String teacherName) {
//        this.teacherName = teacherName;
//    }
//
//    public String getTeacherSurname() {
//        return teacherSurname;
//    }
//
//    public void setTeacherSurname(String teacherSurname) {
//        this.teacherSurname = teacherSurname;
//    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Course)) return false;
        Course course = (Course) o;
        return duration == course.duration && Objects.equals(idCourse, course.idCourse) && Objects.equals(courseName, course.courseName) && Objects.equals(courseTopic, course.courseTopic);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idCourse, courseName, courseTopic, duration);
    }

    @Override
    public String toString() {
        return "Course{" +
                "idCourse=" + idCourse +
                ", courseName='" + courseName + '\'' +
                ", courseTopic='" + courseTopic + '\'' +
                ", duration=" + duration +
                '}';
    }
}

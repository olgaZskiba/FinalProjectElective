package com.olgaskyba.elective.model;

import java.io.Serializable;
import java.util.Objects;

public class Course implements Serializable {
    private Long idCourse;
    private String courseName;
    private Long courseTopic;
    private int duration;

    private String infoCourse;
    private String base64Image;


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

    public Long getCourseTopic() {
        return courseTopic;
    }

    public void setCourseTopic(Long courseTopic) {
        this.courseTopic = courseTopic;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public String getInfoCourse() {
        return infoCourse;
    }

    public void setInfoCourse(String infoCourse) {
        this.infoCourse = infoCourse;
    }

    public String getBase64Image() {
        return base64Image;
    }

    public void setBase64Image(String base64Image) {
        this.base64Image = base64Image;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Course)) return false;
        Course course = (Course) o;
        return duration == course.duration && Objects.equals(idCourse, course.idCourse) && Objects.equals(courseName, course.courseName) && Objects.equals(courseTopic, course.courseTopic) && Objects.equals(infoCourse, course.infoCourse) && Objects.equals(base64Image, course.base64Image);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idCourse, courseName, courseTopic, duration, infoCourse, base64Image);
    }

    @Override
    public String toString() {
        return "Course{" +
                "idCourse=" + idCourse +
                ", courseName='" + courseName + '\'' +
                ", courseTopic=" + courseTopic +
                ", duration=" + duration +
                ", infoCourse='" + infoCourse + '\'' +
                ", base64Image='" + base64Image + '\'' +
                '}';
    }
}

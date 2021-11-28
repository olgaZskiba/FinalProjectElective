package com.olgaskyba.elective.model;

import java.io.Serializable;
import java.sql.Date;
import java.util.Objects;

public class GradeBook implements Serializable {
    private Long idStudent;
    private Long idCourse;
    private int grade;
    private String nameStudent;
    private String surnameStudent;
    private String courseName;
    private Date startDayCourse;
    private int duration;
    private int studentCount;

    public GradeBook(){}

    public int getStudentCount() {
        return studentCount;
    }

    public void setStudentCount(int studentCount) {
        this.studentCount = studentCount;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public Date getStartDayCourse() {
        return startDayCourse;
    }

    public void setStartDayCourse(Date startDayCourse) {
        this.startDayCourse = startDayCourse;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public Long getIdStudent() {
        return idStudent;
    }

    public void setIdStudent(Long idStudent) {
        this.idStudent = idStudent;
    }

    public Long getIdCourse() {
        return idCourse;
    }

    public void setIdCourse(Long idCourse) {
        this.idCourse = idCourse;
    }

    public int getGrade() {
        return grade;
    }

    public void setGrade(int grade) {
        this.grade = grade;
    }

    public String getNameStudent() {
        return nameStudent;
    }

    public void setNameStudent(String nameStudent) {
        this.nameStudent = nameStudent;
    }

    public String getSurnameStudent() {
        return surnameStudent;
    }

    public void setSurnameStudent(String surnameStudent) {
        this.surnameStudent = surnameStudent;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof GradeBook)) return false;
        GradeBook gradeBook = (GradeBook) o;
        return grade == gradeBook.grade && duration == gradeBook.duration && studentCount == gradeBook.studentCount && Objects.equals(idStudent, gradeBook.idStudent) && Objects.equals(idCourse, gradeBook.idCourse) && Objects.equals(nameStudent, gradeBook.nameStudent) && Objects.equals(surnameStudent, gradeBook.surnameStudent) && Objects.equals(courseName, gradeBook.courseName) && Objects.equals(startDayCourse, gradeBook.startDayCourse);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idStudent, idCourse, grade, nameStudent, surnameStudent, courseName, startDayCourse, duration, studentCount);
    }

    @Override
    public String toString() {
        return "GradeBook{" +
                "idStudent=" + idStudent +
                ", idCourse=" + idCourse +
                ", grade=" + grade +
                ", nameStudent='" + nameStudent + '\'' +
                ", surnameStudent='" + surnameStudent + '\'' +
                ", courseName='" + courseName + '\'' +
                ", startDayCourse=" + startDayCourse +
                ", duration=" + duration +
                ", studentCount=" + studentCount +
                '}';
    }
}

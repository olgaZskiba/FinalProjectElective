package com.olgaskyba.elective.model;

import java.io.InputStream;
import java.sql.Blob;
import java.util.Objects;

public class DescriptionCourse {

    private Long idCourseDescription;
    private String courseInfo;
    private String courseImg;

    public Long getIdCourseDescription() {
        return idCourseDescription;
    }

    public void setIdCourseDescription(Long idCourseDescription) {
        this.idCourseDescription = idCourseDescription;
    }

    public String getCourseInfo() {
        return courseInfo;
    }

    public void setCourseInfo(String courseInfo) {
        this.courseInfo = courseInfo;
    }

    public String getCourseImg() {
        return courseImg;
    }

    public void setCourseImg(String courseImg) {
        this.courseImg = courseImg;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof DescriptionCourse)) return false;
        DescriptionCourse that = (DescriptionCourse) o;
        return Objects.equals(idCourseDescription, that.idCourseDescription) && Objects.equals(courseInfo, that.courseInfo) && Objects.equals(courseImg, that.courseImg);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idCourseDescription, courseInfo, courseImg);
    }

    @Override
    public String toString() {
        return "DescriptionCourse{" +
                "idCourseDescription=" + idCourseDescription +
                ", courseInfo='" + courseInfo + '\'' +
                ", courseImg='" + courseImg + '\'' +
                '}';
    }
}

package com.olgaskyba.elective.model;

import java.sql.Date;
import java.util.Objects;

public class ProfileCourse {
    private Long idProfile;
    private Long idCourse;
    private String profileName;
    private String profileSurname;
    private String courseName;
    private Date startDayCourse;
    private int duration;


    public ProfileCourse(){}

    public ProfileCourse(String profileName, String profileSurname, String courseName, Date startDayCourse, int duration) {
        this.profileName = profileName;
        this.profileSurname = profileSurname;
        this.courseName = courseName;
        this.startDayCourse = startDayCourse;
        this.duration = duration;
    }

    public ProfileCourse(Long idProfile, Long idCourse, Date startDayCourse) {
        this.idProfile = idProfile;
        this.idCourse = idCourse;
        this.startDayCourse = startDayCourse;
    }

    public Long getIdProfile() {
        return idProfile;
    }

    public void setIdProfile(Long idProfile) {
        this.idProfile = idProfile;
    }

    public Long getIdCourse() {
        return idCourse;
    }

    public void setIdCourse(Long idCourse) {
        this.idCourse = idCourse;
    }

    public String getProfileName() {
        return profileName;
    }

    public void setProfileName(String profileName) {
        this.profileName = profileName;
    }

    public String getProfileSurname() {
        return profileSurname;
    }

    public void setProfileSurname(String profileSurname) {
        this.profileSurname = profileSurname;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ProfileCourse)) return false;
        ProfileCourse that = (ProfileCourse) o;
        return duration == that.duration && Objects.equals(profileName, that.profileName) && Objects.equals(profileSurname, that.profileSurname) && Objects.equals(courseName, that.courseName) && Objects.equals(startDayCourse, that.startDayCourse);
    }

    @Override
    public int hashCode() {
        return Objects.hash(profileName, profileSurname, courseName, startDayCourse, duration);
    }

    @Override
    public String toString() {
        return "ProfileCourse{" +
                "idProfile=" + idProfile +
                ", idCourse=" + idCourse +
                ", profileName='" + profileName + '\'' +
                ", profileSurname='" + profileSurname + '\'' +
                ", courseName='" + courseName + '\'' +
                ", startDayCourse=" + startDayCourse +
                ", duration=" + duration +
                '}';
    }
}

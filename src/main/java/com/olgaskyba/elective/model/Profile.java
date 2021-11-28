package com.olgaskyba.elective.model;

import java.io.Serializable;
import java.util.Objects;

public class Profile implements Serializable {

    private Long idProfile;
    private String login;
    private String password;
    private String email;
    private String telephone;
    private String name;
    private String surname;
    private String role;
    private int blockStatus;
    private String blockStringStatus;

    public String getBlockStringStatus() {
        return blockStringStatus;
    }

    public void setBlockStringStatus(String blockStringStatus) {
        this.blockStringStatus = blockStringStatus;
    }

    public Long getIdProfile() {
        return idProfile;
    }

    public void setIdProfile(Long idProfile) {
        this.idProfile = idProfile;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public int getBlockStatus() {
        return blockStatus;
    }

    public void setBlockStatus(int blockStatus) {
        this.blockStatus = blockStatus;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Profile)) return false;
        Profile profile = (Profile) o;
        return blockStatus == profile.blockStatus && Objects.equals(idProfile, profile.idProfile) && Objects.equals(login, profile.login) && Objects.equals(password, profile.password) && Objects.equals(email, profile.email) && Objects.equals(telephone, profile.telephone) && Objects.equals(name, profile.name) && Objects.equals(surname, profile.surname) && role == profile.role;
    }

    @Override
    public int hashCode() {
        return Objects.hash(idProfile, login, password, email, telephone, name, surname, role, blockStatus);
    }

    @Override
    public String toString() {
        return "Profile{" +
                "idProfile=" + idProfile +
                ", login='" + login + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                ", telephone='" + telephone + '\'' +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", role=" + role +
                ", blockStatus=" + blockStatus +
                '}';
    }

}

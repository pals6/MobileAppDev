package com.assignment.assignment08;

import java.io.Serializable;

public class User implements Serializable {
    String name, email,phone, state,dob,maritalStatus,eduLevel;
    int age;

    public User(String name, String email, String phone, String state, String dob,String maritalStatus,String eduLevel) {
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.state = state;
        this.dob = dob;
        this.maritalStatus=maritalStatus;
        this.eduLevel=eduLevel;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public String getMaritalStatus() {
        return maritalStatus;
    }

    public void setMaritalStatus(String maritalStatus) {
        this.maritalStatus = maritalStatus;
    }

    public String getEduLevel() {
        return eduLevel;
    }

    public void setEduLevel(String eduLevel) {
        this.eduLevel = eduLevel;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}

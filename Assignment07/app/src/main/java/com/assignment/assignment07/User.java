package com.assignment.assignment07;

import java.io.Serializable;

public class User implements Serializable {
    String name, email,country,dob;
    int age;

    public User(String name, String email, int age, String country, String dob) {
        this.name = name;
        this.email = email;
        this.age = age;
        this.country = country;
        this.dob = dob;
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

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}

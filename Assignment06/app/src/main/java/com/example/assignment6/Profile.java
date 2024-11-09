package com.example.assignment6;

import java.io.Serializable;

public class Profile implements Serializable {
    String name, email, role;

    public Profile(String name, String email, String role) {
        this.name = name;
        this.email = email;
        this.role = role;
    }

    public Profile() {
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

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
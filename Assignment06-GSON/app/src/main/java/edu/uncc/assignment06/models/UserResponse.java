package edu.uncc.assignment06.models;

import java.util.ArrayList;

public class UserResponse {
    public String status;
    public ArrayList<User> users;

    public UserResponse() {
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public ArrayList<User> getUsers() {
        return users;
    }

    public void setUsers(ArrayList<User> users) {
        this.users = users;
    }
}

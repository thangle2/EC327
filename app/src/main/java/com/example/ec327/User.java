package com.example.ec327;

import java.io.Serializable;

@SuppressWarnings("serial")
public class User implements Serializable {
    protected String firstName;
    protected String lastName;
    protected String username;
    protected int age;//
    protected String state;

    public User() {
        firstName = "";
        lastName = "";
        username = "";
        age = 0;
        state = "";
    }

    public boolean isEmpty() {
        if (age == 0) {
            return true;
        } else {
            return false;
        }

    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getUsername() {
        return username;
    }

    public int getAge() {
        return age;
    }

    public String getState() {
        return state;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setState(String state) {
        this.state = state;
    }
}

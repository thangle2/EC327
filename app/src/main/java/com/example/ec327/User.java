package com.example.ec327;

import java.io.Serializable;

@SuppressWarnings("serial")
public class User implements Serializable {
    protected String firstName;

    protected int age;//
    protected String state;

    public User() {
        firstName = "";

        age = 0;
        state = "";
    }

    public String getFirstName() {
        return firstName;
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

    public void setAge(int age) {
        this.age = age;
    }

    public void setState(String state) {
        this.state = state;
    }
}

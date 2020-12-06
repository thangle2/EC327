package com.example.ec327;

import java.io.Serializable;

@SuppressWarnings("serial")
public class User implements Serializable {

  protected String firstName;                                           // Insert Fullname of the User (First, Middle and Last)
  protected int age;                                                    // Insert Age of the user
  protected String state;                                               // Insert User's Residential State

  public User() {
    firstName = "";
    age = 0;
    state = "";
  }                                                                     // Initialization

  public String getFirstName() {
    return firstName;
  }                   // Returns User's Name

  public int getAge() {
    return age;
  }                                  // Returns User's Age

  public String getState() {
    return state;
  }                           // Returns User's State

  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }                   //Returns User's name

  public void setAge(int age) {
    this.age = age;
  }

  public void setState(String state) {
    this.state = state;
  }
}

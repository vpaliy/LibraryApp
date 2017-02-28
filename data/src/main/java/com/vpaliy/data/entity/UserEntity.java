package com.vpaliy.data.entity;

import com.google.gson.annotations.SerializedName;

public class UserEntity {

    @SerializedName("firstName")
    private String firstName;

    @SerializedName("lastName")
    private String lastName;

    @SerializedName("emailAddress")
    private String emailAddress;

    @SerializedName("id")
    private int ID;

    @SerializedName("age")
    private int age;

    public UserEntity(String firstName, String lastName, int ID) {
        this.firstName=firstName;
        this.lastName=lastName;
        this.ID=ID;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getAge() {
        return age;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public int getID() {
        return ID;
    }
}

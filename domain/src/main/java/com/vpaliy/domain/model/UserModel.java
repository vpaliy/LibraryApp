package com.vpaliy.domain.model;

import java.util.List;

public class UserModel {

    /* Required data */
    private String firstName;
    private String lastName;
    private int ID;

    /* Optional data */
    private String emailAddress;
    private int age;
    private byte[] userImage;
    private List<BookModel> bookList;


    public UserModel(String firstName, String lastName, int ID) {
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

    public void setUserImage(byte[] userImage) {
        this.userImage = userImage;
    }

    public byte[] getUserImage() {
        return userImage;
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

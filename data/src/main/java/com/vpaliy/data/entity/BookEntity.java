package com.vpaliy.data.entity;

import com.google.gson.annotations.SerializedName;


public class BookEntity {

    @SerializedName("author")
    private String author;

    @SerializedName("title")
    private String title;

    @SerializedName("description")
    private String description;

    @SerializedName("genre")
    private String genre;

    @SerializedName("id")
    private String ID;

    @SerializedName("numberOfPages")
    private int numberOfPages;

    @SerializedName("ageRestriction")
    private int ageRestriction;

    public BookEntity(String author, String title, String ID){
        this.author=author;
        this.title=title;
        this.ID=ID;
    }

    public void setAuthor(String author) {
        this.author=author;
    }

    public void setTitle(String title) {
        this.title=title;
    }

    public void setGenre(String genre) {
        this.genre=genre;
    }

    public void setDescription(String description) {
        this.description=description;
    }

    public void setID(String ID) {
        this.ID=ID;
    }

    public void setNumberOfPages(int numberOfPages) {
        this.numberOfPages=numberOfPages;
    }

    public void setAgeRestriction(int ageRestriction) {
        this.ageRestriction = ageRestriction;
    }

    public int getAgeRestriction() {
        return ageRestriction;
    }

    public int getNumberOfPages() {
        return numberOfPages;
    }

    public String getAuthor() {
        return author;
    }

    public String getDescription() {
        return description;
    }

    public String getGenre() {
        return genre;
    }

    public String getTitle() {
        return title;
    }

    public String getID() {
        return ID;
    }

}

package com.vpaliy.datasource.data.entity;

public class BookEntity {

    private String author;
    private String title;
    private String description;
    private String genre;
    private int ID;
    private int numberOfPages;
    private int ageRestriction;

    public BookEntity(String author, String title, int ID){
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

    public void setID(int ID) {
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

    public int getID() {
        return ID;
    }

}

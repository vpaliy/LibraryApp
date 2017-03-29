package com.vpaliy.domain.model;


import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

public class BookModel {

    /* Required data */
    private String author;
    private String title;
    private int ID;

    /* Optional data */
    private String description;
    private String genre;
    private int numberOfPages;
    private int ageRestriction;
    private List<UserModel> readerList;

    public BookModel(String author, String title, int ID){
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

    public BookModel addReader(UserModel user) {
        if(readerList==null) {
            synchronized (this) {
                if(readerList==null) {
                    readerList=new LinkedList<>();
                }
            }
        }
        readerList.add(user);
        return this;
    }

    public BookModel addReaders(Collection<? extends UserModel> collection) {
        if(readerList==null) {
            synchronized (this) {
                if(readerList==null) {
                    readerList=new LinkedList<>();
                }
            }
        }
        readerList.addAll(collection);
        return this;
    }
}

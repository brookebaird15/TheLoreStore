package com.example.thelorestore.Pojo;

public class Book {
    private int id;
    private String title;
    private int publisher;
    private int year;
    private int status;
    private String comment;

    /**
     * Book class constructors
     * @param id
     * @param title
     * @param publisher
     * @param year
     * @param status
     * @param comment
     */
    public Book(int id, String title, int publisher, int year, int status, String comment) {
        this.id = id;
        this.title = title;
        this.publisher = publisher;
        this.year = year;
        this.status = status;
        this.comment = comment;
    }

    public Book(int id) {
        this.id = id;
    }

    /**
     * Book class getters and setters
     */
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getPublisher() {
        return publisher;
    }

    public void setPublisher(int publisher) {
        this.publisher = publisher;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    /**
     * Book class toString
     * @return book title
     */

    @Override
    public String toString() {
        return title;
    }
}

package com.example.thelorestore.Pojo;

public class DisplayBook {
    private String title;
    private String publisher;
    private String year;
    private String status;
    private String comment;

    public DisplayBook(String title, String publisher, String year, String status, String comment) {
        this.title = title;
        this.publisher = publisher;
        this.year = year;
        this.status = status;
        this.comment = comment;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    @Override
    public String toString() {
        return  "title='" + title + '\'' +
                ", publisher='" + publisher + '\'' +
                ", year=" + year +
                ", borrowed=" + status;
    }
}

package com.example.thelorestore.Pojo;

public class DisplayBook {
    private String isbn;
    private String title;
    private String author1;
    private String author2;
    private String author3;
    private String genre1;
    private String genre2;
    private String genre3;
    private String publisher;
    private String year;
    private String status;
    private String comment;

    public DisplayBook(String isbn, String title, String author1, String author2, String author3, String genre1, String genre2, String genre3, String publisher, String year, String status, String comment) {
        this.isbn = isbn;
        this.title = title;
        this.author1 = author1;
        this.author2 = author2;
        this.author3 = author3;
        this.genre1 = genre1;
        this.genre2 = genre2;
        this.genre3 = genre3;
        this.publisher = publisher;
        this.year = year;
        this.status = status;
        this.comment = comment;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor1() {
        return author1;
    }

    public void setAuthor1(String author1) {
        this.author1 = author1;
    }

    public String getAuthor2() {
        return author2;
    }

    public void setAuthor2(String author2) {
        this.author2 = author2;
    }

    public String getAuthor3() {
        return author3;
    }

    public void setAuthor3(String author3) {
        this.author3 = author3;
    }

    public String getGenre1() {
        return genre1;
    }

    public void setGenre1(String genre1) {
        this.genre1 = genre1;
    }

    public String getGenre2() {
        return genre2;
    }

    public void setGenre2(String genre2) {
        this.genre2 = genre2;
    }

    public String getGenre3() {
        return genre3;
    }

    public void setGenre3(String genre3) {
        this.genre3 = genre3;
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
                ", author='" + author1 + '\'' +
                ", author='" + author2 + '\'' +
                ", author='" + author3 + '\'' +
                ", genre='" + genre1 + '\'' +
                ", genre='" + genre2 + '\'' +
                ", genre='" + genre3 + '\'' +
                ", publisher='" + publisher + '\'' +
                ", year=" + year +
                ", borrowed=" + status;
    }
}

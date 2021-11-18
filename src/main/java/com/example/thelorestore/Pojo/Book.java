package com.example.thelorestore.Pojo;

public class Book {
    private int isbn;
    private String title;
    private int author1;
    private int author2;
    private int author3;
    private int genre1;
    private int genre2;
    private int genre3;
    private int publisher;
    private int year;
    private int quantity;
    private double price;
    private String borrowed;

    /**
     * Book class constructors
     * @param isbn
     * @param title
     * @param author1
     * @param author2
     * @param author3
     * @param genre1
     * @param genre2
     * @param genre3
     * @param publisher
     * @param year
     * @param quantity
     * @param price
     * @param borrowed
     */
    public Book(int isbn, String title, int author1, int author2, int author3, int genre1, int genre2, int genre3, int publisher, int year, int quantity, double price, String borrowed) {
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
        this.quantity = quantity;
        this.price = price;
        this.borrowed = borrowed;
    }

    public Book(String title, int author1, int author2, int author3, int genre1, int genre2, int genre3, int publisher, int year, int quantity, double price, String borrowed) {
        this.title = title;
        this.author1 = author1;
        this.author2 = author2;
        this.author3 = author3;
        this.genre1 = genre1;
        this.genre2 = genre2;
        this.genre3 = genre3;
        this.publisher = publisher;
        this.year = year;
        this.quantity = quantity;
        this.price = price;
        this.borrowed = borrowed;
    }

    /**
     * Book class getters and setters
     */

    public int getISBN() {
        return isbn;
    }

    public void setISBN(int id) {
        this.isbn = isbn;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getAuthor1() {
        return author1;
    }

    public void setAuthor1(int author1) {
        this.author1 = author1;
    }

    public int getAuthor2() {
        return author2;
    }

    public void setAuthor2(int author2) {
        this.author2 = author2;
    }

    public int getAuthor3() {
        return author3;
    }

    public void setAuthor3(int author3) {
        this.author3 = author3;
    }

    public int getGenre1() {
        return genre1;
    }

    public void setGenre1(int genre1) {
        this.genre1 = genre1;
    }

    public int getGenre2() {
        return genre2;
    }

    public void setGenre2(int genre2) {
        this.genre2 = genre2;
    }

    public int getGenre3() {
        return genre3;
    }

    public void setGenre3(int genre3) {
        this.genre3 = genre3;
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

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getBorrowed() {
        return borrowed;
    }

    public void setBorrowed(String borrowed) {
        this.borrowed = borrowed;
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

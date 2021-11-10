package com.example.thelorestore.Pojo;

public class Book {
    private int id;
    private String title;
    private int author;
    private int genre;
    private int publisher;
    private int year;
    private int quantity;
    private double price;
    private String borrowed;

    /**
     * Book class constructors
     * @param id
     * @param title
     * @param author
     * @param genre
     * @param publisher
     * @param year
     * @param quantity
     * @param price
     * @param borrowed
     */
    public Book(int id, String title, int author, int genre, int publisher, int year, int quantity, double price, String borrowed) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.genre = genre;
        this.publisher = publisher;
        this.year = year;
        this.quantity = quantity;
        this.price = price;
        this.borrowed = borrowed;
    }

    public Book(String title, int author, int genre, int publisher, int year, int quantity, double price, String borrowed) {
        this.title = title;
        this.author = author;
        this.genre = genre;
        this.publisher = publisher;
        this.year = year;
        this.quantity = quantity;
        this.price = price;
        this.borrowed = borrowed;
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

    public int getAuthor() {
        return author;
    }

    public void setAuthor(int author) {
        this.author = author;
    }

    public int getGenre() {
        return genre;
    }

    public void setGenre(int genre) {
        this.genre = genre;
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

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
     */
    public Book(int id, String title, int author, int genre, int publisher, int year, int quantity, double price) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.genre = genre;
        this.publisher = publisher;
        this.year = year;
        this.quantity = quantity;
        this.price = price;
    }

    public Book(String title, int author, int genre, int publisher, int year, int quantity, double price) {
        this.title = title;
        this.author = author;
        this.genre = genre;
        this.publisher = publisher;
        this.year = year;
        this.quantity = quantity;
        this.price = price;
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

    /**
     * Book class toString
     * returns book title
     */

    @Override
    public String toString() {
        return title;
    }
}

package com.example.thelorestore.Pojo;

public class BookAuthor {
    private int book;
    private int author;

    public BookAuthor(int book, int author) {
        this.book = book;
        this.author = author;
    }

    public int getBook() {
        return book;
    }

    public void setBook(int book) {
        this.book = book;
    }

    public int getAuthor() {
        return author;
    }

    public void setAuthor(int author) {
        this.author = author;
    }
}

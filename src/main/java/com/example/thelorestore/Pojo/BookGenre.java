package com.example.thelorestore.Pojo;

public class BookGenre {
    private int book;
    private int genre;

    public BookGenre(int book, int genre) {
        this.book = book;
        this.genre = genre;
    }

    public int getBook() {
        return book;
    }

    public void setBook(int book) {
        this.book = book;
    }

    public int getGenre() {
        return genre;
    }

    public void setGenre(int genre) {
        this.genre = genre;
    }
}

//TODO - delete if unused
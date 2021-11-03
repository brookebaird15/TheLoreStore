package com.example.thelorestore.Pojo;

public class Publisher {
    private int id;
    private String companyName;

    /**
     * Constructors
     *
     * @param id
     * @param companyName
     */
    public Publisher(int id, String companyName) {
        this.id = id;
        this.companyName = companyName;
    }

    public Publisher(String companyName) {
        this.companyName = companyName;
    }

    /**
     * Getters & Setters
     *
     * @return
     */
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    /**
     * toString
     *
     * @return
     */
    @Override
    public String toString() {
        return companyName;
    }
}

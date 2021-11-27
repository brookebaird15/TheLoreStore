package com.example.thelorestore.Pojo;

public class Author {
    private int id;
    private String firstName;
    private String lastName;
    private String middleName;


    /**
     * Author class constructors
     * @param id
     * @param firstName
     * @param lastName
     * @param middleName

     */

    public Author(int id, String firstName, String middleName, String lastName) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.middleName = middleName;

    }

    public Author(String firstName, String middleName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.middleName = middleName;
    }

    public Author(int id, String firstName, String lastName) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public Author(int id) {
        this.id = id;
    }

    /**
     * Author class getters and setters
     */

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }


    /**
     * Author class toString
     * @return Author full name
     */
    public String toString() {
        return  firstName + " " + middleName + " " + lastName;
    }
}

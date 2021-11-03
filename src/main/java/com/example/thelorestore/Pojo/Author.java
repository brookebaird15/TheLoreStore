package com.example.thelorestore.Pojo;

public class Author {
    private int id;
    private String firstName;
    private String lastName;
    private String middleName;
    private String prefix;
    private String suffix;

    /**
     * Author class constructors
     * @param id
     * @param firstName
     * @param lastName
     * @param middleName
     * @param prefix
     * @param suffix
     */

    public Author(int id, String firstName, String lastName, String middleName, String prefix, String suffix) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.middleName = middleName;
        this.prefix = prefix;
        this.suffix = suffix;
    }

    public Author(String firstName, String lastName, String middleName, String prefix, String suffix) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.middleName = middleName;
        this.prefix = prefix;
        this.suffix = suffix;
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

    public String getPrefix() {
        return prefix;
    }

    public void setPrefix(String prefix) {
        this.prefix = prefix;
    }

    public String getSuffix() {
        return suffix;
    }

    public void setSuffix(String suffix) {
        this.suffix = suffix;
    }

    /**
     * Author class toString
     * @return Author full name including prefix and suffix
     */
    public String toString() {
        return prefix + " " + firstName + " " + middleName + " " + lastName + " " + suffix;
    }
}

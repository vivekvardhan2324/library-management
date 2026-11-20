package com.library.model;

public class Book {
    private int id;
    private String bname;
    private String author;
    private String type;
    private float price;

    public Book() {}

    public Book(int id, String bname, String author, String type, float price) {
        this.id = id;
        this.bname = bname;
        this.author = author;
        this.type = type;
        this.price = price;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public String getBname() { return bname; }
    public void setBname(String bname) { this.bname = bname; }
    public String getAuthor() { return author; }
    public void setAuthor(String author) { this.author = author; }
    public String getType() { return type; }
    public void setType(String type) { this.type = type; }
    public float getPrice() { return price; }
    public void setPrice(float price) { this.price = price; }
}

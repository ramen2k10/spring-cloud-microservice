package com.codebuzz.bookmanagement.bookinfoservice.model;

import org.springframework.hateoas.RepresentationModel;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "Book_Info")
public class Book extends RepresentationModel<Book>{
    @Id
    @Column(name = "Book_Id")
    private Integer id;
    private String bookName;
    private String author;
    private String category;
    private String bookDescription;
    private String publicationDate;
    private String environment;

    public Book(){

    }
    
    public Book(Integer id, @JsonProperty("BookName") String bookName, String author, String category, String bookDescription, String publicationDate) {
        this.id = id;
        this.bookName = bookName;
        this.author = author;
        this.category = category;
        this.bookDescription = bookDescription;
        this.publicationDate = publicationDate;
    }
    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public String getBookName() {
        return bookName;
    }
    public void setBookName(String bookName) {
        this.bookName = bookName;
    }
    public String getAuthor() {
        return author;
    }
    public void setAuthor(String author) {
        this.author = author;
    }
    public String getCategory() {
        return category;
    }
    public void setCategory(String category) {
        this.category = category;
    }

    public String getBookDescription() {
        return bookDescription;
    }

    public void setBookDescription(String bookDescription) {
        this.bookDescription = bookDescription;
    }

    public String getPublicationDate() {
        return publicationDate;
    }

    public void setPublicationDate(String publicationDate) {
        this.publicationDate = publicationDate;
    }

    public String getEnvironment() {
        return environment;
    }

    public void setEnvironment(String environment) {
        this.environment = environment;
    }

}

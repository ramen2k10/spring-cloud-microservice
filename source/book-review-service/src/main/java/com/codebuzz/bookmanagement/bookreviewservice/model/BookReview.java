package com.codebuzz.bookmanagement.bookreviewservice.model;

public class BookReview {
    private Integer id;
    private String bookName;
    private String author;
    private String comments;
    private Integer reviewInScaleOfFive;

    public BookReview(){

    }

    public BookReview(Integer id, String bookName, String author, String comments, Integer reviewInScaleOfFive) {
        this.id = id;
        this.bookName = bookName;
        this.author = author;
        this.comments = comments;
        this.reviewInScaleOfFive = reviewInScaleOfFive;
    }
    private String environment;

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
    public String getComments() {
        return comments;
    }
    public void setComments(String comments) {
        this.comments = comments;
    }
    public Integer getReviewInScaleOfFive() {
        return reviewInScaleOfFive;
    }
    public void setReviewInScaleOfFive(Integer reviewInScaleOfFive) {
        this.reviewInScaleOfFive = reviewInScaleOfFive;
    }
    public String getEnvironment() {
        return environment;
    }
    public void setEnvironment(String environment) {
        this.environment = environment;
    }
}

package com.codebuzz.bookmanagement.bookreviewservice.controller;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.codebuzz.bookmanagement.bookreviewservice.config.BookServiceProxy;
import com.codebuzz.bookmanagement.bookreviewservice.model.BookReview;

@RestController
public class BookReviewController {
    
    @Autowired
    private BookServiceProxy bookServiceProxy;
 
    @GetMapping("/api/book/{bookName}/reviews")
    public List<BookReview> getReview(@PathVariable String bookName){

        HashMap<String, String> uriVar = new HashMap<>();
        uriVar.put("bookName", bookName);
        ResponseEntity<BookReview> restTemplate = new RestTemplate().getForEntity("http://localhost:7001/api/book/{bookName}", BookReview.class, uriVar);
        BookReview bookReview= restTemplate.getBody();
        BookReview newBookReview = new BookReview(bookReview.getId(), 
                bookReview.getBookName(),
                bookReview.getAuthor(), 
                "Good Book", 3);
                newBookReview.setEnvironment(bookReview.getEnvironment());
        return Arrays.asList(newBookReview);
    }

    @GetMapping("/api-feign/book/{bookName}/reviews")
    public List<BookReview> getReviewFeign(@PathVariable String bookName){

        BookReview bookReview= bookServiceProxy.retrieveBookByName(bookName);
        BookReview newBookReview = new BookReview(bookReview.getId(), 
                bookReview.getBookName(),
                bookReview.getAuthor(), 
                "Good Book", 3);
                newBookReview.setEnvironment(bookReview.getEnvironment());
        return Arrays.asList(newBookReview);
    }
}

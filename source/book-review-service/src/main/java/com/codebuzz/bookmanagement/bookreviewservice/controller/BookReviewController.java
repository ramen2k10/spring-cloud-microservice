package com.codebuzz.bookmanagement.bookreviewservice.controller;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.codebuzz.bookmanagement.bookreviewservice.config.BookServiceProxy;
import com.codebuzz.bookmanagement.bookreviewservice.model.BookReview;

@Configuration(proxyBeanMethods = false)
class RestTemplateConfiguration {
    
    @Bean
    RestTemplate restTemplate(RestTemplateBuilder builder) {
        return builder.build();
    }
}

@RestController
public class BookReviewController {

    @GetMapping("/api/book/review/version")
    public String getBookReviewVersion(){
        return "Version-2.0";
    }

    private Logger logger = LoggerFactory.getLogger(BookReviewController.class);
    
    @Autowired
    private BookServiceProxy bookServiceProxy;

    @Autowired
    private RestTemplate restTemplate;


 
    @GetMapping("/api/book/{bookName}/reviews")
    public List<BookReview> getReview(@PathVariable String bookName){

        HashMap<String, String> uriVar = new HashMap<>();
        uriVar.put("bookName", bookName);
        ResponseEntity<BookReview> restTemplate1 = restTemplate.getForEntity("http://localhost:7001/api/book/{bookName}", BookReview.class, uriVar);
        BookReview bookReview= restTemplate1.getBody();
        BookReview newBookReview = new BookReview(bookReview.getId(), 
                bookReview.getBookName(),
                bookReview.getAuthor(), 
                "Good Book", 3);
                newBookReview.setEnvironment(bookReview.getEnvironment());
        return Arrays.asList(newBookReview);
    }

    @GetMapping("/api-feign/book/{bookName}/reviews")
    public List<BookReview> getReviewFeign(@PathVariable String bookName){

        BookReview bookReview= bookServiceProxy.findBookByBookName(bookName);
        logger.info("Book name: "+bookReview.getBookName());
        logger.info("Book author: "+bookReview.getAuthor());
        BookReview newBookReview = new BookReview(bookReview.getId(), 
                bookReview.getBookName(),
                bookReview.getAuthor(), 
                "Good Book", 3);
                newBookReview.setEnvironment(bookReview.getEnvironment());
        return Arrays.asList(newBookReview);
    }
}

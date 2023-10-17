package com.codebuzz.bookmanagement.bookreviewservice.config;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.codebuzz.bookmanagement.bookreviewservice.model.BookReview;

//@FeignClient(name = "book-service", url = "http://localhost:7001")
@FeignClient(name = "book-service")
public interface BookServiceProxy {
    
    @GetMapping("api/find-book/{bookName}")
    public BookReview findBookByBookName(@PathVariable String bookName);
}

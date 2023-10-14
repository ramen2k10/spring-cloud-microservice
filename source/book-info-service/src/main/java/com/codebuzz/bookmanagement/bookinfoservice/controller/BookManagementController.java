package com.codebuzz.bookmanagement.bookinfoservice.controller;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.Link;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.codebuzz.bookmanagement.bookinfoservice.config.BookServiceConfig;
import com.codebuzz.bookmanagement.bookinfoservice.exception.BookNotFoundException;
import com.codebuzz.bookmanagement.bookinfoservice.model.Book;
import com.codebuzz.bookmanagement.bookinfoservice.service.BookManagementService;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@RestController
public class BookManagementController {

    @Autowired
    private BookManagementService bookManagementService;

    @Autowired
    private BookServiceConfig bookServiceConfig;

    @Autowired
    private Environment environment;
    
    @PostMapping("api/book")
    public HttpEntity<Book> addBookInfo(@RequestBody Book bookInfo){
        Book book = bookManagementService.addBookInfo(bookInfo);
        Link link = linkTo(methodOn(BookManagementController.class).retrieveBookByName(book.getBookName())).withSelfRel();
        return ResponseEntity.created(link.toUri()).build();
    }

    @GetMapping("api/book/{bookName}")
    public HttpEntity<Book> retrieveBookByName(@PathVariable String bookName){
        Book book = bookManagementService.findBookInfoByName(bookName);
        if(book == null){
            throw new BookNotFoundException("Book Name: "+bookName);
        }
        String port =  environment.getProperty("local.server.port");
        book.setEnvironment(port);
		book.add(linkTo(methodOn(BookManagementController.class).addBookInfo(book)).withSelfRel());
        book.add(linkTo(methodOn(BookManagementController.class).retrieveAllBooks()).withSelfRel());
        return new ResponseEntity<>(book, HttpStatus.OK);
    }

    @GetMapping("api/books")
    public List<Book> retrieveAllBooks(){
        List<Book> books = bookManagementService.findAllBooksInfo();
        if(books.isEmpty()){
            throw new BookNotFoundException("Books List Not Found");
        }
        for (Book book : books) {
            book.setEnvironment(environment.getProperty("local.server.port"));
            book.add(linkTo(methodOn(BookManagementController.class).addBookInfo(book)).withSelfRel());
            book.add(linkTo(methodOn(BookManagementController.class).retrieveBookByName(book.getBookName())).withSelfRel());
        }
        return bookManagementService.findAllBooksInfo();
    }

    @DeleteMapping("api/del/book/{bookName}")
    public ResponseEntity<HttpStatus> deleteBookRecordByName(@PathVariable String bookName){
            bookManagementService.deleteBookByBookName(bookName);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @PutMapping("api/update/book/{id}")
    public ResponseEntity<Book> updateBookDetails(@PathVariable Integer id, @RequestBody Book bookObj){
         Book updated = bookManagementService.updateBookDetails(id, bookObj);
         updated.add(linkTo(methodOn(BookManagementController.class).retrieveBookByName(updated.getBookName())).withSelfRel());
        return ResponseEntity.ok(updated);
    }

}

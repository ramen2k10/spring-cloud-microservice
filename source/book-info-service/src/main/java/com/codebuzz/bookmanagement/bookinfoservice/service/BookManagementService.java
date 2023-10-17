package com.codebuzz.bookmanagement.bookinfoservice.service;

import java.util.List;

import com.codebuzz.bookmanagement.bookinfoservice.model.Book;

public interface BookManagementService {
    
    List<Book> findAllBooksInfo();
    Book findBookInfoByBookName(String bookName);
    Book addBookInfo(Book bookInfo);
    void deleteBookByBookName(String bookName);
    Book updateBookDetails(Integer id, Book updateDetails);
}

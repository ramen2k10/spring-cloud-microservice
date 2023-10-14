package com.codebuzz.bookmanagement.bookinfoservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.codebuzz.bookmanagement.bookinfoservice.model.Book;

@Repository
public interface BookManagementRepository extends JpaRepository<Book, Integer>{
    Book findByBookName(String bookName);
    boolean deleteBookByBookName(String bookName);
}

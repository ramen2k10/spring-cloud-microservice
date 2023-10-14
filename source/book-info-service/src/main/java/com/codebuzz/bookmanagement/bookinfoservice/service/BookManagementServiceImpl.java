package com.codebuzz.bookmanagement.bookinfoservice.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.codebuzz.bookmanagement.bookinfoservice.exception.BookNotFoundException;
import com.codebuzz.bookmanagement.bookinfoservice.model.Book;
import com.codebuzz.bookmanagement.bookinfoservice.repository.BookManagementRepository;

@Service
public class BookManagementServiceImpl implements BookManagementService{
   
    @Autowired
    private BookManagementRepository bookManagementRepository;

    @Override
    public Book addBookInfo(Book bookInfo) {
        bookManagementRepository.save(bookInfo);
        return bookInfo;
    }

    @Override
    public List<Book> findAllBooksInfo() {
        return bookManagementRepository.findAll();
    }

    @Override
    public Book findBookInfoByName(String bookName) {
        return bookManagementRepository.findByBookName(bookName);
    }

    @Override
    public void deleteBookByBookName(String bookName) {
      bookManagementRepository.deleteBookByBookName(bookName);
    }

    @Override
    public Book updateBookDetails(Integer id, Book updatedObj) {
        Optional<Book> book = bookManagementRepository.findById(id);
        if( book.isPresent()){
            Book existingBook = book.get();
            existingBook.setBookName(updatedObj.getBookName());
            existingBook.setAuthor(updatedObj.getAuthor());
            existingBook.setBookDescription(updatedObj.getBookDescription());
            existingBook.setCategory(updatedObj.getCategory());
            existingBook.setPublicationDate(updatedObj.getPublicationDate());
            return bookManagementRepository.save(existingBook);
        }
        throw new BookNotFoundException("Book not found with name: "+updatedObj.getBookName());
    }
}

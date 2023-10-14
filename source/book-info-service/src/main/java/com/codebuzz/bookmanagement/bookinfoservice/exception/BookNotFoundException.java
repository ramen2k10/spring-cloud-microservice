package com.codebuzz.bookmanagement.bookinfoservice.exception;

public class BookNotFoundException extends RuntimeException{

    public BookNotFoundException(){
        super();
    }
    
    public BookNotFoundException(String message){
        super(message);
    }

    public BookNotFoundException(String message, Throwable b){
        super(message, b);
    }
    
}

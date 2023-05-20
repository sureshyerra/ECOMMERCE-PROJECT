package com.example.Ecommerce.exception;

public class EmailAlreadyPresentException extends Exception{

    public EmailAlreadyPresentException(String message){
        super(message);
    }
}

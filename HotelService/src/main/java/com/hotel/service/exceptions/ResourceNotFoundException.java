package com.hotel.service.exceptions;

import org.springframework.web.bind.annotation.ExceptionHandler;


public class ResourceNotFoundException extends RuntimeException{

    public ResourceNotFoundException(){
        super("Resource not found on Server : ");
    }

    public ResourceNotFoundException(String message){
        super(message);
    }

}

package com.hotel.service.exceptions;


import com.hotel.service.payload.ApiResponse;
import org.apache.catalina.util.ResourceSet;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {
///  OLD
//    @ExceptionHandler(ResourceNotFoundException.class)
//    public ResponseEntity<ApiResponse> handleResourceNotFoundException(ResourceNotFoundException exception){
//        String message = exception.getMessage();
//        ApiResponse response = ApiResponse.builder().message(message).success(true).status(HttpStatus.NOT_FOUND).build();
//        return new ResponseEntity<ApiResponse>(response , HttpStatus.NOT_FOUND);
//    }

    /// NEW

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<Map<String , Object>> notFoundHandler(ResourceNotFoundException exception){
        Map map = new HashMap();
        map.put("message" , exception.getMessage());
        map.put("success" , false);
        map.put("status" , HttpStatus.NOT_FOUND);
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(map);
    }






}

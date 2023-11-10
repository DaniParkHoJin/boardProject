package org.parkhojin.restcontrollers;

import org.parkhojin.commons.rest.JSONData;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice("org.parkhojin.restcontrollers")
public class CommRestController {
    @ExceptionHandler(Exception.class)
    public ResponseEntity<JSONData<Object>> errorHandler(Exception e){

    }

}

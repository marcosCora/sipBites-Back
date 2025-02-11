package com.sipAndBites.exception;

import com.sipAndBites.entity.dtos.DtoResponse;
import com.sipAndBites.exception.errror.InvalidDataException;
import com.sipAndBites.exception.errror.ObjectNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.swing.*;

@ControllerAdvice
public class ResponseEntityException extends ResponseEntityExceptionHandler {

    @ExceptionHandler(InvalidDataException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<DtoResponse> responseInvalidDataException(InvalidDataException ex){
        DtoResponse error = new DtoResponse();
        error.setMessage(ex.getMessage());
        error.setStatusCode(HttpStatus.NOT_FOUND);
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
    }

    @ExceptionHandler(ObjectNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_ACCEPTABLE)
    public ResponseEntity<DtoResponse> responseObjectNotFound(ObjectNotFoundException ex){
        DtoResponse error = new DtoResponse();
        error.setMessage(ex.getMessage());
        error.setStatusCode(HttpStatus.NOT_ACCEPTABLE);
        return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(error);
    }

}

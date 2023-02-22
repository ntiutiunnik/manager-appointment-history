package com.demo.manager.appointmenthistory.controller;

import com.demo.manager.appointmenthistory.exception.CustomCrudException;
import com.demo.manager.appointmenthistory.exception.ErrorDetails;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import java.util.Date;

@RestControllerAdvice
public class ControllerAdvice {
    private final static Logger logger = LogManager.getLogger(ControllerAdvice.class);

    @ExceptionHandler(CustomCrudException.class)
    public ResponseEntity<?> handleCustomCrudException(CustomCrudException exception, WebRequest webRequest) {
        return handleException(exception, webRequest, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> handleGlobalException(Exception exception, WebRequest webRequest) {
        return handleException(exception, webRequest, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    private ResponseEntity<?> handleException(Exception exception, WebRequest webRequest, HttpStatus httpStatus) {
        String message = exception.getMessage();
        logger.error(message);

        return new ResponseEntity<>(new ErrorDetails(new Date(), message, webRequest.getDescription(false)), httpStatus);
    }
}

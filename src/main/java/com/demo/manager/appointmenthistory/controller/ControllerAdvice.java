package com.demo.manager.appointmenthistory.controller;

import com.demo.manager.appointmenthistory.exception.CustomCrudException;
import com.demo.manager.appointmenthistory.exception.ErrorDetails;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.TypeMismatchException;
import org.springframework.core.convert.ConversionFailedException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageConversionException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import java.util.Date;

@RestControllerAdvice
public class ControllerAdvice {
    private static final Logger logger = LogManager.getLogger(ControllerAdvice.class);

    @ExceptionHandler(CustomCrudException.class)
    public ResponseEntity<ErrorDetails> handleCustomCrudException(CustomCrudException exception, WebRequest webRequest) {
        return handleException(exception, webRequest, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler({
            ConversionFailedException.class,
            HttpMessageConversionException.class,
            TypeMismatchException.class
    })
    public ResponseEntity<ErrorDetails> handleDataConversionException(Exception exception, WebRequest webRequest) {
        return handleException(exception, webRequest, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorDetails> handleGlobalException(Exception exception, WebRequest webRequest) {
        return handleException(exception, webRequest, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    private ResponseEntity<ErrorDetails> handleException(Exception exception, WebRequest webRequest, HttpStatus httpStatus) {
        String message = exception.getMessage();
        logger.error(message);

        return new ResponseEntity<>(new ErrorDetails(new Date(), message, webRequest.getDescription(false)), httpStatus);
    }
}

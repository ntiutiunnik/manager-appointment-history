package com.demo.manager.appointmenthistory.config.api;

import com.demo.tools.doctorappointment.autoconfigure.api.ControllerAdvice;
import com.demo.tools.doctorappointment.autoconfigure.exception.ErrorDetails;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import java.util.Date;

@RestControllerAdvice
public class ControllerAdviceImpl implements ControllerAdvice {
    private static final Logger logger = LogManager.getLogger(ControllerAdviceImpl.class);

    @Override
    public ResponseEntity<com.demo.tools.doctorappointment.autoconfigure.exception.ErrorDetails> createExceptionResponse(Exception exception, WebRequest webRequest, HttpStatus httpStatus) {
        String message = exception.getMessage();
        logger.error(message);

        return new ResponseEntity<>(new ErrorDetails(new Date(), message, webRequest.getDescription(false)), httpStatus);
    }
}

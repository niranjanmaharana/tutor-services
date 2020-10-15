/**
 * 
 */
package com.niranzan.tutor.config;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.niranzan.tutor.exceptions.ResourceNotFoundException;
import com.niranzan.tutor.view.response.SimpleResponseEntity;

/**
 * @author Niranjan
 *
 */

@ControllerAdvice
public class CustomExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<SimpleResponseEntity> customHandleNotFound(Exception ex, WebRequest request) {
        SimpleResponseEntity errors = new SimpleResponseEntity();
        errors.setTime(LocalDateTime.now());
        errors.setStatusMessage(ex.getMessage());
        errors.setStatusCode(HttpStatus.NOT_FOUND.value());
        return new ResponseEntity<>(errors, HttpStatus.NOT_FOUND);
    }
}
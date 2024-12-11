package com.fundoo.lableservice.util;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.fundoo.lableservice.exception.LableNotFoundException;
import com.fundoo.lableservice.exception.NotesServiceNotAvailableException;
import com.fundoo.lableservice.exception.UserMissmatchException;
import com.fundoo.lableservice.exception.UserServiceNotAvailableException;




@RestControllerAdvice
public class ApplicationExceptionHandler extends ResponseEntityExceptionHandler{

	 private ResponseEntity<ErrorStructure<String>> errorResponse(HttpStatus status, String errorMessage, String rootCause) {
	        return ResponseEntity.status(status).body(ErrorStructure.<String>builder().status(status.value())
	                .message(errorMessage).rootCause(rootCause).build());
	    }

	    @ExceptionHandler(LableNotFoundException.class)
	    public ResponseEntity<ErrorStructure<String>> handleLableNotFound(LableNotFoundException ex) {
	        return errorResponse(HttpStatus.NOT_FOUND, ex.getMessage(), "lable is not found by the given lable id try using valid id");
	    }
	    
	    @ExceptionHandler(UserMissmatchException.class)
	    public ResponseEntity<ErrorStructure<String>> handleUserMissMatchFound(UserMissmatchException ex) {
	    	return errorResponse(HttpStatus.BAD_REQUEST, ex.getMessage(), "user is not not matching for the given id try using valid id");
	    }
	    
	    @ExceptionHandler(UserServiceNotAvailableException.class)
	    public ResponseEntity<ErrorStructure<String>> handleUserServiceNotAvailableException(UserServiceNotAvailableException ex) {
	    	return errorResponse(HttpStatus.SERVICE_UNAVAILABLE, ex.getMessage(), "the requested user service is temporatily unavailable please try later");
	    }
	    
	    @ExceptionHandler(NotesServiceNotAvailableException.class)
	    public ResponseEntity<ErrorStructure<String>> handleNotesServiceNotAvailableException(NotesServiceNotAvailableException ex) {
	    	return errorResponse(HttpStatus.SERVICE_UNAVAILABLE, ex.getMessage(), "the requested Notes service is temporatily unavailable please try later");
	    }
	    
	    @ExceptionHandler(RuntimeException.class)
	    public ResponseEntity<ErrorStructure<String>> handleGenericExceptio(RuntimeException ex) {
	        return errorResponse(HttpStatus.INTERNAL_SERVER_ERROR, ex.getMessage(), ex.getStackTrace().toString());
	    }
	
}

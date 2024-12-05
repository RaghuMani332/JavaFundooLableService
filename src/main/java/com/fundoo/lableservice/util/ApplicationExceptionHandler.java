//package com.fundoo.lableservice.util;
//
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.ExceptionHandler;
//import org.springframework.web.bind.annotation.RestControllerAdvice;
//import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
//
//
//
//
//@RestControllerAdvice
//public class ApplicationExceptionHandler extends ResponseEntityExceptionHandler{
//
//	 private ResponseEntity<ErrorStructure<String>> errorResponse(HttpStatus status, String errorMessage, String rootCause) {
//	        return ResponseEntity.status(status).body(ErrorStructure.<String>builder().status(status.value())
//	                .message(errorMessage).rootCause(rootCause).build());
//	    }
//
////	    @ExceptionHandler(NotesNotFoundException.class)
////	    public ResponseEntity<ErrorStructure<String>> handleNotesNotFound(NotesNotFoundException ex) {
////	        return errorResponse(HttpStatus.NOT_FOUND, ex.getMessage(), "note is not found by the given note id try using valid id");
////	    }
////	    @ExceptionHandler(UserNotFoundException.class)
////	    public ResponseEntity<ErrorStructure<String>> handleUserNotFound(UserNotFoundException ex) {
////	    	return errorResponse(HttpStatus.NOT_FOUND, ex.getMessage(), "user is not found by the id try using valid id");
////	    }
//	    
//	    @ExceptionHandler(RuntimeException.class)
//	    public ResponseEntity<ErrorStructure<String>> handleGenericExceptio(RuntimeException ex) {
//	        return errorResponse(HttpStatus.INTERNAL_SERVER_ERROR, ex.getMessage(), ex.getStackTrace().toString());
//	    }
//	
//}

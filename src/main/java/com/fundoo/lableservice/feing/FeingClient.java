package com.fundoo.lableservice.feing;

import java.util.UUID;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.fundoo.lableservice.exception.NotesServiceNotAvailableException;
import com.fundoo.lableservice.exception.UserServiceNotAvailableException;
import com.fundoo.lableservice.responsedto.NotesResponse;
import com.fundoo.lableservice.responsedto.UserResponse;
import com.fundoo.lableservice.util.ResponceStructure;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;

@FeignClient("FUNDOOAPIGATEWAY")
public interface FeingClient {
	
	@GetMapping("api/v1/user/getuser/{id}")
	@CircuitBreaker(name="feing" , fallbackMethod = "fallbackuser")
	public ResponseEntity<ResponceStructure<UserResponse>> getUserById(@PathVariable(name = "id") UUID id);
	
	@CircuitBreaker(name="feing" , fallbackMethod = "fallbacknotes")
	@GetMapping("api/v1/notes/getByNoteId/{noteId}")
	public ResponseEntity<ResponceStructure<NotesResponse>> getAllNotes(@PathVariable(name = "noteId") UUID noteId);
	
	default ResponseEntity<ResponceStructure<UserResponse>> fallbackuser(Exception e)
	{
		System.out.println("from user fall back");
		throw new UserServiceNotAvailableException("User Service Unavailable please try later");

	}
	
	
	default ResponseEntity<ResponceStructure<UserResponse>> fallbacknotes(Exception e)
	{
		throw new NotesServiceNotAvailableException("Notes Service Unavailable");
	}

}

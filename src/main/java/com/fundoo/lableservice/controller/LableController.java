package com.fundoo.lableservice.controller;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fundoo.lableservice.requestdto.LableRequest;
import com.fundoo.lableservice.responsedto.LableResponse;
import com.fundoo.lableservice.service.LableService;
import com.fundoo.lableservice.util.ResponceStructure;

import jakarta.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("api/v1/lable")
public class LableController {

	@Autowired
	private LableService service;
	
	@PostMapping("addlable")
	public ResponseEntity<ResponceStructure<LableResponse>> addLable(@RequestBody LableRequest request)
	{
		return service.addLable(request);
	}
	
	@PutMapping("addNotesToLable")
	public ResponseEntity<ResponceStructure<LableResponse>> addNotes(@RequestParam(name = "lableId") UUID lableId, @RequestParam(name = "noteId") UUID noteId) 
	{
		return service.addNotes(lableId,noteId);
	}
	
	@PutMapping("removeNotesFromLable")
	public ResponseEntity<ResponceStructure<LableResponse>> removeNotes(@RequestParam(name = "lableId") UUID lableId, @RequestParam(name = "noteId") UUID noteId)
	{
		return service.removeNotes(lableId,noteId);
	}
	
	
	@GetMapping("getLable/{userId}")
	public ResponseEntity<ResponceStructure<List<LableResponse>>> getLable(@PathVariable(name = "userId") UUID userId) {
		
		return service.getLable(userId);
	}
	
	@PutMapping("updateLable/{id}")
	public ResponseEntity<ResponceStructure<LableResponse>> updateLable(@PathVariable(name="id") UUID id,@RequestBody LableRequest request)
	{
		return service.updateLable(id,request);
	}
	
	
	@DeleteMapping("deleteLable/{id}")
	public boolean deleteLable(@PathVariable(name = "id") UUID id)
	{
		return service.deleteLable(id);
	}
}

package com.fundoo.lableservice.serviceImpl;

import java.util.HashSet;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.fundoo.lableservice.dao.LableDao;
import com.fundoo.lableservice.entity.LableEntity;
import com.fundoo.lableservice.exception.LableNotFoundException;
import com.fundoo.lableservice.exception.UserMissmatchException;
import com.fundoo.lableservice.feing.FeingClientNotes;
import com.fundoo.lableservice.feing.FeingClientUser;
import com.fundoo.lableservice.requestdto.LableRequest;
import com.fundoo.lableservice.responsedto.LableResponse;
import com.fundoo.lableservice.responsedto.NotesResponse;
import com.fundoo.lableservice.responsedto.UserResponse;
import com.fundoo.lableservice.service.LableService;
import com.fundoo.lableservice.util.ResponceStructure;


@Service
public class LableServiceImpl implements LableService {

	
	@Autowired
	private LableDao dao;
	
	@Autowired
	private FeingClientNotes notesFeign;
	
	@Autowired
	private FeingClientUser userFeign;
	
	
	@Override
	public ResponseEntity<ResponceStructure<LableResponse>> addLable(LableRequest request) {
		LableEntity entity = mapToEntity(request);
		
		ResponceStructure<UserResponse> user = userFeign.getUserById(request.getUserId()).getBody();		
		
		entity.setNotes(new HashSet<>());
		entity = dao.save(entity);
		
		LableResponse response = mapToResponse(entity);
		return mapToResponseEntity(response, "Lable Added successfully", HttpStatus.OK);
		
	}

	@Override
	public ResponseEntity<ResponceStructure<LableResponse>> addNotes(UUID lableId, UUID noteId) {
		LableEntity lable = dao.findById(lableId).orElseThrow(() -> new LableNotFoundException("please create a lable or provide a valid lable id"));
		
		ResponceStructure<NotesResponse> notes = notesFeign.getAllNotes(noteId).getBody();
		lable.getNotes().add(noteId);
		lable = dao.save(lable);
		LableResponse response = mapToResponse(lable);
		return mapToResponseEntity(response, "Note Added to lable successfully", HttpStatus.OK);
	}

	@Override
	public ResponseEntity<ResponceStructure<LableResponse>> removeNotes(UUID lableId, UUID noteId) {
		LableEntity lable = dao.findById(lableId).orElseThrow(() -> new LableNotFoundException("please create a lable or provide a valid lable id"));
		
		if(lable.getNotes().contains(noteId))
		{
			lable.getNotes().remove(noteId);
		}
		lable = dao.save(lable);
		LableResponse response = mapToResponse(lable);
		return mapToResponseEntity(response, "note removed from lable successfully", HttpStatus.OK);

	}

	@Override
	public ResponseEntity<ResponceStructure<List<LableResponse>>> getLable(UUID userId) {
		List<LableEntity> lable = dao.findByUserId(userId).get();
		
		List<LableResponse> response = lable.stream().map(l -> mapToResponse(l)).toList();
		return mapToResponseEntity(response, "Lable fetched successfully", HttpStatus.OK);

	}

	@Override
	public ResponseEntity<ResponceStructure<LableResponse>> updateLable(UUID id, LableRequest request) {
		LableEntity lable = dao.findById(id).orElseThrow(() -> new LableNotFoundException("please create a lable or provide a valid lable id"));
		
		LableEntity entity = mapToEntity(request);
		
		if(!(lable.getUserId().equals(request.getUserId())))
		{
			throw new UserMissmatchException("the data user who created the lable and trying to edit is different");
		}
		
		entity.setId(id);
		entity.setNotes(lable.getNotes());
		lable = dao.save(entity);
		LableResponse response = mapToResponse(lable);
		return mapToResponseEntity(response, "Lable updated successfully", HttpStatus.OK);

	}

	@Override
	public boolean deleteLable(UUID id) {
		dao.deleteById(id);
		return true;
	}
	
	private LableEntity mapToEntity(LableRequest req) {
	    return LableEntity.builder()
	            .lableName(req.getLableName())
	            .userId(req.getUserId())
	            .build();
	}

	private LableResponse mapToResponse(LableEntity entity) {
	    return LableResponse.builder()
	            .id(entity.getId())
	            .lableName(entity.getLableName())
	            .notes(entity.getNotes())
	            .userId(entity.getUserId())
	            .build();
	}
	private <T> ResponseEntity<ResponceStructure<T>> mapToResponseEntity(T data,String message , HttpStatus status)
	{
		ResponceStructure<T> structure = new ResponceStructure<>();
		structure.setData(data);
		structure.setMessage(message);
		structure.setStatus(status.value());
		
		return new ResponseEntity<ResponceStructure<T>>(structure,status);
		
		
	}



}

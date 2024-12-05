package com.fundoo.lableservice.service;

import java.util.List;
import java.util.UUID;

import org.springframework.http.ResponseEntity;

import com.fundoo.lableservice.requestdto.LableRequest;
import com.fundoo.lableservice.responsedto.LableResponse;
import com.fundoo.lableservice.util.ResponceStructure;

public interface LableService {

	ResponseEntity<ResponceStructure<LableResponse>> addLable(LableRequest request);

	ResponseEntity<ResponceStructure<LableResponse>> addNotes(UUID lableId, UUID noteId);

	ResponseEntity<ResponceStructure<LableResponse>> removeNotes(UUID lableId, UUID noteId);

	ResponseEntity<ResponceStructure<List<LableResponse>>> getLable(UUID userId);

	ResponseEntity<ResponceStructure<LableResponse>> updateLable(UUID id, LableRequest request);

	boolean deleteLable(UUID id);

}

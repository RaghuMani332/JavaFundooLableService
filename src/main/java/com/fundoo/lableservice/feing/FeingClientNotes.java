package com.fundoo.lableservice.feing;

import java.util.List;
import java.util.UUID;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.fundoo.lableservice.responsedto.NotesResponse;
import com.fundoo.lableservice.util.ResponceStructure;


@FeignClient("FUNDOONOTESSERVICE")
public interface FeingClientNotes {

	@GetMapping("api/v1/notes/getByNoteId/{noteId}")
	public ResponseEntity<ResponceStructure<NotesResponse>> getAllNotes(@PathVariable(name = "noteId") UUID noteId);

}

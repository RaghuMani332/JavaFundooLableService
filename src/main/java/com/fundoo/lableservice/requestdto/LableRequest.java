package com.fundoo.lableservice.requestdto;

import java.util.List;
import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LableRequest {

	private String lableName;
	
//	private List<Integer> notes;
	
	
	private UUID userId;
}

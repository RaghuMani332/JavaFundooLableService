package com.fundoo.lableservice.responsedto;

import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserResponse {

	private UUID id;
	private String userName;
	private String email;
	
}

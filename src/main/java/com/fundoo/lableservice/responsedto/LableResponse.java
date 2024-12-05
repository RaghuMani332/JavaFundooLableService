package com.fundoo.lableservice.responsedto;

import java.util.List;
import java.util.Set;
import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class LableResponse {


	private UUID id;

	private String lableName;

	private Set<UUID> notes;


	private UUID userId;
}

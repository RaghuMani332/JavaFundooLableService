package com.fundoo.lableservice.feing;

import java.util.UUID;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.fundoo.lableservice.responsedto.UserResponse;
import com.fundoo.lableservice.util.ResponceStructure;


@FeignClient("FUNDOOUSERSERVICE")
public interface FeingClientUser {
	
	@GetMapping("api/v1/user/getuser/{id}")
	public ResponseEntity<ResponceStructure<UserResponse>> getUserById(@PathVariable(name = "id") UUID id);
	
}

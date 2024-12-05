package com.fundoo.lableservice.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class LableNotFoundException extends RuntimeException {

	private String message;
}

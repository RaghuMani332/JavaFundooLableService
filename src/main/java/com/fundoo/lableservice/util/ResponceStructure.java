package com.fundoo.lableservice.util;

import lombok.Data;

@Data
public class ResponceStructure<T> {

	private int status;
	private T data;
	private String message;
}

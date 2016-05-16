package com.doublee.clothes.exceptions;

public class ClotheNotFoundException extends RuntimeException{

	private static final long serialVersionUID = 3640328687018633797L;

	public ClotheNotFoundException(String message) {
		super(message);		
	}
	
}

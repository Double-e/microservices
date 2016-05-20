package com.doublee.clothes.model;

import org.springframework.hateoas.ResourceSupport;

public class Collection extends ResourceSupport{
	
	private String code;
	private String name;
	
	public Collection(){ super(); }
	
	public String getCode() {
		return code;
	}
	
	public void setCode(String code) {
		this.code = code;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
}

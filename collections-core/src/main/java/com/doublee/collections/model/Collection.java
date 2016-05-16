package com.doublee.collections.model;

import io.swagger.annotations.ApiModelProperty;

import org.springframework.hateoas.ResourceSupport;

public class Collection extends ResourceSupport{
	
	@ApiModelProperty(notes = "The Code of the collection.", required = true)
	private String code;
	
	@ApiModelProperty(notes = "The name of the collection.", required = true)
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

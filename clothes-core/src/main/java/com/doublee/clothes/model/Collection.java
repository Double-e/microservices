package com.doublee.clothes.model;

import io.swagger.annotations.ApiModelProperty;

import java.util.List;

import org.springframework.hateoas.ResourceSupport;

public class Collection extends ResourceSupport{
	
	@ApiModelProperty(notes = "The Code of the collection.", required = true)
	private String code;
	
	@ApiModelProperty(notes = "The name of the collection.", required = true)
	private String name;
	
	@ApiModelProperty(notes = "The clothes in the collection.", required = true)
	private List<Clothe> clothes;
	
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
	
	public List<Clothe> getClothes() {
		return clothes;
	}
	
	public void setClothes(List<Clothe> clothes) {
		this.clothes = clothes;
	}

}

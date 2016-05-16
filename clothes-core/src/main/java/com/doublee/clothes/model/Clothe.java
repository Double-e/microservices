package com.doublee.clothes.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import org.springframework.hateoas.ResourceSupport;

@ApiModel
public class Clothe extends ResourceSupport{
	
	@ApiModelProperty(notes = "The Code of the clothe.", required = true)
//	@JsonProperty(required = true)
	private String code;
	
	@ApiModelProperty(notes = "The name of the clothe.", required = true)
	private String name;
	
	@ApiModelProperty(notes = "The brand of the clothe.", required = true)
	private String brand;
	
	@ApiModelProperty(notes = "The size of the clothe.", required = true)
	private String size;
	
	@ApiModelProperty(notes = "The color of the clothe.", required = true)
	private String color;
	
	@ApiModelProperty(notes = "The information about the clothe.", required = true)
	private String about;
	
	
	public Clothe() {super();}
		
	public String getCode() {
		return code;
	}
	
	public void setCode(String code) {
		this.code = code;
		//this.add(linkTo(methodOn(ClothesController.class).detail(this.code)).withSelfRel());
	}
		
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
		
	public String getBrand() {
		return brand;
	}
	
	public void setBrand(String brand) {
		this.brand = brand;
	}
		
	public String getSize() {
		return size;
	}
	
	public void setSize(String size) {
		this.size = size;
	}
		
	public String getColor() {
		return color;
	}
	
	public void setColor(String color) {
		this.color = color;
	}
		
	public String getAbout() {
		return about;
	}
	
	public void setAbout(String about) {
		this.about = about;
	}
	
}

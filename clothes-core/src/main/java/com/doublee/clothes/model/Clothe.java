package com.doublee.clothes.model;

import org.springframework.hateoas.ResourceSupport;

public class Clothe extends ResourceSupport{
	
	private String code;
	private String name;
	private String brand;
	private ClotheSize size;
	private String color;
	private String about;
		
	public Clothe() {super();}
		
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
		
	public String getBrand() {
		return brand;
	}
	
	public void setBrand(String brand) {
		this.brand = brand;
	}
		
	public ClotheSize getSize() {
		return size;
	}
	
	public void setSize(ClotheSize size) {
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

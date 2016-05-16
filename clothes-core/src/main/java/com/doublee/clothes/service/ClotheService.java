package com.doublee.clothes.service;

import java.util.List;

import com.doublee.clothes.model.Clothe;

public interface ClotheService {

	public List<Clothe> listAll();
	
	public void add(Clothe clothe);

	public Clothe remove(int index);

	public Clothe find(int index);

	public Clothe update(int index, Clothe updClothe);

}
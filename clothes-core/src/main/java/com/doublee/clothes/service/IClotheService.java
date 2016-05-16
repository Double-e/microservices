package com.doublee.clothes.service;

import java.util.List;

import com.doublee.clothes.model.Clothe;

public interface IClotheService {

	public List<Clothe> listAll();
	
	/**
	 * 
	 * @param clothe
	 */
	public void add(Clothe clothe);

	/**
	 * 
	 * @param index
	 * @return
	 */
	public Clothe remove(int index);

	/**
	 * 
	 * @param index
	 * @return
	 */
	public Clothe find(int index);

	/**
	 * 
	 * @param index
	 * @param updClothe
	 * @return
	 */
	public Clothe update(int index, Clothe updClothe);

}
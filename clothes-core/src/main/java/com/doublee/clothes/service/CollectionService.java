package com.doublee.clothes.service;

import java.util.List;

import com.doublee.clothes.model.Collection;

public interface CollectionService {

	public List<Collection> listAll();
	
	public void add(Collection Collection);

	public Collection remove(int index);

	public Collection find(int index);

	public Collection update(int index, Collection updCollection);
	
}

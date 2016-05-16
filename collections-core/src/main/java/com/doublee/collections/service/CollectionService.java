package com.doublee.collections.service;

import java.util.List;

import com.doublee.collections.model.Collection;

public interface CollectionService {

	public List<Collection> listAll();
	
	public void add(Collection Collection);

	public Collection remove(int index);

	public Collection find(int index);

	public Collection update(int index, Collection updCollection);
	
}

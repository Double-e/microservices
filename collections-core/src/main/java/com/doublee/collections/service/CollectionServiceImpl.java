package com.doublee.collections.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.doublee.collections.model.Collection;


@Service
public class CollectionServiceImpl implements CollectionService{

	private List<Collection> collections;
		
	public CollectionServiceImpl() {
		
		this.collections = new ArrayList<Collection>();
		
		Collection co1 = new Collection();
		co1.setCode("0");
		co1.setName("Repositories Collection");
		
		Collection co2 = new Collection();
		co2.setCode("1");
		co2.setName("Geeks@Python Collection");
		
		collections.add(co1);
		collections.add(co2);
	}
	
	@Override
	public List<Collection> listAll() {
		
		return this.collections;
	}

	@Override
	public void add(Collection Collection) {
		
	}

	@Override
	public Collection remove(int index) {
		
		return null;
	}

	@Override
	public Collection find(int index) {
		
		return this.collections.get(index);
	}

	@Override
	public Collection update(int index, Collection updCollection) {
		
		return null;
	}

}

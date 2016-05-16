package com.doublee.clothes.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.doublee.clothes.model.Clothe;
import com.doublee.clothes.model.Collection;
import com.doublee.clothes.model.enums.ClotheSize;
import com.doublee.clothes.service.ClotheService;
import com.doublee.clothes.service.CollectionService;

@Service
public class CollectionServiceImpl implements CollectionService{

	private List<Collection> collections;
	
	@Autowired
	private ClotheService clotheService;
	
	public CollectionServiceImpl() {
		
		this.collections = new ArrayList<Collection>();
		
		List<Clothe> clothes = new ArrayList<Clothe>();
		
		// mocking data.
		Clothe c1 = new Clothe();
		c1.setCode("0");
		c1.setName("GitHub T-shirt");
		c1.setBrand("Double");
		c1.setColor("black");
		c1.setSize(ClotheSize.S);
		c1.setAbout("Inspired in programmers that use this amazing SCM System.");		
		Clothe c2 = new Clothe();
		c2.setCode("1");
		c2.setName("Tomcat Shirt");
		c2.setBrand("Double");
		c2.setColor("white");
		c2.setSize(ClotheSize.M);
		c2.setAbout("For Apache Tomcat Web Server lovers and coders.");
		
		// adding values to clothes arraylist.
		clothes.add(c1);
		clothes.add(c2);
		
		Collection co1 = new Collection();
		co1.setCode("0");
		co1.setName("Repositories Collection");
		co1.setClothes(clothes);
		
		collections.add(co1);
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

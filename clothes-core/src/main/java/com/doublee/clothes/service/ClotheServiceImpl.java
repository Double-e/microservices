package com.doublee.clothes.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.doublee.clothes.model.Clothe;
import com.doublee.clothes.model.ClotheSize;

@Service
public class ClotheServiceImpl implements ClotheService {
	
	private List<Clothe> clothes;
	
	public ClotheServiceImpl(){
		// initializing array of clothes
		clothes = new ArrayList<Clothe>();
		
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
	}
	
	@Override
	public List<Clothe> listAll(){
		return this.clothes;
	}
	
	/* (non-Javadoc)
	 * @see com.doublee.clothes.service.IClotheService#add(com.doublee.clothes.model.Clothe)
	 */
	
	@Override
	public void add(Clothe clothe){
		this.clothes.add(clothe);
	}
	
	/* (non-Javadoc)
	 * @see com.doublee.clothes.service.IClotheService#remove(int)
	 */
	@Override
	public Clothe remove(int index){
		return this.clothes.remove(index);
	}
	
	/* (non-Javadoc)
	 * @see com.doublee.clothes.service.IClotheService#find(int)
	 */
	@Override
	public Clothe find(int index) {
		return this.clothes.get(index);
	}
	
	/* (non-Javadoc)
	 * @see com.doublee.clothes.service.IClotheService#update(int, com.doublee.clothes.model.Clothe)
	 */
	@Override
	public Clothe update(int index, Clothe updClothe) {
		Clothe clothe = this.clothes.get(index);
		clothe.setName(updClothe.getName());
		clothe.setBrand(updClothe.getBrand());
		clothe.setColor(updClothe.getColor());
		clothe.setCode(updClothe.getCode());
		clothe.setSize(updClothe.getSize());
		clothe.setAbout(updClothe.getAbout());
		return clothe;
	}	
	
}

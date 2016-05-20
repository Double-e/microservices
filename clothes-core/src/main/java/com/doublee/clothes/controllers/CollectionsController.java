package com.doublee.clothes.controllers;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.doublee.clothes.model.Collection;
import com.doublee.clothes.service.CollectionService;

@RestController
@RequestMapping(value = "/collections", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class CollectionsController {

	///////////////////////////////////////////////////////////////////////////////	
	// ATRIBUTES //////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////////	
	

	private static final Logger LOGGER = LoggerFactory.getLogger(CollectionsController.class);

	@Autowired
	private CollectionService collectionService;
	
	
	
	///////////////////////////////////////////////////////////////////////////////	
	// METHODS ////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////////	


	/**
	 * LIST OF COLLECTIONS.
	 * @return THE LIST OF COLLECTIONS.
	 */
	@RequestMapping(method = RequestMethod.GET)
	@ResponseStatus(HttpStatus.OK)
	public List<Collection> list() {
		
		LOGGER.info("GET /collections : list().");
		
		List<Collection> collections = collectionService.listAll();
		
		for(Collection collection : collections) {
			collection.add(linkTo(methodOn(CollectionsController.class).detail(collection.getCode())).withSelfRel());
		}
		
		return collections;
	}
	
	
	
	/**
	 * GET THE DETAIL OF THE COLLECTION.
	 * @param code: THE CODE OF THE COLLECTION.
	 * @return THE FOUND COLLECTION OR NFE.
	 */
	@RequestMapping(value = "/{code}", method=RequestMethod.GET)
	@ResponseStatus(HttpStatus.OK)
	public Collection detail(@PathVariable final String code) 
	{
		LOGGER.info("GET /clothes/{} - detail().", code);
		
		return collectionService.find(Integer.parseInt(code));
	}
}

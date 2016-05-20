package com.doublee.clothes.controllers;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

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


@Api(value="collections", produces="application/json")
@RestController
@RequestMapping(value="/collections", 
			produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
public class CollectionsController {

	private static final Logger LOGGER = LoggerFactory.getLogger(CollectionsController.class);

	@Autowired
	private CollectionService collectionService;
		
	/**
	 * LIST.
	 * @return the list of clothes in the System.
	 */
	@ApiOperation(
	  value="List Collections", 
	  notes="List all the collections without any filter applied."
	)
	@ApiResponses(value= 
      {
	    @ApiResponse(
	      code=200, 
		  message="Successfully Retrieved Collection List.", 
		  response=Collection.class, 
		  responseContainer="Collection"
	    ),
	    @ApiResponse(
	  	  code=500, 
	  	  message="Internal Server Error - Collections."
	  	)
	  }
	)
	@RequestMapping(
	  value="",
	  method=RequestMethod.GET
	)
	@ResponseStatus(HttpStatus.OK)
	public List<Collection> list() 
	{
		LOGGER.info("GET /collections - retrieving list of collections.");
		
		List<Collection> collections = collectionService.listAll();
		
		for(Collection collection : collections) {
			collection.add(linkTo(methodOn(CollectionsController.class).detail(collection.getCode())).withSelfRel());
		}
		
		return collections;
	}
	
	/**
	 * DETAIL.
	 * @param id
	 * @return
	 */
	@ApiOperation(
	  value="Get Collection", 
	  notes="Show just one collection by its given Id."
	)
	@ApiResponses(value= 
      {
	    @ApiResponse(
	      code=200, 
		  message="Successfully Retrieved Collection.", 
		  response=Collection.class
		)
	  }
	)
	@RequestMapping(
	  value="/{id}",
	  method=RequestMethod.GET
	)
	@ResponseStatus(HttpStatus.OK)
	public Collection detail(
	  @ApiParam(value="Id to lookup for", required=true)
	  @PathVariable(value="id")
	    final String id) 
	{
		LOGGER.info("GET /clothes/{} - get clothe detail.", id);
		
		// returning clothe.
		return collectionService.find(Integer.parseInt(id));
	}
}

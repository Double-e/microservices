package com.doublee.clothes.rest;

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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

import com.doublee.clothes.model.Clothe;
import com.doublee.clothes.service.ClotheService;

@Api(value="clothes",
	 produces="application/json")
@RestController
@RequestMapping(value="/clothes", 
				produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
public class ClothesController {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(ClothesController.class);

	@Autowired
	private ClotheService clotheService;
		
	/**
	 * LIST.
	 * @return the list of clothes in the System.
	 */
	@ApiOperation(
	  value="List Clothes", 
	  notes="List all the clothes without any filter applied."
	)
	@ApiResponses(value= 
      {
	    @ApiResponse(
	      code=200, 
		  message="Successfully Retrieved Clothes List.", 
		  response=Clothe.class, 
		  responseContainer="Collection"
	    ),
	    @ApiResponse(
	  	  code=500, 
	  	  message="Internal Server Error - Clothes."
	  	)
	  }
	)
	@RequestMapping(
	  value="",
	  method=RequestMethod.GET
	)
	@ResponseStatus(HttpStatus.OK)
	public List<Clothe> list() 
	{
		LOGGER.info("GET /clothes - retrieving list of clothes.");
		
		// returning list.
		List<Clothe> clothes = clotheService.listAll();		
		for(Clothe clothe: clothes) {
			clothe.add(linkTo(methodOn(ClothesController.class).detail(clothe.getCode())).withSelfRel());
		}
		
		return clothes;
	}
	
	/**
	 * CREATE.
	 * @param clothe to be saved.
	 * @return the created clothe.
	 */
	@ApiOperation(
	  value="Create Clothe", 
	  notes="Create a new Clothe."
	)
	@ApiResponses(value= 
      {
	    @ApiResponse(
	      code=201, 
		  message="Successfully Created Clothe.", 
		  response=Clothe.class
		)
	  }
	)
	@RequestMapping(
	  method=RequestMethod.POST
	)
	@ResponseStatus(HttpStatus.CREATED)
	public Clothe create(
			@RequestBody final Clothe clothe) {
		
		LOGGER.info("POST /clothes - Create new clothe.");
		
		clotheService.add(clothe);
		return clothe;
	}

	/**
	 * DELETE.
	 * @param id The parameter to remove the clothe.
	 * @return the removed clothe.
	 */
	@ApiOperation(
	  value="Delete Clothe", 
	  notes="Delete a clothe by its given Id."
	)
	@ApiResponses(value= 
	  {
	    @ApiResponse(
		  code=200, 
		  message="Successfully Removed Clothe.", 
		  response=Clothe.class
		)
	  }
	)
	@RequestMapping(
	  value="/{id}",
	  method=RequestMethod.DELETE
	)
	@ResponseStatus(HttpStatus.OK)
	public Clothe delete(
	  @ApiParam(value="Id to delete for", required=true)
	  @PathVariable(value="id")
		final String id) {
		
		LOGGER.info("DELETE /clothes/{} - delete clothe.", id);
		
		Clothe clothe = clotheService.remove(Integer.parseInt(id));
		return clothe;
	}
	
	
	/**
	 * DETAIL.
	 * @param id the identifier of the clothe.
	 * @return the found clothe.
	 */
	@ApiOperation(
	  value="Get Clothe", 
	  notes="Show just one clothe by its given Id."
	)
	@ApiResponses(value= 
      {
	    @ApiResponse(
	      code=200, 
		  message="Successfully Retrieved Clothe.", 
		  response=Clothe.class
		)
	  }
	)
	@RequestMapping(
	  value="/{id}",
	  method=RequestMethod.GET
	)
	@ResponseStatus(HttpStatus.OK)
	public Clothe detail(
	  @ApiParam(value="Id to lookup for", required=true)
	  @PathVariable(value="id")
	    final String id) 
	{
		LOGGER.info("GET /clothes/{} - get clothe detail.", id);
		
		// returning clothe.
		return clotheService.find(Integer.parseInt(id));
	}
	
	
	/**
	 * UPDATE CLOTHE.
	 * @param id the id of the cloud.
	 * @param clotheUpd the updated content of the clothe.
	 * @return the updated clothe.
	 */
	@ApiOperation(
	  value="Update Clothe", 
	  notes="Update a clothe by its given Id."
	)
	@ApiResponses(value= 
	  {
	    @ApiResponse(
		  code=200, 
		  message="Successfully Updated Clothe.", 
		  response=Clothe.class
		)
	  }
	)
	@RequestMapping(
	  value="/{id}",
	  method=RequestMethod.PUT
	)
	@ResponseStatus(HttpStatus.OK)
	public Clothe update(
	  @ApiParam(value="Id to update for", required=true)
	  @PathVariable(value="id")
		final String id,
	  @ApiParam(value="Clothe updated", required=true)
	  @RequestBody 
	    final Clothe clotheUpd) 
	{
		LOGGER.info("PUT /clothes/{} - retrieving list of clothes.", id);
		
		// update clothe.
		Clothe clothe = clotheService.update(Integer.parseInt(id), clotheUpd);		
		// return clothe.
		return clothe;
	}
	
}

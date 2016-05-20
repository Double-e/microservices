package com.doublee.clothes.controllers;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.doublee.clothes.exceptions.ClotheNotFoundException;
import com.doublee.clothes.model.Clothe;
import com.doublee.clothes.service.ClotheService;
 
@RestController
@RequestMapping(value = "/clothes", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class ClothesController {
	
	///////////////////////////////////////////////////////////////////////////////	
	// ATRIBUTES //////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////////	
	
	
	private static final Logger LOGGER = LoggerFactory.getLogger(ClothesController.class);

	@Autowired
	private ClotheService clotheService;
		
	
	
	///////////////////////////////////////////////////////////////////////////////	
	// METHODS ////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////////	
	
	
	/**
	 * LIST THE CLOTHES.
	 * @return THE LIST OF CLOTHES.
	 */
	@RequestMapping(method = RequestMethod.GET)
	@ResponseStatus(HttpStatus.OK)
	public List<Clothe> list() {
		
		LOGGER.info("GET /clothes : list().");
		
		List<Clothe> clothes = clotheService.listAll();	
		
		for(Clothe clothe: clothes) {
			clothe.add(linkTo(methodOn(ClothesController.class).detail(clothe.getCode())).withSelfRel());
		}
		
		return clothes;
	}
	
	
	
	/**
	 * GET DETAIL OF THE CLOTHE.
	 * @param code: THE CODE OF THE CLOTHE.
	 * @return THE FOUND CLOTHE OR CFE.
	 */
	@RequestMapping(value = "/{code}", method = RequestMethod.GET)
	@ResponseStatus(HttpStatus.OK)
	public Clothe detail(@PathVariable final String code) {
		
		Clothe clothe = clotheService.find(Integer.parseInt(code));
		
		if (clothe == null) {
			throw new ClotheNotFoundException("GET /clothes/" + code + " - detail() NOT FOUND.");
		}
		
		LOGGER.info("GET /clothes/{} : get clothe detail.", code);
		
		return clothe;
	}
	
	
	
	/**
	 * CREATE A NEW CLOTHE.
	 * @param clothe : THE CLOTHE BODY CONTENT.
	 * @return THE RECENTLY CREATED CLOTHE.
	 */
	@RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseStatus(HttpStatus.CREATED)
	public Clothe create(@RequestBody final Clothe clothe) {
		
		LOGGER.info("POST /clothes : create().");
		
		clotheService.add(clothe);
		
		return clothe;
	}

	
	
	/**
	 * DELETE A CLOTHE BY ITS GIVEN CODE.
	 * @param code: THE CODE OF THE CLOTHE.
	 * @return the removed clothe.
	 */
	@RequestMapping(value = "/{code}", method = RequestMethod.DELETE)
	@ResponseStatus(HttpStatus.OK)
	public Clothe delete(@PathVariable final String code) {
				
		Clothe clothe = clotheService.find(Integer.parseInt(code));
		
		if (clothe == null) {
			throw new ClotheNotFoundException("DELETE /clothes/"+code+" : delete() NOT FOUND.");
		}
		
		clothe = clotheService.remove(Integer.parseInt(clothe.getCode()));
		
		LOGGER.info("DELETE /clothes/{} : delete().", code);
		
		return clothe;
	}
	
	
	
	/**
	 * UPDATE A CLOTHE BY ITS GIVEN CODE.
	 * @param code THE CODE OF THE CLOTHE.
	 * @param clotheUpd THE BODY CONTENT TO BE REPLACED.
	 * @return THE UPDATED CLOTHE.
	 */
	@RequestMapping(value = "/{code}", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseStatus(HttpStatus.OK)
	public Clothe update(@PathVariable final String code, @RequestBody final Clothe clotheUpd){
		
		Clothe clothe = clotheService.find(Integer.parseInt(code));
		
		if (clothe == null) {
			throw new ClotheNotFoundException("PUT /clothes/"+code+" : update() NOT FOUND.");
		}	
		
		LOGGER.info("PUT /clothes/{} : update().", code);
		
		clothe = clotheService.update(Integer.parseInt(code), clotheUpd);		
		
		return clothe;
	}
	
	
	
	///////////////////////////////////////////////////////////////////////////////
	// ERROR HANDLERS /////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////////
		
	
	/**
	 * HANDLES CLOTHE NOT FOUND EXCEPTION.
	 * @param exception: THE RAISED EXCEPTION.
	 * @param response: THE RESPONSE THAT CONTAINS THE EXCEPTION TO THE REQUESTER.
	 * @throws IOException
	 */
	@ExceptionHandler(ClotheNotFoundException.class)
	public void handleClotheNotFoundException(ClotheNotFoundException exception, HttpServletResponse response) 
			throws IOException {
		
		LOGGER.error(exception.getMessage());
		
		response.sendError(HttpStatus.NOT_FOUND.value(), exception.getMessage());
	}
	
}

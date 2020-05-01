package com.ms.bootcamp.currencyinventory.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ms.bootcamp.currencyinventory.exception.MessageGenericException;
import com.ms.bootcamp.currencyinventory.model.Currency;
import com.ms.bootcamp.currencyinventory.model.CurrencyResponse;
import com.ms.bootcamp.currencyinventory.model.Request;
import com.ms.bootcamp.currencyinventory.service.CurrencyInventoryService;




@RestController
@RefreshScope
public class CurrencyInventoryController {
	
	@Autowired
	CurrencyInventoryService service;
	
	@GetMapping("/conversionfactors")
	public List<Currency> getAllConversionfactors(){
		
		return service.getAllConversionFactors();
		
	}
	
	@GetMapping("/conversionfactors/{isoCode}")
	public ResponseEntity<List<Currency>> getProduct(@PathVariable String isoCode) {
		List<Currency> c = service.getConvfactorByCountry(isoCode);
		if (!c.isEmpty()) {
			return new ResponseEntity<List<Currency>>(c,HttpStatus.FOUND);
		} else {
			throw new MessageGenericException("Country code not found");
		}
	}
	
	@RequestMapping(value="/addconversionfactor",method = RequestMethod.POST)
	public CurrencyResponse addConversionFactor(@RequestBody Request request) {
		
		CurrencyResponse response=null;
		response = service.addCurrencyFactor(request);		
		return response;
			
		
	}
	
	
	  @RequestMapping(value="/updateconversionfactor",method = RequestMethod.POST)
	  public CurrencyResponse updateConversionFactor(@RequestBody Request request)
	  {
	  
		  CurrencyResponse response=null; 
		  response = service.updateCurrencyFactor(request); 
		  return response;
	  
	  
	  }
	 

}

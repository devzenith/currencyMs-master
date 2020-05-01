package com.ms.bootcamp.currencyinventory.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ms.bootcamp.currencyinventory.model.Currency;
import com.ms.bootcamp.currencyinventory.model.CurrencyResponse;
import com.ms.bootcamp.currencyinventory.model.Request;
import com.ms.bootcamp.currencyinventory.repo.CurrencyRepository;

@Service
public class CurrencyInventoryService {
	
	@Autowired
	CurrencyRepository repo;
	
	public List<Currency> getAllConversionFactors(){
		
		 return repo.findAll();		
		
	}
	
	public List<Currency> getConvfactorByCountry(String isoCode) {
		
		System.out.println("Country code at service >>>>>>>>>>>>> " + isoCode);
		return repo.findByCountryCode(isoCode);		
		
		
	}
	@Transactional
	public CurrencyResponse addCurrencyFactor(Request request) {
		
		CurrencyResponse response = new CurrencyResponse();
		
		List<Currency> existingList = repo.findByCountryCode(request.getCountryCode());	
		
		if(!existingList.isEmpty())
		{
			response.setErrorMessage("Country code already added..");
		}
		else {
			System.out.println("country code - curr factor " + request.getCountryCode() + "--" + request.getConversionFactor());
			Currency p = new Currency(request.getCountryCode(), request.getConversionFactor());
			
			System.out.println("Primary key at this point " + p.getId());
			repo.save(p);
			response.setStatusMessage("Country Code - Conversion factor added");
		}
		
		return response;
		
	}
	
	
	  @Transactional 
	  public CurrencyResponse updateCurrencyFactor(Request request)
	  {
	  
		  CurrencyResponse response = new CurrencyResponse();
	  
		  int status = repo.setFixedConversionFactorFor(request.getConversionFactor(),request.getCountryCode()); 
		  System.out.println("update status " + status);
		  response.setStatusMessage("Conversion Factor updated");
	  
		  return response; 
	  }
	 

}

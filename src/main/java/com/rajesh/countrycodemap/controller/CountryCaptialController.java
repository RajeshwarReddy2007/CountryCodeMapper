package com.rajesh.countrycodemap.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.rajesh.countrycodemap.service.CountryCapitalService;

import java.util.logging.Logger;

/**
 * This class acts as Rest Controller to receive requests
 * @author Rajesh Reddy Patlola
 *
 */
@RestController
public class CountryCaptialController {
	
	@Autowired
	private CountryCapitalService countryCapitalService;

	Logger logger=Logger.getLogger("CountryCaptialController");

	@GetMapping("/fetch")
	public ResponseEntity<String> getCountryDetails(@RequestParam(value = "country") String requestParameter){
		

		String capitalCity=countryCapitalService.getCapitalCity(requestParameter);
		if(StringUtils.isEmpty(capitalCity)){
			capitalCity="Invalid Country Code or Country Name. So couldn't fetch Capital City";
		}
		
		System.out.println("Result:"+capitalCity);
		return new ResponseEntity<>(capitalCity,HttpStatus.ACCEPTED);
	}

}

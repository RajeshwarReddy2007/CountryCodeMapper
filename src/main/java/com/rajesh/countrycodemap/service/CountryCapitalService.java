package com.rajesh.countrycodemap.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.rajesh.countrycodemap.cache.CacheBuilder;

/**
 * Service class for CountryCapitalService
 */
@Service
public class CountryCapitalService {

	@Autowired
	CacheBuilder cacheBuilder;
	
	/**
	 * Fetches capital city based on country name/ country code
	 * @param requestParameter
	 * @return Capital City
	 */
	public String getCapitalCity(String requestParameter){
		
		if(StringUtils.isEmpty(requestParameter))
			return null;
		
		System.out.println("Search Capital City for request parameter:"+requestParameter);
		
		String capitalCity= cacheBuilder.findCapitalByCountryCode(requestParameter);
		
		return StringUtils.isEmpty(capitalCity) ? 
				cacheBuilder.findCapitalByCountryName(requestParameter) : capitalCity;
	}
}

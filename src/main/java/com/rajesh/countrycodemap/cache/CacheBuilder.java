package com.rajesh.countrycodemap.cache;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.context.annotation.Bean;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import org.springframework.web.client.RestTemplate;

import com.rajesh.countrycodemap.dto.CountryDetails;
import com.rajesh.countrycodemap.dto.CountryDetailsList;

@Component
/**
 * Builds Cache with country details
 * @author Rajesh Reddy Patlola
 */
public class CacheBuilder {
	
	Map<String,CountryDetails> countryNameCountryDetailsMap=new HashMap<>();
	Map<String,CountryDetails> countryCode2CountryDetailsMap=new HashMap<>();
	Map<String,CountryDetails> countryCode3CountryDetailsMap=new HashMap<>();
	Map<Integer,Map<String,CountryDetails>> countryCodeLengthMap=new HashMap<>();

	@Autowired
    private RestTemplate restTemplate;

	@Bean
    private RestTemplate restTemplate() {
        return new RestTemplate();
    }


    /**
	 * Finds capital by country name if exists,else returns null
	 * @param countryName
	 * @return
	 */
	public String findCapitalByCountryName(String countryName){

		String lowerCaseCountryName=countryName.toLowerCase();

		return countryNameCountryDetailsMap.containsKey(lowerCaseCountryName) ?
				countryNameCountryDetailsMap.get(lowerCaseCountryName).getCapital() : null;
			
	}
	
	/**
	 * Finds capital by country code if exists,else returns null
	 * @param countryCode
	 * @return
	 */
	public String findCapitalByCountryCode(String countryCode){
		
		if(countryCode == null || countryCode.isEmpty())
			return null;
		
		int length=countryCode.length();
		String lowerCaseCountryCode=countryCode.toLowerCase();
		
		Map<String,CountryDetails> countryCodeMap=countryCodeLengthMap.containsKey(length) ?
				countryCodeLengthMap.get(length) : null;
				
				if(CollectionUtils.isEmpty(countryCodeMap))
					return null;
				
		return countryCodeMap.containsKey(lowerCaseCountryCode) ?
				countryCodeMap.get(lowerCaseCountryCode).getCapital() : null;
	}
	
	/**
	 * Builds country code to country details map and country name to country details map
	 *
	 */
	public void build(){
		
		System.out.println("Building Country Details Cache..");
		ResponseEntity<String> countryDetailsEntity= restTemplate.getForEntity("https://restcountries" +
                        ".eu/rest/v2/all",
				String.class);
		
		String countryDetailsJson=countryDetailsEntity.getBody();
        CountryDetails[] countryDetailsList=null;
        ObjectMapper objectMapper=new ObjectMapper();
        objectMapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);

        try {
            countryDetailsList= objectMapper.readValue(countryDetailsJson,CountryDetails[].class);
            objectMapper.disable(DeserializationFeature.FAIL_ON_IGNORED_PROPERTIES);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        for(CountryDetails countryDetail:countryDetailsList){
			countryNameCountryDetailsMap.put(countryDetail.getName().toLowerCase(), countryDetail);
			countryCode2CountryDetailsMap.put(countryDetail.getAlpha2Code().toLowerCase(), countryDetail);
			countryCode3CountryDetailsMap.put(countryDetail.getAlpha3Code().toLowerCase(), countryDetail);
		}
		
		
		countryCodeLengthMap.put(2, countryCode2CountryDetailsMap);
		countryCodeLengthMap.put(3, countryCode3CountryDetailsMap);
	}


}

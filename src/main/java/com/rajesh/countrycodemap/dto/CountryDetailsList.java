package com.rajesh.countrycodemap.dto;

import java.util.ArrayList;
import java.util.List;

public class CountryDetailsList {
	private List<CountryDetails> countryDetailsList=new ArrayList<>();

	public List<CountryDetails> getCountryDetailsList() {
		return countryDetailsList;
	}

	public void setCountryDetailsList(List<CountryDetails> countryDetailsList) {
		this.countryDetailsList = countryDetailsList;
	}
		
}

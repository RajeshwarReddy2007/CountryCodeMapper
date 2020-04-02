package com.rajesh.countrycodemap.controller;

import com.rajesh.countrycodemap.controller.CountryCaptialController;
import com.rajesh.countrycodemap.service.CountryCapitalService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

/**
 * @author Rajesh Reddy Patlola
 */

public class CountryCapitalControllerTest {

    @Mock
    CountryCapitalService countryCapitalService;

    @InjectMocks
    CountryCaptialController countryCaptialController;

    @Before
    public void init(){
        MockitoAnnotations.initMocks(this);
    }


    @Test
    public void getCountryDetailsTest(){

        String reqParam="US";
        when(countryCapitalService.getCapitalCity(reqParam)).thenReturn("Washington DC");

        ResponseEntity<String> responseEntity=countryCaptialController.getCountryDetails(reqParam);
        assertEquals(responseEntity.getStatusCode(),HttpStatus.ACCEPTED);
        assertEquals(responseEntity.getBody(),"Washington DC");
    }

    @Test
    public void getCountryDetailsForInvalidInputTest(){

        String reqParam="ZYS";
        when(countryCapitalService.getCapitalCity(reqParam)).thenReturn(null);

        ResponseEntity<String> responseEntity=countryCaptialController.getCountryDetails(reqParam);
        assertEquals(responseEntity.getStatusCode(),HttpStatus.ACCEPTED);
        assertEquals(responseEntity.getBody(),"Invalid Country Code or Country Name. So couldn't fetch Capital City");
    }
}

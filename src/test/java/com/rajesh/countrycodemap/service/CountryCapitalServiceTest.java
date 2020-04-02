package com.rajesh.countrycodemap.service;

import com.rajesh.countrycodemap.cache.CacheBuilder;
import com.rajesh.countrycodemap.service.CountryCapitalService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

/**
 * @author Rajesh Reddy Patlola
 */

public class CountryCapitalServiceTest {

    @Mock
    CacheBuilder cacheBuilder;

    @InjectMocks
    CountryCapitalService countryCapitalService;

    @Before
    public  void init(){

        MockitoAnnotations.initMocks(this);
    }


    @Test
    public void getCapitalCityByCountryCodeTest(){

        String requestParam="US";

        when(cacheBuilder.findCapitalByCountryCode(requestParam)).thenReturn("Washington DC");

       String val= countryCapitalService.getCapitalCity(requestParam);

        assertEquals("Washington DC",val);


    }

    @Test
    public void getCapitalCityByCountryCodeNullTest(){

        String requestParam="India";

        when(cacheBuilder.findCapitalByCountryCode(requestParam)).thenReturn(null);
        when(cacheBuilder.findCapitalByCountryName(requestParam)).thenReturn("New Delhi");


        String val= countryCapitalService.getCapitalCity(requestParam);

        assertEquals("New Delhi",val);


    }

    @Test
    public void getCapitalCityByCountryNullTest(){

        String requestParam="Wrong country";

        when(cacheBuilder.findCapitalByCountryCode(requestParam)).thenReturn(null);
        when(cacheBuilder.findCapitalByCountryName(requestParam)).thenReturn(null);


        String val= countryCapitalService.getCapitalCity(requestParam);

        assertEquals(null,val);


    }


}

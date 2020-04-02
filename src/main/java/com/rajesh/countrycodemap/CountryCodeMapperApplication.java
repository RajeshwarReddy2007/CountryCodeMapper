package com.rajesh.countrycodemap;

import com.rajesh.countrycodemap.cache.CacheBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

/**
 * Application to find Capital Cities for given Country Name/Code
 */
@SpringBootApplication
@EnableCaching
public class CountryCodeMapperApplication implements CommandLineRunner {

    @Autowired
    private CacheBuilder cacheBuilder;

	public static void main(String[] args) {
		

		SpringApplication.run(CountryCodeMapperApplication.class, args);
		
	}

    @Override
    public void run(String... args) throws Exception {
        System.out.print("Starting..");
        cacheBuilder.build();
    }
}
 
package com.kata.taxcalculation;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.*;

import com.kata.taxcalculation.domain.Product;
import com.kata.taxcalculation.domain.ProductCategory;
import com.kata.taxcalculation.repository.ProductRepository;

@SpringBootApplication
public class TaxCalculationApplication {

	private static final Logger log = LoggerFactory.getLogger(TaxCalculationApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(TaxCalculationApplication.class, args);
	}

	@Bean
	public CommandLineRunner commandLineRunner(ApplicationContext ctx) {
		return (args) -> {
			log.info("Start Application ...");
			System.out.println("Start Application Kata for Tax Calculation ...");
			System.out.println("H2 database is used, console under localhost:8080/console");
			Product product1 = new Product("aProduct", 29, ProductCategory.BOOK, 
					false, false, 20);
			ctx.getBean(ProductRepository.class).save(product1);
			
			Product productFromDB = ctx.getBean(ProductRepository.class).findById(1L).get();
			System.out.println(productFromDB);
			System.out.println(productFromDB.getProductCategory().toString());
		};
	}

}

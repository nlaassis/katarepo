package com.kata.taxcalculation;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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

	private static final Logger log = org.slf4j.LoggerFactory.getLogger(TaxCalculationApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(TaxCalculationApplication.class, args);
	}

//	@Override
//	public void run(String... args) throws Exception {
//
//        System.out.println("TO do ...");
//        //List<Product> products = new ArrayList<Product>();
//        
////		System.out.println("Inspection des beans fournis");
////
////		String[] beanNames = ctx.getBeanDefinitionNames();
////		Arrays.sort(beanNames);
////		for (String beanName : beanNames) {
////			System.out.println(beanName);
////		}
//	}

	@Bean
	public CommandLineRunner commandLineRunner(ApplicationContext ctx) {
		return (args) -> {
			System.out.println("Start Application Kata for Tax Calculation ...");
			System.out.println("H2 database is used, console under localhost:8080/console");
			Product product1 = new Product("sdcjbs", 29, ProductCategory.BOOK, 
					false, false, 20);
			ctx.getBean(ProductRepository.class).save(product1);
			
			Product productFromDB = ctx.getBean(ProductRepository.class).findById(1L).get();
			System.out.println(productFromDB);
			System.out.println(productFromDB.getProductCategory().toString());
		};
	}

}

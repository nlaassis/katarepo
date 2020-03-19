package com.kata.taxcalculation;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.kata.taxcalculation.domain.Product;
import com.kata.taxcalculation.model.Basket;
import com.kata.taxcalculation.model.BasketEntry;
import com.kata.taxcalculation.model.TaxComputerFactory;
import com.kata.taxcalculation.repository.ProductRepository;
import com.kata.taxcalculation.service.TaxCalculationService;

@SpringBootTest
class TaxCalculationApplicationTests {

    @Autowired
    TaxCalculationService taxCalculationService;

    @Autowired
    ProductRepository productRepo;

	@Test
	void contextLoads() {
	}

	@Test
	public void testRoundingUC1() {
		double tax = 0.99;
		double taxRounded = TaxComputerFactory.roundTo5CentsUp(tax);
		assertEquals(1.00, taxRounded);
	}

	@Test
	public void testRoundingUC2() {
		double tax = 1.00;
		double taxRounded = TaxComputerFactory.roundTo5CentsUp(tax);
		assertEquals(1.00, taxRounded);
	}
	
	@Test
	public void testRoundingUC3() {
		double tax = 1.01;
		double taxRounded = TaxComputerFactory.roundTo5CentsUp(tax);
		assertEquals(1.05, taxRounded);
	}
	
	@Test
	public void testRoundingUC4() {
		double tax = 1.02;
		double taxRounded = TaxComputerFactory.roundTo5CentsUp(tax);
		assertEquals(1.05, taxRounded);
	}
//
//	private double computeRoundedDouble(double tax) {
//		int taxInteger = (int)(tax * 100);
//		double taxTempRounded = taxInteger % 5 == 0 ? tax : ((double)(((taxInteger / 5) * 5) + 5)) / 100;
//		return taxTempRounded;
//	}
	
	/**
	 * 
	 * 2 livres à 12.49€ : 27,5€ TTC
	 * 1 CD musical à 14.99€ : 18€ TTC
	 * 3 barres de chocolat à 0.85€ : 2.55€ TTC
	 *
	 * Montant des taxes : 5.53€
	 * Total : 48.05€
	 */
	@Test
	public void testTaxCalculationUC1() {
		Product p1 = Product.builder()
				.name("livres")
				.price(12.49)
				.tax(10).isEssential(false)
				.isImported(false)
				.build();
		Product p2 = Product.builder()
				.name("CD musical")
				.price(14.99)
				.tax(20).isEssential(false)
				.isImported(false)
				.build();
		Product p3 = Product.builder()
				.name("barres de chocolat")
				.price(0.85)
				.tax(0).isEssential(true)
				.isImported(false)
				.build();
		;
		productRepo.save(p1);
		productRepo.save(p2);
		productRepo.save(p3);

		Basket panier = new Basket();
		panier.getContent().add(BasketEntry.builder().product(p1).itemCount(2).build());
		panier.getContent().add(BasketEntry.builder().product(p2).itemCount(1).build());
		panier.getContent().add(BasketEntry.builder().product(p3).itemCount(3).build());
	
		taxCalculationService.computeBasketPrice(panier);
		System.out.println(panier.toString());
		
		assertEquals("5.53", panier.getTotalTaxes().toString());
		assertEquals("48.05", panier.getTotalTtc().toString());
	}
}

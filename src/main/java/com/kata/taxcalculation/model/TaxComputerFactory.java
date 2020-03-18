package com.kata.taxcalculation.model;

import com.kata.taxcalculation.domain.Product;

public class TaxComputerFactory {
    public static TaxComputer createTaxComputer(Product p) {
        return (nbOfItems) -> { 
        	double tax = p.getTax();
        	if(p.isEssential()) {
        		tax = 0;
        	}
        	if(p.isImported()) {
        		tax = tax + 10;
        	}

        	double totalPriceHt = p.getPrice() * nbOfItems;
        	double totalTaxTemp = (totalPriceHt * tax / 100);
        	double totalTaxRounded = roundTo5CentsUp(totalTaxTemp);

        	double priceTtc = totalPriceHt + totalTaxRounded;
        	priceTtc = roundTo5CentsUp(priceTtc);
        	return priceTtc;
        };
    }
    
    /**
     * | Taxe calculée | Taxe imputée |
	 * |---------------|--------------|
	 * |          0.99 |         1.00 |
	 * |          1.00 |         1.00 |
	 * |          1.01 |         1.05 |
	 * |          1.02 |         1.05 |
     * 
     * @param amount to round
     * @return rounded amount
     */
    public static double roundTo5CentsUp(double amount) {
		int taxInteger = (int)(amount * 100);
		double taxRounded = taxInteger % 5 == 0 ? amount : ((double)(((taxInteger / 5) * 5) + 5)) / 100;
		return taxRounded;
    }
}
package com.kata.taxcalculation.service;

import java.math.BigDecimal;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kata.taxcalculation.domain.Product;
import com.kata.taxcalculation.model.Basket;
import com.kata.taxcalculation.model.TaxComputer;
import com.kata.taxcalculation.model.TaxComputerFactory;
import com.kata.taxcalculation.repository.ProductRepository;

@Service
public class TaxCalculationService {

    @Autowired
    ProductRepository productRepo;

    public void computeBasketPrice(Basket basket) {

        final Map<Long, TaxComputer> taxByItem = basket.getContent().stream()
                .map(basketEntry -> productRepo.findById(basketEntry.getItem().getId()))
                .filter(Optional<Product>::isPresent)
                .map(Optional::get)
                .collect(
                        Collectors.toMap(
                            d -> d.getId(),
                            TaxComputerFactory::createTaxComputer
                        )
                );

        basket.getContent()
                .forEach(be -> {
                	be.setPttcPrice((taxByItem
                		.get(be.getItem().getId())
                        .computeTax(be.getItemCount())));
                	be.setPhtPrice(be.getItem().getPrice() * be.getItemCount());
                });
        
    	Double totalTtc = basket.getContent().stream().map((be) -> new BigDecimal(be.getPttcPrice())).reduce(new BigDecimal(0), BigDecimal::add).doubleValue();
    	Double totalHt = basket.getContent().stream().map((be) -> new BigDecimal(be.getPhtPrice())).reduce(new BigDecimal(0), BigDecimal::add).doubleValue();
    	Double totalTaxes = new BigDecimal(Double.toString(totalTtc)).subtract(new BigDecimal(Double.toString(totalHt))).doubleValue();
    	
    	basket.setTotalTtc(totalTtc);
    	basket.setTotalHt(totalHt);
    	basket.setTotalTaxes(totalTaxes);
    }
}

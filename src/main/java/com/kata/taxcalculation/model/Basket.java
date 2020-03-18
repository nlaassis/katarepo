package com.kata.taxcalculation.model;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import lombok.Data;

@Data
public class Basket {
    private List<BasketEntry> content = new ArrayList<BasketEntry>();
    
    @Override
    public String toString() {
    	Double totalTtc = content.stream().mapToDouble((be) -> be.getPttcPrice()).reduce(0, Double::sum);
    	Double totalHt = content.stream().mapToDouble((be) -> be.getPhtPrice()).reduce(0, Double::sum);
    	Double totalTaxes = totalTtc - totalHt;

    	StringBuilder sb = new StringBuilder();
    	sb.append("*****************************************************************\n");
    	sb.append(content.stream().map((be) -> be.toString()).collect(Collectors.joining("\n")));
    	sb.append("\n\n\n");
    	sb.append("Montant des taxes : ").append(totalTaxes).append("\n");
    	sb.append("Total : ").append(totalTtc).append("\n\n");
    	sb.append("*****************************************************************");
    	return sb.toString();
    }
}

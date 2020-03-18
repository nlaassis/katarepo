package com.kata.taxcalculation.model;

@FunctionalInterface
public interface TaxComputer {
    double computeTax(int nbOfItems);
}

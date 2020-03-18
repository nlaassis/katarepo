package com.kata.taxcalculation.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.kata.taxcalculation.domain.Product;

public interface ProductRepository extends CrudRepository<Product, Long> {

	@Override
	Optional<Product> findById(Long id);
	
}

package com.pellin.api.repository;

import com.pellin.api.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
/**
 *
 * @author eloipelmon
 */
public interface ProductRepository extends JpaRepository<Product, Integer> {
    
}

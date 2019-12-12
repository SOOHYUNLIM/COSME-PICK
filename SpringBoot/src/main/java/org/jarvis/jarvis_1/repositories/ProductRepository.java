package org.jarvis.jarvis_1.repositories;

import org.jarvis.jarvis_1.domain.Product;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * ProductRepository
 */
public interface ProductRepository extends JpaRepository<Product, Integer> {

    
}
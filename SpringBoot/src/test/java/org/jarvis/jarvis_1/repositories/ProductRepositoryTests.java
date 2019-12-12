package org.jarvis.jarvis_1.repositories;

import org.jarvis.jarvis_1.domain.Product;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * ProductRepositoryTests
 */
@SpringBootTest
public class ProductRepositoryTests {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private MemberRepository memberRepository;

    @Test
    public void addProduct() {
        productRepository.save(Product.builder().title("존바바").price(10000).member(memberRepository.getOne("user00")).build());
    }

}
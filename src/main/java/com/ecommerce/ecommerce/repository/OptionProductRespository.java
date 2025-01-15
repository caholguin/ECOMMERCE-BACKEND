package com.ecommerce.ecommerce.repository;

import com.ecommerce.ecommerce.entity.OptionProduct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OptionProductRespository extends JpaRepository<OptionProduct, Long> {
}

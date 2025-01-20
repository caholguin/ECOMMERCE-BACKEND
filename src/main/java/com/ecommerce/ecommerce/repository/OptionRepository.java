package com.ecommerce.ecommerce.repository;

import com.ecommerce.ecommerce.entity.Option;
import com.ecommerce.ecommerce.entity.OptionProduct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OptionRepository extends JpaRepository<Option, Long> {


}

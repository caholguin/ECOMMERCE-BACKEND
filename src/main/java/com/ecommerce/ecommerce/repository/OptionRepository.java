package com.ecommerce.ecommerce.repository;

import com.ecommerce.ecommerce.entity.Option;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OptionRepository extends JpaRepository<Option, Long>, JpaSpecificationExecutor<Option> {

    List<Option> findDistinctByOptionProductsProductSubCategoryId(Long subcategoryId);



}

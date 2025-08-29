package com.ecommerce.ecommerce.repository;

import com.ecommerce.ecommerce.entity.City;
import com.ecommerce.ecommerce.entity.Family;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface CityRepository extends JpaRepository<City, Long>, JpaSpecificationExecutor<City> {
}

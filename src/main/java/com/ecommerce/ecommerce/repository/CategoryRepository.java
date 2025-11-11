package com.ecommerce.ecommerce.repository;

import com.ecommerce.ecommerce.entity.Category;
import com.ecommerce.ecommerce.entity.Family;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long>, JpaSpecificationExecutor<Category> {

    @Query(value = "SELECT c.* FROM orders o JOIN JSON_TABLE(o.content,'$[*]' COLUMNS (productId INT PATH '$.productId', amount INT PATH '$.amount') " +
            ") AS jt ON TRUE JOIN products p ON p.id = jt.productId JOIN subcategories s ON s.id = p.subcategory_id JOIN categories c ON c.id = s.category_id " +
            "GROUP BY c.id ORDER BY SUM(jt.amount) DESC LIMIT 10", nativeQuery = true)
    List<Category> findTopCategoriesBySales();
}

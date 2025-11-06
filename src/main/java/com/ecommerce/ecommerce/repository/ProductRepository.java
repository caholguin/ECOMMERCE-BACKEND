package com.ecommerce.ecommerce.repository;
import com.ecommerce.ecommerce.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long>, JpaSpecificationExecutor<Product> {

    List<Product> findBySubCategoryIdAndStatus(Long subCategoryId,int status);

    @Query(value = """
    SELECT p.*
    FROM orders o
    JOIN JSON_TABLE(
        o.content,
        '$[*]' COLUMNS (
            productId INT PATH '$.productId',
            amount INT PATH '$.amount'
        )
    ) AS jt ON TRUE
    JOIN products p ON p.id = jt.productId
    GROUP BY p.id
    ORDER BY SUM(jt.amount) DESC
    LIMIT 10
    """, nativeQuery = true)
    List<Product> findTopProductsBySales();

    @Query("SELECT p FROM Product p WHERE p.discount > 0 ORDER BY p.discount DESC LIMIT 10")
    List<Product> productsWithDiscount();
}

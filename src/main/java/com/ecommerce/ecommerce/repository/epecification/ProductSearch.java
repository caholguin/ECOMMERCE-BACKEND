package com.ecommerce.ecommerce.repository.epecification;

import com.ecommerce.ecommerce.dto.request.ProductSearchDTO;
import com.ecommerce.ecommerce.entity.Product;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

public class ProductSearch implements Specification<Product> {
    private final ProductSearchDTO productSearchDTO;

    public ProductSearch(ProductSearchDTO productSearchDTO){
        this.productSearchDTO = productSearchDTO;
    }


    @Override
    public Predicate toPredicate(Root<Product> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder){
        List<Predicate> predicates = new ArrayList<>();

        if (StringUtils.hasText(this.productSearchDTO.getName())){
            Predicate nameTitle = criteriaBuilder.like(root.get("name"), "%" + this.productSearchDTO.getName() + "%");
            predicates.add(nameTitle);
        }

        if (StringUtils.hasText(this.productSearchDTO.getDetail())){
            Predicate detail = criteriaBuilder.like(root.get("detail"), "%" + this.productSearchDTO.getDetail() + "%");
            predicates.add(detail);
        }

        return criteriaBuilder.and(predicates.toArray(new Predicate[0]));


    }
}

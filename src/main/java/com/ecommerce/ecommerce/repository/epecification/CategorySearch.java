package com.ecommerce.ecommerce.repository.epecification;

import com.ecommerce.ecommerce.dto.request.CategorySearchDTO;
import com.ecommerce.ecommerce.entity.Category;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

public class CategorySearch implements Specification<Category> {
    
    private final CategorySearchDTO searchDTO;

    public CategorySearch(CategorySearchDTO searchDTO){
        this.searchDTO = searchDTO;
    }

    @Override
    public Predicate toPredicate(Root<Category> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder){
        List<Predicate> predicates = new ArrayList<>();

        if (StringUtils.hasText(this.searchDTO.getName())){
            Predicate nameTitle = criteriaBuilder.like(root.get("name"), "%" + this.searchDTO.getName() + "%");
            predicates.add(nameTitle);
        }

        return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
    }
}

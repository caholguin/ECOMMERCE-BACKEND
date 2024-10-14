package com.ecommerce.ecommerce.repository.epecification;

import com.ecommerce.ecommerce.dto.request.SubcategorySearchDTO;
import com.ecommerce.ecommerce.entity.SubCategory;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

public class SuCategorySearch implements Specification<SubCategory> {

    private final SubcategorySearchDTO searchDTO;

    public SuCategorySearch(SubcategorySearchDTO searchDTO){
        this.searchDTO = searchDTO;
    }

    @Override
    public Predicate toPredicate(Root<SubCategory> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder){
        List<Predicate> predicates = new ArrayList<>();

        if (StringUtils.hasText(this.searchDTO.getName())){
            Predicate nameTitle = criteriaBuilder.like(root.get("name"), "%" + this.searchDTO.getName() + "%");
            predicates.add(nameTitle);
        }

        return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
    }
}

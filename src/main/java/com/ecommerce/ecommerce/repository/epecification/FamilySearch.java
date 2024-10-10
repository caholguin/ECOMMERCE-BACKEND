package com.ecommerce.ecommerce.repository.epecification;

import com.ecommerce.ecommerce.dto.request.FamilySearchDTO;
import com.ecommerce.ecommerce.entity.Family;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

public class FamilySearch implements Specification<Family> {

    private final FamilySearchDTO searchDTO;

    public FamilySearch(FamilySearchDTO searchDTO){
        this.searchDTO = searchDTO;
    }

    @Override
    public Predicate toPredicate(Root<Family> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder){
        List<Predicate> predicates = new ArrayList<>();

        if (StringUtils.hasText(this.searchDTO.getName())){
            Predicate titleLike = criteriaBuilder.like(root.get("name"), "%" + this.searchDTO.getName() + "%");
            predicates.add(titleLike);
        }

        return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
    }


}

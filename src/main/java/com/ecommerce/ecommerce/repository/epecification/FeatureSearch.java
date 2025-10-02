package com.ecommerce.ecommerce.repository.epecification;

import com.ecommerce.ecommerce.dto.request.search.FeatureSearchDTO;
import com.ecommerce.ecommerce.entity.Feature;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

public class FeatureSearch implements Specification<Feature> {

    private final FeatureSearchDTO searchDTO;

    public FeatureSearch(FeatureSearchDTO searchDTO){
        this.searchDTO = searchDTO;
    }

    @Override
    public Predicate toPredicate(Root<Feature> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder){
        List<Predicate> predicates = new ArrayList<>();

        if (StringUtils.hasText(this.searchDTO.getName())){
            Predicate description = criteriaBuilder.like(root.get("name"), "%" + this.searchDTO.getName() + "%");
            predicates.add(description);
        }

        if (this.searchDTO.getOption() != null) {
            Predicate option = criteriaBuilder.equal(root.get("option").get("id"), this.searchDTO.getOption());
            predicates.add(option);
        }

        return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
    }
}

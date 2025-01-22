package com.ecommerce.ecommerce.repository.epecification;

import com.ecommerce.ecommerce.dto.request.OptionSearchDTO;
import com.ecommerce.ecommerce.entity.Option;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

public class OptionSearch implements Specification<Option> {

    private final OptionSearchDTO optionSearchDTO;

    public OptionSearch(OptionSearchDTO optionSearchDTO){
        this.optionSearchDTO = optionSearchDTO;
    }

    @Override
    public Predicate toPredicate(Root<Option> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder){
        List<Predicate> predicates = new ArrayList<>();

        if (StringUtils.hasText(this.optionSearchDTO.getName())){
            Predicate nameTitle = criteriaBuilder.like(root.get("name"), "%" + this.optionSearchDTO.getName() + "%");
            predicates.add(nameTitle);
        }

        return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
    }
}

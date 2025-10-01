package com.ecommerce.ecommerce.repository.epecification;

import com.ecommerce.ecommerce.dto.request.search.OrderSearchDTO;
import com.ecommerce.ecommerce.entity.Order;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;

public class OrderSearch implements Specification<Order> {

    private final OrderSearchDTO searchDTO;

    public OrderSearch(OrderSearchDTO searchDTO){
        this.searchDTO = searchDTO;
    }

    @Override
    public Predicate toPredicate(Root<Order> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder){
        List<Predicate> predicates = new ArrayList<>();

        if (this.searchDTO.getId() != null) {
            Predicate id = criteriaBuilder.equal(root.get("id"), this.searchDTO.getId());
            predicates.add(id);
        }

        if (this.searchDTO.getStatus() != null) {
            Predicate status = criteriaBuilder.equal(root.get("status"), this.searchDTO.getStatus());
            predicates.add(status);
        }

        return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
    }
}

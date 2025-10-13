package com.ecommerce.ecommerce.repository;

import com.ecommerce.ecommerce.entity.Permission;
import com.ecommerce.ecommerce.entity.Role;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface PermissionRepository extends JpaRepository<Permission, Long> {

    @Modifying
    @Transactional
    @Query("DELETE FROM Permission gp WHERE gp.role = :role")
    void deleteByRole(Role role);
}

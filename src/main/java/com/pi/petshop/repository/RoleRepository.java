package com.pi.petshop.repository;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pi.petshop.entity.Role;

/**
 * RoleRepository
 * @author Lizqui
 */
public interface RoleRepository extends JpaRepository<Role, UUID> {
    Optional<Role> findByAuthority(String authority);
}

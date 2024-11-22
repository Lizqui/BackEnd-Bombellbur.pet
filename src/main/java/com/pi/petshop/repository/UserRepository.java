package com.pi.petshop.repository;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pi.petshop.entity.ApplicationUser;

/**
 * UserRepository
 * @author Lizqui
 */
public interface UserRepository extends JpaRepository<ApplicationUser, UUID> {
    Optional<ApplicationUser> findByUsername(String username);
}

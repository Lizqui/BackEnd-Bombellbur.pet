package com.pi.petshop.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pi.petshop.entity.UserProfile;

/**
 * UserProfileService
 */
public interface UserProfileRepository
    extends JpaRepository<UserProfile, UUID> {

}

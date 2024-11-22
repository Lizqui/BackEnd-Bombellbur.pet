package com.pi.petshop.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import com.pi.petshop.entity.Opinion;

public interface OpinionRepository extends JpaRepository<Opinion, UUID>{
    @Query("SELECT o FROM Opinion o WHERE o.applicationUser.id = :userId")
    List<Opinion> findByIdUser(@Param("userId") UUID idUser);       
}

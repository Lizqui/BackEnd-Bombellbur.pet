package com.pi.petshop.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pi.petshop.entity.Pet;

public interface PetRespository extends JpaRepository<Pet, UUID>{

    
}

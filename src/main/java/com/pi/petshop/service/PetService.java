package com.pi.petshop.service;

import java.util.List;
import java.util.UUID;

import org.springframework.http.ResponseEntity;

import com.pi.petshop.entity.Pet;
import com.pi.petshop.exceptions.PetNotFoundException;
import com.pi.petshop.payload.PetDTO;

public interface PetService {

    ResponseEntity<Pet> createPet(PetDTO pet);
    ResponseEntity<List<Pet>> getAllPets();
    ResponseEntity<Pet> getPetById(UUID petId) throws PetNotFoundException;
    ResponseEntity<Pet> updatePet(UUID petId, PetDTO updatedPet)
            throws PetNotFoundException;
    ResponseEntity<?> deletePet(UUID petId)
            throws PetNotFoundException;

}

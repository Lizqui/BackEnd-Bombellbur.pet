package com.pi.petshop.controller;

import java.util.List;
import java.util.UUID;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pi.petshop.entity.Pet;
import com.pi.petshop.payload.PetDTO;
import com.pi.petshop.service.PetService;

@RestController
@RequestMapping("/api/v1/pets")
@CrossOrigin("*")
public class PetController {

    private final PetService petService;

    public PetController(PetService petService) {
        this.petService = petService;
    }

    @PostMapping
    public ResponseEntity<Pet> createPet(@ModelAttribute PetDTO pet) {
        return petService.createPet(pet);
    }

    @GetMapping
    public ResponseEntity<List<Pet>> getAllPets() {
        return petService.getAllPets();
    }

    @GetMapping("/{petId}")
    public ResponseEntity<Pet> getPetById(@PathVariable("petId") UUID petId) {
        return petService.getPetById(petId);
    }

    @PutMapping("/{petId}")
    public ResponseEntity<Pet> updatePet(@PathVariable("petId") UUID petId, @ModelAttribute PetDTO updatedPet) {
        return petService.updatePet(petId, updatedPet);
    }

    @DeleteMapping("/{petId}")
    public ResponseEntity<?> deletePet(@PathVariable("petId") UUID petId) {
        return petService.deletePet(petId);
    }

}

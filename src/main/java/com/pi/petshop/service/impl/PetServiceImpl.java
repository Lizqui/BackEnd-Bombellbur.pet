package com.pi.petshop.service.impl;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.pi.petshop.entity.Pet;
import com.pi.petshop.exceptions.PetNotFoundException;
import com.pi.petshop.payload.PetDTO;
import com.pi.petshop.repository.PetRespository;
import com.pi.petshop.service.PetService;

@Service
public class PetServiceImpl implements PetService {
    private PetRespository petRespository;
    private String staticResourcesPath;
    private String staticResourcesBasePath;

    public PetServiceImpl(
            PetRespository petRespository,
            @Value("${app.static-resources.path}") String staticResourcesPath,
            @Value("${app.static-resources.basePath}") String staticResourcesBasePath) {
        this.petRespository = petRespository;
        this.staticResourcesPath = staticResourcesPath;
        this.staticResourcesBasePath = staticResourcesBasePath;
    }

    @Override
    public ResponseEntity<Pet> createPet(PetDTO pet) {
        final Pet newPet = mapBasePet(pet);
        newPet.setImagePath(saveImage(pet.getImage()));
        return ResponseEntity.ok(petRespository.save(newPet));
    }

    @Override
    public ResponseEntity<List<Pet>> getAllPets() {
        return ResponseEntity.ok(petRespository.findAll());
    }

    @Override
    public ResponseEntity<Pet> getPetById(UUID petId) throws PetNotFoundException {
        Pet targetPet = petRespository.findById(petId)
            .orElseThrow(() -> new PetNotFoundException(
                        String.format("Pet identified with '%s' not found",
                            petId.toString())));
        return ResponseEntity.ok(targetPet);
    }

    @Override
    public ResponseEntity<Pet> updatePet(UUID petId, PetDTO updatedPet) throws PetNotFoundException {
        Pet targetPet = petRespository.findById(petId)
            .orElseThrow(() -> new PetNotFoundException(
                        String.format("Pet identified with '%s' not found",
                            petId.toString())));
        targetPet.setAge(updatedPet.getAge());
        targetPet.setName(updatedPet.getName());
        targetPet.setSpecies(updatedPet.getSpecies());
        targetPet.setDescription(updatedPet.getDescription());
        targetPet.setImagePath(
                updateImage(updatedPet.getImage(), targetPet.getImagePath())
                );
        targetPet.setUrl(updatedPet.getUrl());
        return ResponseEntity.ok(petRespository.save(targetPet));
    }

    @Override
    public ResponseEntity<?> deletePet(UUID petId) throws PetNotFoundException {
        Pet targetPet = petRespository.findById(petId)
            .orElseThrow(() -> new PetNotFoundException(
                        String.format("Pet identified with '%s' not found",
                            petId.toString())));
        deleteImage(targetPet.getImagePath());
        petRespository.delete(targetPet);
        return ResponseEntity.noContent().build();
    }

    private Pet mapBasePet(PetDTO petDTO) {
        return Pet.builder()
            .name(petDTO.getName())
            .age(petDTO.getAge())
            .species(petDTO.getSpecies())
            .description(petDTO.getDescription())
            .url(petDTO.getUrl())
            .build();
    }

    public String saveImage(MultipartFile image) {
        String originalFilename = image.getOriginalFilename();
        String extension = originalFilename.substring(originalFilename.lastIndexOf("."));
        String filename = UUID.randomUUID().toString() + extension;
        String imagePath = staticResourcesPath + "/" + filename;
        try {
            image.transferTo(new File(imagePath));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return imagePath.replace(staticResourcesBasePath, "/images");
    }

    private void deleteImage(String currentPath) {
        final String imagePath = currentPath
            .replace("/images", staticResourcesBasePath);
        File image = new File(imagePath);
        if (image.exists()) {
            image.delete();
        }
    }

    private String updateImage(MultipartFile newImage, String oldImagePath) {
        deleteImage(oldImagePath);
        return saveImage(newImage);
    }
}

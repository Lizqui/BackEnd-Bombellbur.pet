package com.pi.petshop.payload;

import org.springframework.web.multipart.MultipartFile;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * PetDTO
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
public class PetDTO {
    private String name;
    private String species;
    private int age;
    private String description;
    private MultipartFile image;
    private String url;
}

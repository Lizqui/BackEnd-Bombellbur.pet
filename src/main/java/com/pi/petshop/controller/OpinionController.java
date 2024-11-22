package com.pi.petshop.controller;

import java.util.List;
import java.util.UUID;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pi.petshop.payload.OpinionDTO;
import com.pi.petshop.service.OpinionService;
import com.pi.petshop.entity.Opinion;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/api/v1/opinion")
@AllArgsConstructor
@CrossOrigin("*")
public class OpinionController {
    private final OpinionService opinionService;

    @PostMapping("/")
    public ResponseEntity<?> createOpinion (@RequestBody OpinionDTO opinion) {
        return ResponseEntity.ok(opinionService.createOpinion(opinion));
    }

    @GetMapping("/")
    public ResponseEntity<List<Opinion>> getAllOpinion(){
        return opinionService.getAllOpinion();
    }

    
    @GetMapping("/{userId}")
    public ResponseEntity<?> getPetById(@PathVariable("userId") UUID petId) {
        return opinionService.getOpionById(petId);
    }
}

package com.pi.petshop.service;

import java.util.List;
import java.util.UUID;

import org.springframework.http.ResponseEntity;

import com.pi.petshop.entity.Opinion;
import com.pi.petshop.payload.OpinionDTO;

public interface OpinionService {
    public ResponseEntity<?> createOpinion (OpinionDTO opinion);
    public ResponseEntity<List<Opinion>> getAllOpinion();
    public ResponseEntity<List<Opinion>> getOpionById(UUID ID);
}

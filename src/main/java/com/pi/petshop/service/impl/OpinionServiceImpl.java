package com.pi.petshop.service.impl;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.pi.petshop.entity.ApplicationUser;
import com.pi.petshop.entity.Opinion;
import com.pi.petshop.payload.OpinionDTO;
import com.pi.petshop.repository.OpinionRepository;
import com.pi.petshop.repository.UserRepository;
import com.pi.petshop.service.OpinionService;

import lombok.AllArgsConstructor;

/**
 * OpinionServiceImpl
 */
@Service
@AllArgsConstructor
public class OpinionServiceImpl implements OpinionService {
    private OpinionRepository opinionRepository;
    private UserRepository userRepository;

    @Override    
    public ResponseEntity<?> createOpinion(OpinionDTO opinion) {
        final Opinion newOpinion =  mapBaseOpinion(opinion);
        return ResponseEntity.ok(opinionRepository.save(newOpinion));
    }

    public Opinion mapBaseOpinion (OpinionDTO opinion) {
        LocalDateTime fechaHoraActual = LocalDateTime.now();
        ApplicationUser user = userRepository.findById(opinion.getIdUser())
                    .orElseThrow(() -> new UsernameNotFoundException("No found user"));
        return Opinion.builder()
                      .applicationUser(user)
                      .textOpinion(opinion.getText())
                      .date(fechaHoraActual)
                      .build();
    }

    @Override
    public ResponseEntity<List<Opinion>> getAllOpinion(){
        return ResponseEntity.ok(opinionRepository.findAll());
    }

    @Override
    public ResponseEntity<List<Opinion>> getOpionById(UUID idUser) {                        
        return ResponseEntity.ok(opinionRepository.findByIdUser(idUser));
    }
    
}
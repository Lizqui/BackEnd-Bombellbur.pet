package com.pi.petshop.entity;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.UUID;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "opinion")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class Opinion {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "opinion_id")
    private UUID idOpinion;
    @ManyToOne(cascade = CascadeType.ALL) 
    @JoinColumn(name = "user_id", referencedColumnName = "user_id")
    private ApplicationUser applicationUser;    
    private String textOpinion;    
    private LocalDateTime date;
}

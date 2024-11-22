package com.pi.petshop.entity;

import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * UserProfile
 * @author Lizqui
 */
@Entity
@Table(name = "user_profile")
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder
public class UserProfile {

    @Id
    @Column(name = "user_profile_id")
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID userProfileId;

    @JsonProperty("user_identification")
    @Column(nullable = false, name = "user_identification")
    private String userIdentification;

    @JsonProperty("first_name")
    @Column(nullable = false, name = "first_name")
    private String firstName;

    @JsonProperty("last_name")
    @Column(nullable = false, name = "last_name")
    private String lastName;

    @JsonProperty("telephone_number")
    @Column(name = "telephone_number", nullable = false)
    private String telephoneNumber;

    @Column(name = "address", nullable = false)
    private String address;

    @Column(name = "email")
    private String email;

}

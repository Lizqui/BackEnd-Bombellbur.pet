package com.pi.petshop.service;

import org.springframework.http.ResponseEntity;

import com.pi.petshop.entity.ApplicationUser;
import com.pi.petshop.payload.UserLoginDTO;
import com.pi.petshop.payload.UserLoginResponseDTO;
import com.pi.petshop.payload.UserRegistrationDTO;

/**
 * AuthenticationService
 * @author Lizqui
 */
public interface AuthenticationService {
    ResponseEntity<ApplicationUser> registerUser(UserRegistrationDTO user);
    ResponseEntity<UserLoginResponseDTO> loginUser(UserLoginDTO loginInfo);
}

package com.pi.petshop.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pi.petshop.entity.ApplicationUser;
import com.pi.petshop.payload.UserLoginDTO;
import com.pi.petshop.payload.UserLoginResponseDTO;
import com.pi.petshop.payload.UserRegistrationDTO;
import com.pi.petshop.service.AuthenticationService;

import lombok.AllArgsConstructor;

/**
 * AuthenticationController
 * @author Lizqui
 */
@RestController
@RequestMapping("/api/v1/auth")
@AllArgsConstructor
@CrossOrigin("*")
public class AuthenticationController {

    private AuthenticationService authenticationService;

    @PostMapping("/register")
    public ResponseEntity<ApplicationUser> registerUser(
            @RequestBody UserRegistrationDTO registrationInfo) {
        return authenticationService.registerUser(registrationInfo);
    }

    @PostMapping("/login")
    public ResponseEntity<UserLoginResponseDTO> loginUser(@RequestBody UserLoginDTO loginInfo){
        return authenticationService.loginUser(loginInfo);
    }    
}

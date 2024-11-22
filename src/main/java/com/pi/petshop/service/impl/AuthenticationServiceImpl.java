package com.pi.petshop.service.impl;

import java.util.HashSet;
import java.util.Set;

import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pi.petshop.constants.Authorities;
import com.pi.petshop.entity.ApplicationUser;
import com.pi.petshop.entity.Role;
import com.pi.petshop.entity.UserProfile;
import com.pi.petshop.payload.UserLoginDTO;
import com.pi.petshop.payload.UserLoginResponseDTO;
import com.pi.petshop.payload.UserRegistrationDTO;
import com.pi.petshop.repository.UserRepository;
import com.pi.petshop.service.AuthenticationService;
import com.pi.petshop.service.JwtTokenService;
import com.pi.petshop.service.RoleService;
import com.pi.petshop.service.UserProfileService;

import lombok.AllArgsConstructor;


/**
 * AuthenticationService
 * @author Lizqui
 */
@Service
@Transactional
@AllArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {

    private PasswordEncoder encoder;
    private RoleService roleService;
    private UserProfileService userProfileService;
    private UserRepository userRepository;
    private AuthenticationManager authenticationManager;
    private JwtTokenService tokenService;


    @Override
    public ResponseEntity<ApplicationUser> registerUser(UserRegistrationDTO registrationInfo) {
        final String userAuthName = Authorities.USER.getAthorityName();
        final Role USER_AUTH = roleService
            .retriveOrCreateAuthority(userAuthName);

        final Set<Role> userAuthorities = new HashSet<>();
        userAuthorities.add(USER_AUTH);

        var registrationProfile = registrationInfo.getProfile();
        UserProfile newUserProfile = UserProfile.builder()
            .userIdentification(registrationProfile.getUserIdentification())
            .firstName(registrationProfile.getFirstName())
            .lastName(registrationProfile.getLastName())
            .telephoneNumber(registrationProfile.getTelephoneNumber())
            .address(registrationProfile.getAddress())
            .email(registrationProfile.getEmail())
            .build();
        userProfileService.createUserProfile(newUserProfile);

        ApplicationUser newUser = ApplicationUser.builder()
            .password(encoder.encode(registrationInfo.getPassword()))
            .username(registrationInfo.getUserName())
            .authorities(userAuthorities)
            .userProfile(newUserProfile)
            .isActive(true)
            .build();

        final ApplicationUser createdUser = userRepository.save(newUser);
        return ResponseEntity.ok(createdUser);
    }

    @Override
    public ResponseEntity<UserLoginResponseDTO> loginUser(UserLoginDTO loginInfo){
        final String userName = loginInfo.getUserName();
        final String userPassword = loginInfo.getPassword();

        try{
            var userCredentials = new UsernamePasswordAuthenticationToken(
                    userName,
                    userPassword);
            Authentication auth = authenticationManager.authenticate(userCredentials);
            String token = tokenService.generateJwtToken(auth);
            return ResponseEntity.ok(UserLoginResponseDTO.builder()
                .user(userRepository.findByUsername(userName).get())
                .token(token)
                .build());
        } catch(AuthenticationException e){
            throw e;
        }
    }
}

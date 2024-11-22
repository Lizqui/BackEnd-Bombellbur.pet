package com.pi.petshop.service.impl;

import org.springframework.stereotype.Service;

import com.pi.petshop.entity.UserProfile;
import com.pi.petshop.repository.UserProfileRepository;
import com.pi.petshop.service.UserProfileService;

import lombok.AllArgsConstructor;

/**
 * ProfileServiceIpml
 * @author Lizqui
 */
@Service
@AllArgsConstructor
public class UserProfileServiceIpml implements UserProfileService {

    private UserProfileRepository userProfileRepository;

    @Override
    public UserProfile createUserProfile(UserProfile userProfile) {
        return userProfileRepository.save(userProfile);
    }


}

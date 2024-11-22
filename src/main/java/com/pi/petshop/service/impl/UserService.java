package com.pi.petshop.service.impl;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.pi.petshop.entity.ApplicationUser;
import com.pi.petshop.repository.UserRepository;

import lombok.AllArgsConstructor;

/**
 * UserService
 * @author Lizqui
 */
@Service
@AllArgsConstructor
public class UserService implements UserDetailsService {

    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username)
        throws UsernameNotFoundException {
        ApplicationUser loadedUser =
            userRepository.findByUsername(username)
            .orElseThrow(() -> new UsernameNotFoundException(
                        String.format("ApplicationUser::%s not found", username)
                        )
                    );
        return loadedUser;
    }

}

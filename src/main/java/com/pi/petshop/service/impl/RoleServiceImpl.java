package com.pi.petshop.service.impl;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.pi.petshop.entity.Role;
import com.pi.petshop.exceptions.AuthorityNotFoundException;
import com.pi.petshop.repository.RoleRepository;
import com.pi.petshop.service.RoleService;

import lombok.AllArgsConstructor;

/**
 * RoleService
 * @author Lizqui
 */
@Service
@AllArgsConstructor
public class RoleServiceImpl implements RoleService {

    private RoleRepository roleRepository;

    @Override
    public Role findByAuthority(String authority) {
        return roleRepository.findByAuthority(authority)
            .orElseThrow(() -> new AuthorityNotFoundException(
                        String.format("Authority::%s not found", authority)
                        ));
    }

    @Override
    public Role retriveOrCreateAuthority(String authority) {
        Optional<Role> targetAuth = roleRepository.findByAuthority(authority);
        if(!targetAuth.isPresent()) {
            Role newRole = Role
                .builder()
                .authority(authority)
                .build();
            return roleRepository.save(newRole);
        }
        return targetAuth.get();
    }

}

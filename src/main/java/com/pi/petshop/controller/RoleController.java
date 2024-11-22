package com.pi.petshop.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pi.petshop.entity.Role;
import com.pi.petshop.payload.RoleDTO;
import com.pi.petshop.service.RoleService;

@RestController
@RequestMapping("/api/v1/roles")
@CrossOrigin("*")
public class RoleController {

    private RoleService roleService;

    public RoleController(RoleService roleService) {
        this.roleService = roleService;
    }

    @PostMapping
    public ResponseEntity<Role> createAuthority(@RequestBody RoleDTO roleDto) {
        final String targetAuthority = roleDto.getRoleName();
        return ResponseEntity.ok(roleService.retriveOrCreateAuthority(targetAuthority));
    }
    
}

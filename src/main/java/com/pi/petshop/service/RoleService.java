package com.pi.petshop.service;

import com.pi.petshop.entity.Role;
import com.pi.petshop.exceptions.AuthorityNotFoundException;

/**
 * RoleService
 * @author Lizqui
 */
public interface RoleService {
    Role findByAuthority(String authority) throws AuthorityNotFoundException;
    Role retriveOrCreateAuthority(String authority);
}

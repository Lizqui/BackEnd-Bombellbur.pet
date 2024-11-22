package com.pi.petshop.constants;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Roles
 */
@AllArgsConstructor
@Getter
public enum Authorities {
    USER("USER"),
    ADMIN("ADMIN");

    private String athorityName;
}

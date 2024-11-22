package com.pi.petshop.service;

import org.springframework.security.core.Authentication;

public interface JwtTokenService {

    String generateJwtToken(Authentication auth);
}

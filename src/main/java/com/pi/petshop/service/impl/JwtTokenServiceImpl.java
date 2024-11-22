package com.pi.petshop.service.impl;

import java.time.Instant;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.stereotype.Service;

import com.pi.petshop.service.JwtTokenService;

@Service
public class JwtTokenServiceImpl implements JwtTokenService {

    private JwtEncoder jwtEncoder;
    private JwtDecoder jwtDecoder;
    private long expirationSeconds;

    public JwtTokenServiceImpl(
            @Value("${jwt.token.expiration-time}") long expirationSeconds,
            JwtEncoder jwtEncoder, JwtDecoder jwtDecoder) {
        this.expirationSeconds = expirationSeconds;
        this.jwtEncoder = jwtEncoder;
        this.jwtDecoder = jwtDecoder;
    }

    @Override
    public String generateJwtToken(Authentication auth) {
        Instant now = Instant.now();
        Instant expiration = now.plusSeconds(expirationSeconds);

        String scope = auth.getAuthorities().stream()
            .map(GrantedAuthority::getAuthority)
            .collect(Collectors.joining(" "));

        JwtClaimsSet claims = JwtClaimsSet.builder()
            .issuer("self")
            .issuedAt(now)
            .expiresAt(expiration)
            .subject(auth.getName())
            .claim("roles", scope)
            .build();

        return jwtEncoder.encode(JwtEncoderParameters.from(claims)).getTokenValue();
    }
}

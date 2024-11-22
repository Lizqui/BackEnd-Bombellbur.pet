package com.pi.petshop.payload;

import com.pi.petshop.entity.ApplicationUser;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserLoginResponseDTO {
    private ApplicationUser user;
    private String token;
}

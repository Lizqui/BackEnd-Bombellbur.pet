package com.pi.petshop.payload;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserRegistrationDTO {

    @JsonProperty("user_name")
    private String userName;
    private String password;
    private Profile profile;

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    public static class Profile {
        @JsonProperty("user_identification")
        private String userIdentification;
        @JsonProperty("first_name")
        private String firstName;
        @JsonProperty("last_name")
        private String lastName;
        @JsonProperty("telephone_number")
        private String telephoneNumber;
        private String address;
        private String email;
    }
}

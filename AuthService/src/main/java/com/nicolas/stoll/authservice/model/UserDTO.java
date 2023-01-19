package com.nicolas.stoll.authservice.model;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UserDTO {

    private String email;
    private String password;
    private String username;
}

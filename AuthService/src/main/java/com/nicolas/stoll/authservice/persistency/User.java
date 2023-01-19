package com.nicolas.stoll.authservice.persistency;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {

    @Id private String email;
    private String username;
    private String passwordHash;
    private String salt;

}

package com.nicolas.stoll.authservice.services;

import com.google.common.hash.Hashing;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;

@Component
public class PasswordService {

    public String generateHash(String password, String salt)  {
        return Hashing.sha384()
                .hashString(password + salt, StandardCharsets.UTF_8)
                .toString();
    }

    public boolean isValidPassword(String passwordHash, String password, String salt) {

        return passwordHash.equals(generateHash(password, salt));
    }
}

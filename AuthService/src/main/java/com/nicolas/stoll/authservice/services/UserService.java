package com.nicolas.stoll.authservice.services;

import com.nicolas.stoll.authservice.persistency.IDao;
import com.nicolas.stoll.authservice.persistency.User;
import com.nicolas.stoll.authservice.model.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.UUID;

@Component
public class UserService {

    @Autowired
    @Qualifier("inmemory")
    private IDao<User, String> storage;

    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    @Autowired PasswordService passwordService;

    public boolean signUp(UserDTO userModel) {

        final String salt = UUID.randomUUID().toString();
        final String passwordHash = this.passwordService.generateHash(userModel.getPassword(), salt);

        User user = new User(userModel.getEmail(), userModel.getUsername(), passwordHash, salt);

        return this.storage.save(user);
    }

    public Optional<String> login(UserDTO tempUser) {

        Optional<User> userOptional = this.storage.get(tempUser.getEmail());

        if(userOptional.isEmpty()) {
            return Optional.empty();

        }

        User user = userOptional.get();


        final boolean validPassword = this.passwordService
                .isValidPassword(user.getPasswordHash(), tempUser.getPassword(), user.getSalt());

        if(validPassword) {
            String token = jwtTokenProvider.createToken(user.getUsername());

            return Optional.of(token);
        }

        return Optional.empty();
    }

    public boolean validateToken(String bearerToken) {
        String token;
        try {
            token = bearerToken.split(" ")[1];
        } catch (ArrayIndexOutOfBoundsException e) {
            return false;
        }

        return jwtTokenProvider.validateToken(token);
    }
}

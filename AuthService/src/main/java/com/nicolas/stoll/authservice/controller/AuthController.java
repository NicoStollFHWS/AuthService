package com.nicolas.stoll.authservice.controller;


import com.nicolas.stoll.authservice.model.UserDTO;
import com.nicolas.stoll.authservice.services.JwtTokenProvider;
import com.nicolas.stoll.authservice.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api")
public class AuthController {

    @Autowired
    private UserService userService;

    @Autowired
    private JwtTokenProvider jwtTokenProvider;


    @PostMapping("/signUp")
    public ResponseEntity<String> signUp(@RequestBody UserDTO tempUser) {


        if(userService.signUp(tempUser)) {
            return new ResponseEntity<>("User successfully registered", HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>("User could not be created", HttpStatus.UNPROCESSABLE_ENTITY);
        }
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody UserDTO tempUser) {
        Optional<String> optionalToken = this.userService.login(tempUser);

        return optionalToken
                .map(s -> new ResponseEntity<>(s, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>("Login nicht möglich", HttpStatus.UNPROCESSABLE_ENTITY));

    }

    @GetMapping("/verifyToken")
    public ResponseEntity<String> verifyToken(@RequestHeader("Authorization") String bearerToken) {


        boolean isValid = this.userService.validateToken(bearerToken);

        if(isValid) {
            return new ResponseEntity<>("Token is valid", HttpStatus.OK);
        }

        return new ResponseEntity<>("Token invalid", HttpStatus.FORBIDDEN);
    }

}

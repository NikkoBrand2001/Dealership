package com.security.controller;

import com.security.model.dtos.AuthRequest;
import com.security.model.dtos.UserRequest;
import com.security.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final UserService userService;


    @PostMapping("/sign-up")
    @ResponseStatus(HttpStatus.CREATED)
    public String register(@RequestBody UserRequest userRequest) {
        return userService.saveUser(userRequest);
    }

    @GetMapping("/sign-in")
    public ResponseEntity<String> loadByUserName(@RequestBody AuthRequest  authRequest) {
        return ResponseEntity.ok(userService.loginUser(authRequest));
    }

    @GetMapping("/validate")
    public ResponseEntity<Boolean> validateToken(@RequestParam("token") String token) {
        return ResponseEntity.ok( userService.validateToken(token));
    }
}

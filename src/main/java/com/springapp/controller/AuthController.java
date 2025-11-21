package com.springapp.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;

import com.springapp.jwtsecurity.JwtUtil;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthenticationManager authManager;
    private final JwtUtil jwtUtil;
    private final UserDetailsService uds;

    public AuthController(AuthenticationManager authManager, JwtUtil jwtUtil, UserDetailsService uds) {
        this.authManager = authManager;
        this.jwtUtil = jwtUtil;
        this.uds = uds;
    }

    public static class LoginRequest {
        public String username;
        public String password;
        public LoginRequest() {}
        public LoginRequest(String username, String password) { this.username = username; this.password = password; }
        public String getUsername() { return username; }
        public String getPassword() { return password; }
    }

    public static class LoginResponse {
        public String token;
        public LoginResponse() {}
        public LoginResponse(String token) { this.token = token; }
        public String getToken() { return token; }
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest req) {
        try {
            authManager.authenticate(new UsernamePasswordAuthenticationToken(req.getUsername(), req.getPassword()));
        } catch (BadCredentialsException ex) {
            return ResponseEntity.status(401).body("Invalid username/password");
        }

        UserDetails ud = uds.loadUserByUsername(req.getUsername());
        String token = jwtUtil.generateToken(ud.getUsername());
        return ResponseEntity.ok(new LoginResponse(token));
    }
}

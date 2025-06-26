package com.exemple.exotoken.controller;

import com.exemple.exotoken.dto.CreateUserRequest;
import com.exemple.exotoken.model.User;
import com.exemple.exotoken.repository.UserRepository;
import com.exemple.exotoken.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.token.TokenService;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/users")
@AllArgsConstructor
public class UserController {

    private final UserService userService;
    private final UserRepository userRepository;

    /**Créer nouvel utilisateur*/
    @PostMapping
    public ResponseEntity<?> createUser(@RequestBody CreateUserRequest request) {
        try {
            User createdUser = userService.createUser(request.username(), request.password(), request.role());
            return ResponseEntity.ok(createdUser);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }


}

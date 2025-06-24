package com.exemple.exotoken.controller;

import com.exemple.exotoken.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.security.core.token.TokenService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/user")
@AllArgsConstructor
public class UserController {

    private final UserService userService;
    //private final TokenService tokenService;

}

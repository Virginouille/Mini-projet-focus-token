package com.exemple.exotoken.dto;

import com.exemple.exotoken.model.RoleEnum;

public record CreateUserRequest(String username, String password, RoleEnum role) {
}
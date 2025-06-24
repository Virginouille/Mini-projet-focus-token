package com.exemple.exotoken.model;

public enum RoleEnum {
    ROLE_ADMIN,
    ROLE_USER;

    public String getAuthority() {
        return name();
    }
}

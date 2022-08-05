package com.example.shop.models;

import lombok.Getter;
import lombok.Setter;

public enum Permission {
    DEVELOPERS_READ("developers:read"),
    DEVELOPERS_WRITE("developers:write");

    @Getter
    private final String permission;

    Permission(String permission) {
        this.permission = permission;
    }
}

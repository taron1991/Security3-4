package org.example.security3.models;

import java.util.Set;

public enum Role {
    ADMIN(Set.of(Permission.READ,Permission.WRITE)),
    USER(Set.of(Permission.READ));

    private final Set<Permission> permissionSet;

    Role(Set<Permission> permissionSet) {
        this.permissionSet = permissionSet;
    }

    public Set<Permission> getPermissionSet() {
        return permissionSet;
    }
}

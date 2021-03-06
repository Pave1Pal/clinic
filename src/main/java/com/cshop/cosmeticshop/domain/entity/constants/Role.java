package com.cshop.cosmeticshop.domain.entity.constants;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;

public enum Role {
    CUSTOMER(Set.of(Permission.READ)),
    ADMIN(Set.of(Permission.READ, Permission.WRITE)),
    DOCTOR(Set.of(Permission.WRITE, Permission.READ));

    private final Set<Permission> permissions;

    Role(Set<Permission> permissions) {
        this.permissions = permissions;
    }

    public Set<Permission> getPermissions() {
        return permissions;
    }

    public Collection<? extends GrantedAuthority> getAuthorities() {
        var authorities = getPermissions().stream()
                .map(permission -> new SimpleGrantedAuthority(permission.getPermission()))
                .collect(Collectors.toSet());
        authorities.add(new SimpleGrantedAuthority(this.getRole()));
        return authorities;
    }

    public String getRole() {
        return "ROLE_" + this.name();
    }
}

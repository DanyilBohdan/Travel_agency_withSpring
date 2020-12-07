package org.bohdan.model;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import java.util.Set;

/**
 * Role entity.
 *
 * @author Bohdan Daniel
 *
 */

public enum Role {
    USER, MANAGER, ADMIN;

    public static Role getRole(User user) {
        int roleId = user.getRoleId();
        return Role.values()[roleId-1];
    }

    public static Integer getId(String role) {
        return Role.valueOf(role.toUpperCase()).ordinal() + 1;
    }

    public String getName() {
        return name();
    }

    public static SimpleGrantedAuthority getAuthorities(User user){
        return new SimpleGrantedAuthority("ROLE_" + Role.getRole(user).getName().toUpperCase());
    }
}

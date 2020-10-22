package org.bohdan.db.entity;

/**
 * Role entity.
 *
 * @author Bohdan Daniel
 *
 */
public enum Role {
    USER, MANAGER, ADMIN;

    public static Role getRole(User user) {
        int roleId = user.getRole_id();
        return Role.values()[roleId-1];
    }

    public static Integer getId(String role) {
        return Role.valueOf(role.toUpperCase()).ordinal() + 1;
    }

    public String getName() {
        return name().toLowerCase();
    }
}

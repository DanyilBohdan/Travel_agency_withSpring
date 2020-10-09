package org.bohdan.db.entity;

public enum Role {
    USER, MANAGER, ADMIN;

    public static Role getRole(User user) {
        int roleId = user.getRole_id();
        return Role.values()[roleId-1];
    }

    public String getName() {
        return name().toLowerCase();
    }
}

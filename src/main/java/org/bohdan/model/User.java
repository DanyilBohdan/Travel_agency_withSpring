package org.bohdan.model;

import org.springframework.stereotype.Component;

/**
 * User entity.
 *
 * @author Bohdan Daniel
 *
 */

public class User {

    private Integer id;

    private String username;

    private String password;

    private String login;

    private String phoneNumber;

    private boolean status;

    private int roleId;

    public static User createUser(String username, String password, String login,
                                  String phoneNumber, boolean status, int roleId) {
        User user = new User();
        user.setUsername(username);
        user.setPassword(password);
        user.setLogin(login);
        user.setPhoneNumber(phoneNumber);
        user.setStatus(status);
        user.setRoleId(roleId);
        return user;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public boolean getStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public int getRoleId() {
        return roleId;
    }

    public void setRoleId(int roleId) {
        this.roleId = roleId;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", email='" + login + '\'' +
                ", status='" + status + '\'' +
                ", role_id=" + roleId +
                '}';
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}

package org.bohdan.model.general;

public class UserRole {

    private Integer id;

    private String username;

    private String password;

    private String login;

    private String phoneNumber;

    private boolean status;

    private String role;

    public static UserRole create(Integer id, String username, String password, String login, String phoneNumber, boolean status, String role) {
        UserRole userRole = new UserRole();
        userRole.setId(id);
        userRole.setUsername(username);
        userRole.setPassword(password);
        userRole.setLogin(login);
        userRole.setPhoneNumber(phoneNumber);
        userRole.setStatus(status);
        userRole.setRole(role);
        return userRole;
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

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}

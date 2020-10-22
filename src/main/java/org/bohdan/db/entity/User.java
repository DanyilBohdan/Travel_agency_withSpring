package org.bohdan.db.entity;

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

    private String phone_number;

    private boolean status;

    private int role_id;

    public static User createUser(String username, String password, String login,
                                  String phone_number, boolean status, int role_id) {
        User user = new User();
        user.setUsername(username);
        user.setPassword(password);
        user.setLogin(login);
        user.setPhone_number(phone_number);
        user.setStatus(status);
        user.setRole_id(role_id);
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

    public int getRole_id() {
        return role_id;
    }

    public void setRole_id(int role_id) {
        this.role_id = role_id;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", email='" + login + '\'' +
                ", status='" + status + '\'' +
                ", role_id=" + role_id +
                '}';
    }

    public String getPhone_number() {
        return phone_number;
    }

    public void setPhone_number(String phone_number) {
        this.phone_number = phone_number;
    }
}

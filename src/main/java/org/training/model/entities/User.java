package org.training.model.entities;

/**
 * Created by nicko on 1/18/2017.
 */
public class User {
    private Integer id;
    private String login;
    private String password;
    private String email;
    private Role role;

    public User() {
    }

    public User(Integer id, String login, String password, String email, Integer role) {
        this.id = id;
        this.login = login;
        this.password = password;
        if (role == 1) {
            this.role = Role.ADMIN;
        } else if (role == 2) {
            this.role = Role.USER;
        }
        this.email = email;
    }

    public User(String login, String password, String email, Integer role) {
        this.login = login;
        this.password = password;
        if (role == 1) {
            this.role = Role.ADMIN;
        } else if (role == 2) {
            this.role = Role.USER;
        }
        this.email = email;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(int role) {
        if (role == 1) {
            this.role = Role.ADMIN;
        } else if (role == 2) {
            this.role = Role.USER;
        }
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return this.login;
    }
}

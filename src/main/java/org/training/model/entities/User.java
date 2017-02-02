package org.training.model.entities;

import javax.jws.soap.SOAPBinding;

/**
 * Created by nicko on 1/18/2017.
 */
public class User {
    private Integer id;
    private String login;
    private String password;
    private String email;
    private Role role;
    private Double balance;

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

    public void setRole(Role role) {
        this.role = role;
    }

    public Double getBalance() {
        return balance;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
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

    public static class Builder {
        private User user = new User();

        public Builder setId(int id) {
            user.id = id;
            return this;
        }

        public Builder setPassword(String password) {
            user.password = password;
            return this;
        }

        public Builder setEmail(String email) {
            user.email = email;
            return this;
        }

        public Builder setLogin(String login) {
            user.login = login;
            return this;
        }

        public Builder setBalance(Double balance) {
            user.balance = balance;
            return this;
        }

        public Builder setRole(int role) {
            if (role == 1) {
                user.role = Role.ADMIN;
            } else if (role == 2) {
                user.role = Role.USER;
            }
            return this;
        }

        public User build() {
            return user;
        }
    }
}

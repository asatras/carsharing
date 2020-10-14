package com.carsharing.model.db.entity;

import java.math.BigDecimal;
import java.util.Objects;

public class User extends Entity {

    private static final long serialVersionUID = -2185123857944884609L;
    private long id;
    private String login;
    private String password;
    private String email;
    private String name;
    private String surname;
    private String passport;
    private Role role;
    private BigDecimal balance;
    private boolean isActive;

    @Override
    public long getId() {
        return id;
    }

    @Override
    public void setId(long id) {
        this.id = id;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getPassport() {
        return passport;
    }

    public void setPassport(String passport) {
        this.passport = passport;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(id, user.id);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", login='" + login + '\'' +
                ", password='" + password + '\'' +
                ", email=" + email +
                ", name=" + name +
                ", surname=" + surname +
                ", passport=" + passport +
                ", role=" + role +
                ", balance=" + balance +
                ", isActive=" + isActive +
                '}';
    }

    public static Builder builder() {
        return new User().new Builder();
    }

    public class Builder {
        private Builder() {
        }

        public Builder id(Long id) {
            User.this.id = id;
            return this;
        }

        public Builder login(String login) {
            User.this.login = login;
            return this;
        }

        public Builder password(String password) {
            User.this.password = password;
            return this;
        }

        public Builder email(String email) {
            User.this.email = email;
            return this;
        }

        public Builder name(String name) {
            User.this.name = name;
            return this;
        }

        public Builder surname(String surname) {
            User.this.surname = surname;
            return this;
        }

        public Builder passport(String passport) {
            User.this.passport = passport;
            return this;
        }

        public Builder role(Role role) {
            User.this.role = role;
            return this;
        }

        public Builder balance(BigDecimal balance) {
            User.this.balance = balance;
            return this;
        }

        public Builder isActive(Boolean isActive) {
            User.this.isActive = isActive;
            return this;
        }


        public User build() {
            return User.this;
        }
    }

}

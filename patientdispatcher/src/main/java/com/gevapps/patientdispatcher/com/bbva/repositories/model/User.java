package com.gevapps.patientdispatcher.com.bbva.repositories.model;

import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * Created by manuelmola on 19/07/17.
 */
@Entity
public class User {

    @Id
    @GeneratedValue
    private Long id;

    private String username;

    private String password;

    @Enumerated
    private Role role;

    public User() {}

    public User(UserBuilder userBuilder) {
        this.id = userBuilder.id;
        this.username = userBuilder.username;
        this.password = userBuilder.password;
        this.role = userBuilder.role;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public static class UserBuilder {

        private Long id;

        private String username;

        private String password;

        public Role role;

        public UserBuilder withId(final Long id) {
            this.id = id;
            return this;
        }

        public UserBuilder withUsername(final String username) {
            this.username = username;
            return this;
        }

        public UserBuilder withPassword(final String password) {
            this.password = password;
            return this;
        }

        public UserBuilder withRole(final Role role) {
            this.role = role;
            return this;
        }

        public User build() {
            return new User(this);
        }

    }
}

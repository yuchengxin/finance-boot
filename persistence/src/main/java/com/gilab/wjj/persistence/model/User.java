package com.gilab.wjj.persistence.model;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 * Created by yuankui on 12/17/17.
 * <p>
 * Desc:
 * <p>
 * Change:
 */
public class User implements Entity, Cloneable {
    private static final Logger logger = LoggerFactory.getLogger(User.class);
    private long id;
    private String username;
    private String password;
    private List<Permission> permissions;
    private String description;

    public User(){

    }

    public User(long id, String username, String password, List<Permission> permissions, String description) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.permissions = permissions;
        this.description = description;
    }

    public void setId(long id) {
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

    public List<Permission> getPermissions() {
        return permissions;
    }

    public void setPermissions(List<Permission> permissions) {
        this.permissions = permissions;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public long getId() {
        return id;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", permissions='" + permissions + '\'' +
                ", description='" + description +'\'' +
                '}';
    }

    @Override
    public User clone() {
        User cloned = null;
        try {
            cloned = (User) super.clone();
        } catch (CloneNotSupportedException e) {
            logger.error("Failed to clone UserBean", e);
        }
        return cloned;
    }

    public static class Builder {
        private long id;
        private String username;
        private String password;
        private List<Permission> permissions;
        private String description;

        public Builder id(long id) {
            this.id = id;
            return this;
        }

        public Builder username(String username) {
            this.username = username;
            return this;
        }

        public Builder password(String password) {
            this.password = password;
            return this;
        }

        public Builder permissions(List<Permission> permissions) {
            this.permissions = permissions;
            return this;
        }

        public Builder description(String description) {
            this.description = description;
            return this;
        }

        public User build() {
            return new User(id, username, password, permissions, description);
        }
    }

}

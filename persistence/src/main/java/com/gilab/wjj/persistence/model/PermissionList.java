package com.gilab.wjj.persistence.model;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by yuankui on 12/17/17.
 * <p>
 * Desc:
 * <p>
 * Change:
 */
public class PermissionList implements Entity, Cloneable{
    ;
    private static final Logger logger = LoggerFactory.getLogger(PermissionList.class);
    private long id;
    private long menuid;
    private List<Permission> permission;
    private String name;
    private String url;



    public static Logger getLogger() {
        return logger;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getMenuid() {
        return menuid;
    }

    public void setMenuid(long menuid) {
        this.menuid = menuid;
    }

    public List<Permission> getPermission() {
        return permission;
    }

    public void setPermission(List<Permission> permission) {
        this.permission = permission;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public String toString() {
        return "PermissionList{" +
                "id=" + id +
                ", menuid='" + menuid + '\'' +
                ", permission=" + permission +
                ", name='" + name + '\'' +
                ", url='" + url + '\'' +
                '}';
    }

//    @Override
//    public PermissionList clone() {
//        PermissionList cloned = null;
//        try {
//            cloned = (PermissionList) super.clone();
//        } catch (CloneNotSupportedException e) {
//            logger.error("Failed to clone UserBean", e);
//        }
//        return cloned;
//    }
//
//    public static class Builder {
//        private long id;
//        private String menuid;
//        private List<Permission> permission;
//        private String name;
//        private String url;
//
//        public Builder id(long id) {
//            this.id = id;
//            return this;
//        }
//
//        public Builder menuid(String menuid) {
//            this.menuid = menuid;
//            return this;
//        }
//
//        public Builder permission(List<Permission> permission) {
//            this.permission = permission;
//            return this;
//        }
//
//        public Builder name(String name) {
//            this.name = name;
//            return this;
//        }
//
//        public Builder url(String url) {
//            this.url = url;
//            return this;
//        }
//
//        public PermissionList build() {
//            return new PermissionList(id, menuid, permission, name, url);
//        }
//    }
}

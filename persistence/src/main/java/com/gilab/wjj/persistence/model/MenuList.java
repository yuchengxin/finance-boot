package com.gilab.wjj.persistence.model;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by che on 2017/12/19.
 */
public class MenuList {
    private static final Logger logger = LoggerFactory.getLogger(MenuList.class);
    private Integer id;//菜单ID

    private String name;

    private String url;

    private Integer state; //是否根节点

    private String icon;

    private Integer pId;//父节点Id

    public static Logger getLogger() {
        return logger;
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getUrl() {
        return url;
    }

    public Integer getState() {
        return state;
    }

    public String getIcon() {
        return icon;
    }

    public Integer getpId() {
        return pId;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public void setpId(Integer pId) {
        this.pId = pId;
    }

    @Override
    public String toString() {
        return "MenuList{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", url='" + url + '\'' +
                ", state=" + state +
                ", icon='" + icon + '\'' +
                ", pId=" + pId +
                '}';
    }
}

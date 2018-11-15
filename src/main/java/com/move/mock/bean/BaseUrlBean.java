package com.move.mock.bean;

import com.move.mock.util.TextUtil;

import java.io.Serializable;

/**
 * 对应数据库的表：network_mock_baseurl
 * 同时也是客户端传过来的实体对象
 */
public class BaseUrlBean implements Serializable {

    private Integer id;

    private String project;

    private String platform;

    private String env;

    private String baseUrl;

    private String desc;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getProject() {
        return project;
    }

    public void setProject(String project) {
        this.project = project;
    }

    public String getPlatform() {
        return platform;
    }

    public void setPlatform(String platform) {
        this.platform = platform;
    }

    public String getEnv() {
        return env;
    }

    public void setEnv(String env) {
        this.env = env;
    }

    public String getBaseUrl() {
        return baseUrl;
    }

    public void setBaseUrl(String baseUrl) {
        this.baseUrl = baseUrl;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public boolean isDataFull() {

        if(
                TextUtil.isEmpty(project) ||
                TextUtil.isEmpty(env)||
                TextUtil.isEmpty(baseUrl)||
                TextUtil.isEmpty(desc)

        ){
            return false;
        }

        return true;

    }

}

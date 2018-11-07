package com.move.mock.bean;

import com.move.mock.util.TextUtil;

import java.io.Serializable;

public class DataMockRequest implements Serializable {

    private String project;

    private String platform;

    private String env;

    private int version;

    private String requestUrl;

    private String userId;

    /**
     * 请求mock的返回值,会转化成文件,然后存放在服务端
     */
    private String data;

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

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }

    public String getRequestUrl() {
        return requestUrl;
    }

    public void setRequestUrl(String requestUrl) {
        this.requestUrl = requestUrl;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public boolean isDataFull() {

        if (
                TextUtil.isEmpty(project) ||
                        TextUtil.isEmpty(platform) ||
                        TextUtil.isEmpty(env) ||
                        version <= 0 ||
                        TextUtil.isEmpty(requestUrl)
        ) {
            return false;
        }

        return true;
    }

}

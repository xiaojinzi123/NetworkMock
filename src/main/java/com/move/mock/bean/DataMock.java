package com.move.mock.bean;

import java.io.Serializable;

/**
 * 对应数据库表的:network_mock_data
 */
public class DataMock implements Serializable {

    private Integer id;

    private String project;

    private String platform;

    private String env;

    private int version;

    private long createtime;

    private long modifytime;

    private long expiretime;

    private String requestUrl;

    private String requestMethod;

    private String userId;

    private String dataType;

    private String dataLink;

    public DataMock() {
    }

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

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }

    public long getCreatetime() {
        return createtime;
    }

    public void setCreatetime(long createtime) {
        this.createtime = createtime;
    }

    public long getModifytime() {
        return modifytime;
    }

    public void setModifytime(long modifytime) {
        this.modifytime = modifytime;
    }

    public long getExpiretime() {
        return expiretime;
    }

    public void setExpiretime(long expiretime) {
        this.expiretime = expiretime;
    }

    public String getRequestUrl() {
        return requestUrl;
    }

    public void setRequestUrl(String requestUrl) {
        this.requestUrl = requestUrl;
    }

    public String getRequestMethod() {
        return requestMethod;
    }

    public void setRequestMethod(String requestMethod) {
        this.requestMethod = requestMethod;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getDataType() {
        return dataType;
    }

    public void setDataType(String dataType) {
        this.dataType = dataType;
    }

    public String getDataLink() {
        return dataLink;
    }

    public void setDataLink(String dataLink) {
        this.dataLink = dataLink;
    }

    @Override
    public String toString() {
        return "DataMock{" +
                "id=" + id +
                ", project='" + project + '\'' +
                ", platform='" + platform + '\'' +
                ", env='" + env + '\'' +
                ", version=" + version +
                ", createtime=" + createtime +
                ", modifytime=" + modifytime +
                ", requestUrl='" + requestUrl + '\'' +
                ", requestMethod='" + requestMethod + '\'' +
                ", userId='" + userId + '\'' +
                ", dataType='" + dataType + '\'' +
                ", dataLink='" + dataLink + '\'' +
                '}';
    }

}

package com.move.mock.bean;

import com.move.mock.util.TextUtil;

import java.io.Serializable;

/**
 * 对外的模拟数据录入对象,客户端发送的数据最终会经过业务处理后转化成{@link DataMock}
 * 然后保存到数据库
 */
public class NetworkDataBean implements Serializable {

    public static final String DATA_TYPE_OK = "ok";
    public static final String DATA_TYPE_ERROR = "error";

    private String project;

    private String platform;

    private int version;

    private String requestUrl;

    private String requestMethod;

    private String userId;

    /**
     * @see {@link #DATA_TYPE_OK}
     * @see {@link #DATA_TYPE_ERROR}
     */
    private String dataType;

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
                        version <= 0 ||
                        TextUtil.isEmpty(requestUrl) ||
                        TextUtil.isEmpty(requestMethod) ||
                        TextUtil.isEmpty(dataType) ||
                        TextUtil.isEmpty(data)
        ) {
            return false;
        }

        return true;
    }

}

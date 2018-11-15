package com.move.mock.bean;

import com.move.mock.service.baseurl.BaseUrlCache;
import com.move.mock.util.TextUtil;

import java.io.Serializable;

/**
 * 这个实体对象是客户端想获取 mock 数据的入参对象
 * 从数据库检索出 {@link DataMock} 之后转化成为字符串(mock的response json 数据)
 */
public class NetworkDataAccess implements Serializable, BaseUrlCache.OnParseEnvInter {

    private String project;

    private String platform;

    private String env;

    private int version;

    private String requestUrl;

    private String requestMethod;

    private String userId;

    /**
     * @see {@link NetworkDataBean#DATA_TYPE_OK}
     * @see {@link NetworkDataBean#DATA_TYPE_ERROR}
     */
    private String dataType = NetworkDataBean.DATA_TYPE_OK;

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

    public boolean isDataFull() {

        if (
                TextUtil.isEmpty(project) ||
                        TextUtil.isEmpty(platform) ||
                        version <= 0 ||
                        TextUtil.isEmpty(requestUrl) ||
                        TextUtil.isEmpty(requestMethod)
        ) {
            return false;
        }

        return true;
    }

}

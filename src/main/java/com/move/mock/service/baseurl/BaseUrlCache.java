package com.move.mock.service.baseurl;

import com.move.mock.bean.BaseUrlBean;
import com.move.mock.bean.DataMock;
import com.move.mock.bean.NetworkDataAccess;
import com.move.mock.bean.NetworkDataBean;

import java.util.List;

public class BaseUrlCache {

    private static BaseUrlCache instance;

    /**
     * 获取实例对象
     *
     * @return
     */
    public static BaseUrlCache getInstance() {
        if (instance == null) {
            synchronized (BaseUrlCache.class) {
                if (instance == null) {
                    instance = new BaseUrlCache();
                }
            }
        }
        return instance;
    }

    private List<BaseUrlBean> mBaseUrlBeans;

    public synchronized void syncData(List<BaseUrlBean> baseUrlBeans) {
        mBaseUrlBeans = baseUrlBeans;
    }

    public synchronized boolean parseEnv(OnParseEnvInter envInter) {
        if (envInter == null || mBaseUrlBeans == null) {
            return false;
        }
        // 请求的地址
        String requestUrl = envInter.getRequestUrl();
        for (BaseUrlBean urlBean : mBaseUrlBeans) {
            if (requestUrl.startsWith(urlBean.getBaseUrl())) {
                envInter.setEnv(urlBean.getEnv());
                envInter.setRequestUrl(requestUrl.substring(urlBean.getBaseUrl().length()));
                return true;
            }
        }
        return false;
    }

    public interface OnParseEnvInter {

        /**
         * 获取url
         *
         * @return
         */
        String getRequestUrl();

        /**
         * 设置url
         *
         * @param url
         */
        void setRequestUrl(String url);

        /**
         * 设置环境
         *
         * @param env
         */
        void setEnv(String env);


    }

}

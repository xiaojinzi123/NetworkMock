package com.move.mock.service;

import com.move.mock.base.dao.BaseDao;
import com.move.mock.base.service.BaseServiceImpl;
import com.move.mock.bean.DataMock;
import com.move.mock.bean.NetworkDataAccess;
import com.move.mock.bean.NetworkDataBean;
import com.move.mock.mapper.DataMockMapper;
import com.move.mock.util.BusinessException;
import com.move.mock.util.FileUtil;
import com.move.mock.util.Md5Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.util.List;

@Service("dataMockService")
public class DataMockServiceImpl extends BaseServiceImpl<DataMock> implements DataMockService {

    @Autowired
    DataMockMapper dataMockMapper;

    @Override
    protected BaseDao<DataMock, Integer> getBaseDao() {
        return dataMockMapper;
    }

    @Transactional
    public void insert(NetworkDataBean networkDataBean) throws BusinessException {

        File folder = new File(System.getProperty("user.dir"), "/mock");

        if (!folder.exists()) {
            boolean b = folder.mkdirs();
            if (!b) {
                throw new BusinessException("文件夹创建失败");
            }
        }

        if (!networkDataBean.isDataFull()) {
            throw new BusinessException("信息不完整");
        }

        // 最终记录在数据库中的 url
        String resultUrl = null;

        try {

            String url = networkDataBean.getRequestUrl();

            // 用于 url query 的精细化的分类,根据 rule 表
            URI uri = URI.create(url);

            int index = -1;
            if ((index = url.lastIndexOf('?')) != -1) {
                resultUrl = url.substring(0, index);
            } else {
                resultUrl = url;
            }

        } catch (IllegalArgumentException ignore) {
            throw new BusinessException("request url 不合法");
        }

        DataMock dataMock = new DataMock();
        dataMock.setProject(networkDataBean.getProject());
        dataMock.setPlatform(networkDataBean.getPlatform());
        dataMock.setEnv(networkDataBean.getEnv());
        dataMock.setVersion(networkDataBean.getVersion());
        dataMock.setRequestUrl(resultUrl);
        dataMock.setRequestMethod(networkDataBean.getRequestMethod());
        dataMock.setUserId(networkDataBean.getUserId());
        dataMock.setDataType(networkDataBean.getDataType());

        // 计算一个唯一值

        String md5 = Md5Util.MD5(
                dataMock.getProject() +
                        dataMock.getPlatform() +
                        dataMock.getEnv() +
                        dataMock.getVersion() +
                        dataMock.getRequestUrl() +
                        dataMock.getDataType() +
                        dataMock.getUserId());

        if (md5 == null) {
            throw new BusinessException("计算唯一值失败");
        }

        File file = new File(folder, md5 + ".data");

        try {
            FileUtil.saveToFile(file, networkDataBean.getData());
        } catch (IOException e) {
            throw new BusinessException("文件存储失败");
        }

        dataMock.setDataLink(file.getName());
        dataMock.setCreatetime(System.currentTimeMillis());
        dataMock.setModifytime(System.currentTimeMillis());

        insert(dataMock);

    }

    @Override
    public String get(NetworkDataAccess networkDataAccess) throws BusinessException {

        if (!networkDataAccess.isDataFull()) {
            throw new BusinessException("少传入参数了");
        }

        List<DataMock> dataMockList = dataMockMapper.get(networkDataAccess);

        if (dataMockList == null ||
                dataMockList.isEmpty()) {

            throw new BusinessException("数据为空或者不唯一");
        }

        DataMock dataMock = dataMockList.get(0);

        return dataMock.toString();

        /*File folder = new File(System.getProperty("user.dir"), "/mock");
        File file = new File(folder, dataMock.getDataLink());

        if (!file.exists()) {
            throw new BusinessException("file is not exist");
        }

        try {
            return FileUtil.getFromFile(file);
        } catch (IOException e) {
            throw new BusinessException("文件读取失败");
        }*/

    }

}

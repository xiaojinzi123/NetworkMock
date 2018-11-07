package com.move.mock.service;

import com.move.mock.base.dao.BaseDao;
import com.move.mock.base.service.BaseServiceImpl;
import com.move.mock.bean.DataMock;
import com.move.mock.bean.DataMockRequest;
import com.move.mock.mapper.DataMockMapper;
import com.move.mock.util.BusinessException;
import com.move.mock.util.FileUtil;
import com.move.mock.util.Md5Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;

@Service("dataMockService")
public class DataMockServiceImpl extends BaseServiceImpl<DataMock> implements DataMockService {

    @Autowired
    DataMockMapper dataMockMapper;

    @Override
    protected BaseDao<DataMock, Integer> getBaseDao() {
        return dataMockMapper;
    }


    public void insert(DataMockRequest dataMockRequest) throws BusinessException {

        File folder = new File(System.getProperty("user.dir"), "/mock");

        if (!folder.exists()) {
            boolean b = folder.mkdirs();
            if (!b) {
                throw new BusinessException("文件夹创建失败");
            }
        }

        if (!dataMockRequest.isDataFull()) {
            throw new BusinessException("信息不完整");
        }

        DataMock dataMock = new DataMock();
        dataMock.setProject(dataMockRequest.getProject());
        dataMock.setPlatform(dataMockRequest.getPlatform());
        dataMock.setEnv(dataMockRequest.getEnv());
        dataMock.setVersion(dataMockRequest.getVersion());
        dataMock.setRequestUrl(dataMockRequest.getRequestUrl());
        dataMock.setUserId(dataMockRequest.getUserId());

        // 计算一个唯一值

        String md5 = Md5Util.MD5(dataMock.getProject() + dataMock.getPlatform() + dataMock.getEnv() + dataMock.getVersion() + dataMock.getRequestUrl() + dataMock.getUserId());

        if (md5 == null) {
            throw new BusinessException("计算唯一值失败");
        }

        File file = new File(folder, md5 + ".data");

        try {
            FileUtil.saveToFile(file,dataMockRequest.getData());
        } catch (IOException e) {
            throw new BusinessException("文件存储失败");
        }

        dataMock.setDataLink(file.getName());
        dataMock.setCreatetime(System.currentTimeMillis());
        dataMock.setModifytime(System.currentTimeMillis());

        insert(dataMock);

    }

}

package com.move.mock.service.mock;

import com.move.mock.base.dao.BaseDao;
import com.move.mock.base.service.BaseServiceImpl;
import com.move.mock.bean.DataMock;
import com.move.mock.bean.NetworkDataAccess;
import com.move.mock.bean.NetworkDataBean;
import com.move.mock.mapper.DataMockMapper;
import com.move.mock.service.baseurl.BaseUrlCache;
import com.move.mock.util.BusinessException;
import com.move.mock.util.FileUtil;
import com.move.mock.util.Md5Util;
import com.move.mock.util.TextUtil;
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

        // 最终记录在数据库中的 url,目前剔除了query
        String resultUrl = null;

        try {

            String url = networkDataBean.getRequestUrl();

            // 用于 url query 的精细化的分类,根据 rule 表
            URI uri = URI.create(url);

            int index = -1;
            if ((index = url.indexOf('?')) != -1) {
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
        dataMock.setVersion(networkDataBean.getVersion());
        dataMock.setRequestUrl(resultUrl);
        dataMock.setRequestMethod(networkDataBean.getRequestMethod());
        dataMock.setUserId(networkDataBean.getUserId());
        dataMock.setDataType(networkDataBean.getDataType());

        if(!BaseUrlCache.getInstance().parseEnv(dataMock)){
            throw new BusinessException("无法识别 url 的环境(env)");
        }

        if (TextUtil.isEmpty(dataMock.getEnv())) {
            throw new BusinessException("无法识别 url 的环境(env)");
        }

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

        DataMock dbDataMock = dataMockMapper.getByDataLink(md5 + ".data");

        File file = new File(folder, md5 + ".data");

        // 说明已经存过了
        if (dbDataMock != null) {
            // 如果过期了
            if (dbDataMock.getExpiretime() != 0 && dbDataMock.getExpiretime() < System.currentTimeMillis()) {
                try {
                    FileUtil.saveToFile(file, networkDataBean.getData());
                } catch (IOException ignore) {
                    // ignore
                }
                dbDataMock.setModifytime(System.currentTimeMillis());

                // 更新实体对象
                update(dbDataMock);

            }
        } else {

            try {
                FileUtil.saveToFile(file, networkDataBean.getData());
            } catch (IOException e) {
                throw new BusinessException("文件存储失败");
            }

            dataMock.setDataLink(file.getName());
            long currentTimeMillis = System.currentTimeMillis();
            dataMock.setCreatetime(currentTimeMillis);
            dataMock.setModifytime(currentTimeMillis);
            // 表示永远不过期
            dataMock.setExpiretime(0);

            insert(dataMock);

        }

    }

    @Override
    public String get(NetworkDataAccess networkDataAccess) throws BusinessException {

        if (!networkDataAccess.isDataFull()) {
            throw new BusinessException("少传入参数了");
        }

        // 最终记录在数据库中的 url
        String resultUrl = null;

        try {

            String url = networkDataAccess.getRequestUrl();

            // 用于 url query 的精细化的分类,根据 rule 表
            URI.create(url);

            int index = -1;
            if ((index = url.indexOf('?')) != -1) {
                resultUrl = url.substring(0, index);
            } else {
                resultUrl = url;
            }

        } catch (IllegalArgumentException ignore) {
            throw new BusinessException("request url 不合法");
        }

        networkDataAccess.setRequestUrl(resultUrl);

        if(!BaseUrlCache.getInstance().parseEnv(networkDataAccess)){
            throw new BusinessException("无法识别 url 的环境(env)");
        }

        if (TextUtil.isEmpty(networkDataAccess.getEnv())) {
            throw new BusinessException("无法识别 url 的环境(env)");
        }

        List<DataMock> dataMockList = dataMockMapper.get(networkDataAccess);

        if (dataMockList == null ||
                dataMockList.isEmpty() ||
                dataMockList.size() > 1) {

            throw new BusinessException("数据为空或者不唯一");
        }

        DataMock dataMock = dataMockList.get(0);

        File folder = new File(System.getProperty("user.dir"), "/mock");
        File file = new File(folder, dataMock.getDataLink());

        if (!file.exists()) {
            throw new BusinessException("file is not exist");
        }

        try {
            return FileUtil.getFromFile(file);
        } catch (IOException e) {
            throw new BusinessException("文件读取失败");
        }

    }

}
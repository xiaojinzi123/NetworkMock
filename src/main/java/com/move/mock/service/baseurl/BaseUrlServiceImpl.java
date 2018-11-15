package com.move.mock.service.baseurl;

import com.move.mock.base.dao.BaseDao;
import com.move.mock.base.service.BaseServiceImpl;
import com.move.mock.bean.BaseUrlBean;
import com.move.mock.mapper.BaseUrlMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Base Url 处理的业务实现类
 */
@Service("baseUrlService")
public class BaseUrlServiceImpl extends BaseServiceImpl<BaseUrlBean> implements BaseUrlService  {

    @Autowired
    BaseUrlMapper baseUrlMapper;

    @Override
    protected BaseDao<BaseUrlBean, Integer> getBaseDao() {
        return baseUrlMapper;
    }

    @Override
    @Transactional
    public void insert(BaseUrlBean baseUrlBean) throws RuntimeException {
        if (!baseUrlBean.isDataFull()) {
            throw new RuntimeException("信息不完整");
        }
        super.insert(baseUrlBean);

        List<BaseUrlBean> baseUrlBeans = queryAll();
        BaseUrlCache.getInstance().syncData(baseUrlBeans);
    }

    @Override
    @Transactional
    public void deleteById(Integer id) throws RuntimeException {
        super.deleteById(id);
        List<BaseUrlBean> baseUrlBeans = queryAll();
        BaseUrlCache.getInstance().syncData(baseUrlBeans);
    }

    @Override
    @Transactional
    public void update(BaseUrlBean baseUrlBean) throws RuntimeException {
        super.update(baseUrlBean);
        List<BaseUrlBean> baseUrlBeans = queryAll();
        BaseUrlCache.getInstance().syncData(baseUrlBeans);
    }

}

package com.move.mock.service;

import com.move.mock.base.dao.BaseDao;
import com.move.mock.base.service.BaseService;
import com.move.mock.base.service.BaseServiceImpl;
import com.move.mock.bean.BaseUrlBean;
import com.move.mock.mapper.BaseUrlMapper;
import com.move.mock.util.BusinessException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("baseUrlService")
public class BaseUrlServiceImpl extends BaseServiceImpl<BaseUrlBean> implements BaseUrlService  {

    @Autowired
    BaseUrlMapper baseUrlMapper;

    @Override
    protected BaseDao<BaseUrlBean, Integer> getBaseDao() {
        return baseUrlMapper;
    }

    @Override
    public void insert(BaseUrlBean baseUrlBean) throws RuntimeException {

        if (!baseUrlBean.isDataFull()) {
            throw new RuntimeException("信息不完整");
        }

        super.insert(baseUrlBean);
    }

}

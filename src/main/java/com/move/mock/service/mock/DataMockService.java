package com.move.mock.service.mock;

import com.move.mock.base.service.BaseService;
import com.move.mock.bean.DataMock;
import com.move.mock.bean.NetworkDataAccess;
import com.move.mock.bean.NetworkDataBean;
import com.move.mock.util.BusinessException;

public interface DataMockService extends BaseService<DataMock,Integer> {

    void insert(NetworkDataBean networkDataBean) throws BusinessException;

    String get(NetworkDataAccess networkDataAccess) throws BusinessException;

}

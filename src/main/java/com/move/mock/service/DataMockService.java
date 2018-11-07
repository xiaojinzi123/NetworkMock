package com.move.mock.service;

import com.move.mock.base.service.BaseService;
import com.move.mock.bean.DataMock;
import com.move.mock.bean.DataMockRequest;
import com.move.mock.util.BusinessException;

public interface DataMockService extends BaseService<DataMock,Integer> {

    void insert(DataMockRequest dataMockRequest) throws BusinessException;

}

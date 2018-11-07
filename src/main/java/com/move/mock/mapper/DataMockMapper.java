package com.move.mock.mapper;

import com.move.mock.base.dao.BaseDao;
import com.move.mock.bean.DataMock;
import org.apache.ibatis.annotations.Mapper;

/**
 * DataMock的数据库交互对象
 */
@Mapper
public interface DataMockMapper extends BaseDao<DataMock, Integer> {
}

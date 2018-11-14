package com.move.mock.mapper;

import com.move.mock.base.dao.BaseDao;
import com.move.mock.bean.BaseUrlBean;
import com.move.mock.bean.DataMock;
import com.move.mock.bean.NetworkDataAccess;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * DataMock的数据库交互对象
 */
@Mapper
public interface BaseUrlMapper extends BaseDao<BaseUrlBean, Integer> {
}

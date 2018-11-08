package com.move.mock.mapper;

import com.move.mock.base.dao.BaseDao;
import com.move.mock.bean.DataMock;
import com.move.mock.bean.NetworkDataAccess;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * DataMock的数据库交互对象
 */
@Mapper
public interface DataMockMapper extends BaseDao<DataMock, Integer> {

    /**
     * 从数据表中获取指定条件的数据
     *
     * @param networkDataAccess
     * @return
     */
    List<DataMock> get(NetworkDataAccess networkDataAccess);

}

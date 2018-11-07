package com.move.mock.base.service;

import com.move.mock.base.dao.BaseDao;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import java.util.List;

/**
 * Created by cxj on 2016/8/13.
 * 业务对象的基类
 */
public abstract class BaseServiceImpl<T> implements BaseService<T, Integer> {

    @Transactional
    public T queryById(Integer id) {
        return getBaseDao().queryById(id);
    }

    @Transactional
    public List<T> queryAll() {
        return getBaseDao().queryAll();
    }

    @Transactional
    public void insert(T t) throws RuntimeException {
        try {
            getBaseDao().insert(t);
        } catch (Exception e) {
//            result.resultText = e.getMessage();
            e.printStackTrace();
            //回滚事务
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            throw new RuntimeException(e);
        }
    }

    @Transactional
    public void update(T t)  throws RuntimeException {
        try {
            getBaseDao().update(t);
        } catch (Exception e) {
            e.printStackTrace();
            //回滚事务
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            throw new RuntimeException(e);
        }
    }

    @Transactional
    public void deleteById(Integer id)  throws RuntimeException {
        try {
            if (id == null) {
                throw new IllegalArgumentException("id为空");
            }
            getBaseDao().delete(id);
        } catch (Exception e) {
            e.printStackTrace();
            //回滚事务
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            throw new RuntimeException(e);
        }
    }

    /**
     * 获取数据库操作接口
     *
     * @return
     */
    protected abstract BaseDao<T, Integer> getBaseDao();

}

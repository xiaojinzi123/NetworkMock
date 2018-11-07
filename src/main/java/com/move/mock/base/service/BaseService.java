package com.move.mock.base.service;

import java.util.List;

/**
 * Created by cxj on 2016/8/13.
 */
public interface BaseService<T, PK> {

    T queryById(PK pk);

    List<T> queryAll();

    void insert(T t) throws RuntimeException;

    void update(T t) throws RuntimeException;

    void deleteById(PK pk) throws RuntimeException;

}

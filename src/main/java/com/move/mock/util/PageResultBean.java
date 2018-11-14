package com.move.mock.util;

import java.io.Serializable;
import java.util.List;

/**
 * 分页的对象应该用这个包一下的
 */
public class PageResultBean<T> implements Serializable {

    /**
     * 总记录数
     */
    private long totalCount;

    /**
     * 当前页
     */
    private int currentPage;

    private List<T> items;

    public PageResultBean(long totalCount, int currentPage, List<T> items) {
        this.totalCount = totalCount;
        this.currentPage = currentPage;
        this.items = items;
    }

    public long getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(long totalCount) {
        this.totalCount = totalCount;
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    public List<T> getItems() {
        return items;
    }

    public void setItems(List<T> items) {
        this.items = items;
    }

}

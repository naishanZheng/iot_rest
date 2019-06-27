package com.iot.base.pojo;

import com.github.pagehelper.PageInfo;

/**
 * @author zhengnaishan
 * @date 2019/4/2 0002
 * @describe :分页的基础类
 */
public class PageEntity<D extends PageEntity> extends BaseEntity{
    protected PageInfo<D> pageInfo;

    protected int page;//当前页

    protected int limit;//分页条数

    protected int index;//序号

    public PageInfo<D> getPageInfo() {
        return pageInfo;
    }

    public void setPageInfo(PageInfo<D> pageInfo) {
        this.pageInfo = pageInfo;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getLimit() {
        return limit;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }
}

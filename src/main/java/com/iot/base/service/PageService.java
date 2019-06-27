package com.iot.base.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.iot.base.dao.PageMapper;
import com.iot.base.pojo.PageEntity;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * @author zhengnaishan
 * @date 2019/4/2 0008
 * @describe :分页service类
 */
public abstract class PageService< T extends PageEntity , D extends PageMapper<T>> extends BaseService<T,D>{
    @Autowired
    public D d;

    public T getByPage(T t){//分页查询
        int currentPage = t.getPage();
        int pageSize = t.getLimit();
        PageHelper.startPage(currentPage,pageSize);
        //设置t的序号
       // t.setIndex((currentPage-1)*pageSize);
        List<T> list = d.getByPage(t);
        //设置序号
        for(int i=0;i<list.size();i++){
            list.get(i).setIndex((currentPage-1)*pageSize+i+1);//从一开始
        }
        PageInfo<T> pageInfo = new PageInfo<T>(list , pageSize);
        t.setPageInfo(pageInfo);
        return t;
    }
}

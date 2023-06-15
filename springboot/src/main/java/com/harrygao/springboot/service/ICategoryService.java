package com.harrygao.springboot.service;

import com.harrygao.springboot.controller.request.BaseRequest;
import com.harrygao.springboot.entity.Category;
import com.github.pagehelper.PageInfo;

import java.util.List;

public interface ICategoryService {
    
    List<Category> list();

    PageInfo<Category> page(BaseRequest baseRequest);

    void save(Category obj);

    Category getById(Integer id);

    void update(Category obj);

    void deleteById(Integer id);

}

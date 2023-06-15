package com.harrygao.springboot.mapper;

import com.harrygao.springboot.controller.request.BaseRequest;
import com.harrygao.springboot.controller.request.PasswordRequest;
import com.harrygao.springboot.entity.Admin;
import com.harrygao.springboot.entity.Category;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface CategoryMapper {

    List<Category> list();

    List<Category> listByCondition(BaseRequest baseRequest);

    void save(Category obj);

    Category getById(Integer id);

    void updateById(Category user);

    void deleteById(Integer id);

}

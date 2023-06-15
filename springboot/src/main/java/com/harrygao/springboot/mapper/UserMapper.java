package com.harrygao.springboot.mapper;

import com.harrygao.springboot.controller.request.BaseRequest;
import com.harrygao.springboot.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface UserMapper {

//    @Select("select * from user")
    List<User> list();

    List<User> listByCondition(BaseRequest baseRequest);

    void save(User user);

    User getById(Integer id);

    void updateById(User user);

    void deleteById(Integer id);

    User getByUsername(String username);

    @Select("select * from user where name = #{name}")
    User getByName(String name);
}

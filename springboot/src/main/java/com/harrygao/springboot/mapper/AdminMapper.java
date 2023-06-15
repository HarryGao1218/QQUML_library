package com.harrygao.springboot.mapper;

import com.harrygao.springboot.controller.request.BaseRequest;
import com.harrygao.springboot.controller.request.LoginRequest;
import com.harrygao.springboot.controller.request.PasswordRequest;
import com.harrygao.springboot.entity.Admin;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface AdminMapper {

    List<Admin> list();

    List<Admin> listByCondition(BaseRequest baseRequest);

    void save(Admin obj);

    Admin getById(Integer id);

    void updateById(Admin user);

    void deleteById(Integer id);

    Admin getByUsernameAndPassword(@Param("username") String username, @Param("password") String password);

    int updatePassword(PasswordRequest request);

    Admin getByUsername(String username);
}

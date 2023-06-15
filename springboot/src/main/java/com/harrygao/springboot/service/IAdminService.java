package com.harrygao.springboot.service;

import com.harrygao.springboot.controller.dto.LoginDTO;
import com.harrygao.springboot.controller.request.BaseRequest;
import com.harrygao.springboot.controller.request.LoginRequest;
import com.harrygao.springboot.controller.request.PasswordRequest;
import com.harrygao.springboot.entity.Admin;
import com.github.pagehelper.PageInfo;

import java.util.List;

public interface IAdminService {

    List<Admin> list();

    PageInfo<Admin> page(BaseRequest baseRequest);

    void save(Admin obj);

    Admin getById(Integer id);

    void update(Admin obj);

    void deleteById(Integer id);

    LoginDTO login(LoginRequest request);

    void changePass(PasswordRequest request);

}

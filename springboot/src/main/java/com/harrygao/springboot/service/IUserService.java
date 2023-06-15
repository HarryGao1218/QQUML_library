package com.harrygao.springboot.service;

import com.harrygao.springboot.controller.dto.LoginDTO;
import com.harrygao.springboot.controller.dto.UserLoginDTO;
import com.harrygao.springboot.controller.request.BaseRequest;
import com.harrygao.springboot.controller.request.LoginRequest;
import com.harrygao.springboot.controller.request.UserLoginRequest;
import com.harrygao.springboot.controller.request.UserPageRequest;
import com.harrygao.springboot.entity.User;
import com.github.pagehelper.PageInfo;

import java.util.List;

public interface IUserService {

    List<User> list();

    PageInfo<User> page(BaseRequest baseRequest);

    void save(User user);

    User getById(Integer id);


    void update(User user);

    UserLoginDTO userLogin(UserLoginRequest request);

    void deleteById(Integer id);

    void handleAccount(User user);

}

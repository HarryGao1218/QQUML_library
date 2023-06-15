package com.harrygao.springboot.controller;

import cn.hutool.crypto.SecureUtil;
import com.harrygao.springboot.common.Result;
import com.harrygao.springboot.controller.dto.LoginDTO;
import com.harrygao.springboot.controller.dto.UserLoginDTO;
import com.harrygao.springboot.controller.request.LoginRequest;
import com.harrygao.springboot.controller.request.PasswordRequest;
import com.harrygao.springboot.controller.request.UserLoginRequest;
import com.harrygao.springboot.controller.request.UserPageRequest;
import com.harrygao.springboot.entity.Admin;
import com.harrygao.springboot.entity.User;
import com.harrygao.springboot.exception.ServiceException;
import com.harrygao.springboot.service.IUserService;
import com.harrygao.springboot.utils.UserTokenUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    IUserService userService;

    @PostMapping("/save")
    public Result save(@RequestBody User user) {
        userService.save(user);
        return Result.success();
    }

    @PostMapping("/account")
    public Result account(@RequestBody User user) {
        userService.handleAccount(user);
        return Result.success();
    }

    @PutMapping("/update")
    public Result update(@RequestBody User user) {
        userService.update(user);
        return Result.success();
    }

    @DeleteMapping("/delete/{id}")
    public Result delete(@PathVariable Integer id) {
        userService.deleteById(id);
        return Result.success();
    }

    @GetMapping("/{id}")
    public Result getById(@PathVariable Integer id) {
        User user = userService.getById(id);
        return Result.success(user);
    }

    @GetMapping("/list")
    public Result list() {
        List<User> users = userService.list();
        return Result.success(users);
    }

    @GetMapping("/page")
    public Result page(UserPageRequest userPageRequest) {
        return Result.success(userService.page(userPageRequest));
    }

    @PostMapping("/login")
    public Result userLogin(@RequestBody UserLoginRequest request) {
        UserLoginDTO userLogin = userService.userLogin(request);
        return Result.success(userLogin);
    }

}

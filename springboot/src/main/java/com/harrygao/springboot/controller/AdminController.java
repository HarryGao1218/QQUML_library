package com.harrygao.springboot.controller;

import com.harrygao.springboot.common.Result;
import com.harrygao.springboot.controller.dto.LoginDTO;
import com.harrygao.springboot.controller.request.AdminPageRequest;
import com.harrygao.springboot.controller.request.LoginRequest;
import com.harrygao.springboot.controller.request.PasswordRequest;
import com.harrygao.springboot.entity.Admin;
import com.harrygao.springboot.service.IAdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    IAdminService adminService;

    @PostMapping("/login")
    public Result login(@RequestBody LoginRequest request) {
        LoginDTO login = adminService.login(request);
        return Result.success(login);
    }

    @PutMapping("/password")
    public Result password(@RequestBody PasswordRequest request) {
        adminService.changePass(request);
        return Result.success();
    }

    @PostMapping("/save")
    public Result save(@RequestBody Admin obj) {
        adminService.save(obj);
        return Result.success();
    }

    @PutMapping("/update")
    public Result update(@RequestBody Admin obj) {
        adminService.update(obj);
        return Result.success();
    }

    @DeleteMapping("/delete/{id}")
    public Result delete(@PathVariable Integer id) {
        adminService.deleteById(id);
        return Result.success();
    }

    @GetMapping("/{id}")
    public Result getById(@PathVariable Integer id) {
        Admin obj = adminService.getById(id);
        return Result.success(obj);
    }

    @GetMapping("/list")
    public Result list() {
        List<Admin> list = adminService.list();
        return Result.success(list);
    }

    @GetMapping("/page")
    public Result page(AdminPageRequest pageRequest) {
        return Result.success(adminService.page(pageRequest));
    }

}

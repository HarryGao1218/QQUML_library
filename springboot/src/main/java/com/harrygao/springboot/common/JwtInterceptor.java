package com.harrygao.springboot.common;

import cn.hutool.core.util.StrUtil;
import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.harrygao.springboot.entity.Admin;
import com.harrygao.springboot.entity.User;
import com.harrygao.springboot.exception.ServiceException;
import com.harrygao.springboot.service.IAdminService;
import com.harrygao.springboot.service.IUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
@Slf4j
public class JwtInterceptor implements HandlerInterceptor {

    private static final String ERROR_CODE_401 = "401";

    @Autowired
    private IAdminService adminService;

    @Autowired
    private IUserService userService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        String token = request.getHeader("token");
        if (StrUtil.isBlank(token)) {
            token = request.getParameter("token");
        }

        // 执行认证
        if (StrUtil.isBlank(token)) {
            throw new ServiceException(ERROR_CODE_401, "无token，请重新登录");
        }

        // 获取 token 中的adminId
        String adminId;
        Admin admin;
        String userId;
        User user;

        if(Integer.parseInt(JWT.decode(token).getAudience().get(0))<10)
        {
            try {
            adminId = JWT.decode(token).getAudience().get(0);
            // 根据token中的userid查询数据库
            admin = adminService.getById(Integer.parseInt(adminId));
        } catch (Exception e) {
            String errMsg = "token验证失败，请重新登录";
            log.error(errMsg + ", token=" + token, e);
            throw new ServiceException(ERROR_CODE_401, errMsg);
        }
            if (admin == null) {
                throw new ServiceException(ERROR_CODE_401, "用户不存在，请重新登录");
            }

            try {
                // 用户密码加签验证 token
                JWTVerifier jwtVerifier = JWT.require(Algorithm.HMAC256(admin.getPassword())).build();
                jwtVerifier.verify(token); // 验证token
            } catch (JWTVerificationException e) {
                throw new ServiceException(ERROR_CODE_401, "token验证失败，请重新登录");
            }
            return true;}

        else{try {
            userId = JWT.decode(token).getAudience().get(0);
            // 根据token中的userid查询数据库
            user = userService.getById(Integer.parseInt(userId));
        } catch (Exception e) {
            String errMsg = "token验证失败，请重新登录";
            log.error(errMsg + ", token=" + token, e);
            throw new ServiceException(ERROR_CODE_401, errMsg);
        }
            if (user == null) {
                throw new ServiceException(ERROR_CODE_401, "用户不存在，请重新登录");
            }

            try {
                // 用户密码加签验证 token
                JWTVerifier jwtVerifier = JWT.require(Algorithm.HMAC256(user.getPassword())).build();
                jwtVerifier.verify(token); // 验证token
            } catch (JWTVerificationException e) {
                throw new ServiceException(ERROR_CODE_401, "token验证失败l，请重新登录");
            }
            return true;}
    }
}


package com.harrygao.springboot.service.impl;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.crypto.SecureUtil;
import com.harrygao.springboot.controller.dto.UserLoginDTO;
import com.harrygao.springboot.controller.request.BaseRequest;
import com.harrygao.springboot.controller.request.UserLoginRequest;
import com.harrygao.springboot.controller.request.UserPageRequest;
import com.harrygao.springboot.entity.User;
import com.harrygao.springboot.exception.ServiceException;
import com.harrygao.springboot.mapper.UserMapper;
import com.harrygao.springboot.service.IUserService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.harrygao.springboot.utils.UserTokenUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;
import lombok.extern.slf4j.Slf4j;

import java.util.Date;
import java.util.List;

@Slf4j
@Service
public class UserService implements IUserService {

    private static final String PASS_SALT = "harry";
    private static final String DEFAULT_PASS = "123123";

    @Autowired
    UserMapper userMapper;

    @Override
    public List<User> list() {
        return userMapper.list();
    }

    @Override
    public PageInfo<User> page(BaseRequest baseRequest) {
        PageHelper.startPage(baseRequest.getPageNum(), baseRequest.getPageSize());
        List<User> users = userMapper.listByCondition(baseRequest);
        return new PageInfo<>(users);
    }

    @Override
    public void save(User user) {
        Date date = new Date();
        // 当做卡号来处理
        user.setUsername(DateUtil.format(date, "yyyyMMdd") + Math.abs(IdUtil.fastSimpleUUID().hashCode()));
        // 默认密码 123
        if (StrUtil.isBlank(user.getPassword())) {
            user.setPassword(DEFAULT_PASS);
        }
        user.setPassword(securePass(user.getPassword()));  // 设置md5加密，加盐
        try {
            userMapper.save(user);
        } catch (DuplicateKeyException e) {
            log.error("数据插入失败， username:{}", user.getUsername(), e);
            throw new ServiceException("用户名重复");
        }
    }

    @Override
    public User getById(Integer id) {
        return userMapper.getById(id);
    }

    @Override
    public void update(User user) {
        user.setUpdatetime(new Date());
        userMapper.updateById(user);
    }

    @Override
    public void deleteById(Integer id) {
        userMapper.deleteById(id);
    }

    @Override
    public void handleAccount(User user) {
        Integer score = user.getScore();
        if (score == null) {
            return;
        }
        Integer id = user.getId();
        User dbUser = userMapper.getById(id);
        dbUser.setAccount(dbUser.getAccount() + score);
        userMapper.updateById(dbUser);
    }

    @Override
    public UserLoginDTO userLogin(UserLoginRequest request) {
        User user = null;
        try {
            user = userMapper.getByName(request.getName());
//            System.out.println("lalala:"+request.getName()+"\n"+user);
        } catch (Exception e) {
            log.error("根据用户名{} 查询出错", request.getName());
            throw new ServiceException("用户名错误");
        }
        if (user == null) {
            throw new ServiceException("用户名或密码错误");
        }
        // 判断密码是否合法
        String securePass = securePass(request.getPassword());
        if (!securePass.equals(user.getPassword())) {
            throw new ServiceException("密码不合法");
        }
        UserLoginDTO userLoginDTO = new UserLoginDTO();
        BeanUtils.copyProperties(user, userLoginDTO);

        // 生成token
        String token = UserTokenUtils.genUserToken(String.valueOf(user.getId()), user.getPassword());
        userLoginDTO.setToken(token);
        return userLoginDTO;
    }

    private String securePass(String password) {
        return SecureUtil.md5(password + PASS_SALT);
    }


}

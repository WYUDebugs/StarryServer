package com.zhuolang.starryserver.service.impl;

import com.zhuolang.starryserver.dao.UserDao;
import com.zhuolang.starryserver.dto.ResultDto;
import com.zhuolang.starryserver.entity.User;
import com.zhuolang.starryserver.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service  //添加UserService的test时，选中类名UserServiceImpl，右键go to->test-创建->选择Junit4,选择添加的测试方法，finish
public class UserServiceImpl implements UserService {

    //注入Dao实现类依赖
    //    @Resource
    @Autowired
    private UserDao userDao;

    //也可以注入别的dao,例如对应post_tab表的PostDao，这样就可以根据需求整合多表的增删改查


    /**注册会用到
     * 通过 phone和password 来添加 User
     * @param phone
     * @param password
     * @return
     */
    public ResultDto addUserByPhonePsw(String phone, String password) {
        //先判断手机号是否已被注册
        User user = userDao.findUserByPhone(phone);
        if (user != null) {
            return  new ResultDto(200, "phone_exist", null);
        }
        //手机号未被注册，进行注册，且记录注册时间
        if (userDao.addUserByPhonePsw(phone, password,new Date()) == 1) {
            return new ResultDto(200, "register_success", null);
        }
        return new ResultDto(200, "register_failure", null);
    }

    /**
     * 通过phone查询User信息
     * @param phone
     * @return 查找成功返回User，没有则null
     */
    public User findUserByPhone(String phone) {
        return userDao.findUserByPhone(phone);
    }

    /**
     * 通过phone检验密码是否正确
     * @param phone
     * @param password
     * @return 正确返回1，错误返回0
     */
    public ResultDto checkPassword(String phone, String password) {
        User user = userDao.checkPassword(phone,password);
        if (user == null) {
            return new ResultDto(200, "login_failure", null);
        }else {
            return new ResultDto(200, "login_success", user);
        }
    }


}

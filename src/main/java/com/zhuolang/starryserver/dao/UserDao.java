package com.zhuolang.starryserver.dao;

import com.zhuolang.starryserver.entity.User;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

/**
 * Created by wunaifu on 2018/7/28.
 * 定义一些通过什么参数进行增删改查的方法，只需要定义，实现在对应的*Dao.xml里实现，这就是mybatis做的工作
 */
public interface UserDao {//添加UserDao的test时，选中类名UserDao，右键go to->test-创建->选择Junit4,选择添加的测试方法，finish

    /**注册会用到
     * 通过 phone和password 来添加 User
     * @param phone
     * @param password
     * @return 插入的行数
     */
    int addUserByPhonePsw(@Param("phone") String phone, @Param("password") String password, @Param("registerTime") Date registerTime);

    /**
     * 通过phone查询User信息
     * @param phone
     * @return 查找成功返回User，没有则null
     */
    User findUserByPhone(String phone);
}
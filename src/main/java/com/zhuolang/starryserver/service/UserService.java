package com.zhuolang.starryserver.service;

import com.zhuolang.starryserver.dto.ResultDto;
import com.zhuolang.starryserver.entity.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserService {

    ResultDto addUserByPhonePsw(String phone, String password);

    /**
     * 通过phone查询User信息
     *
     * @param phone
     * @return 查找成功返回User，没有则null
     */
    User findUserByPhone(String phone);

    /**
     * 通过id查询User信息
     *
     * @param id
     * @return 查找成功返回User，没有则null
     */
    User findUserById(int id);

    /**
     * 通过phone检验密码是否正确
     *
     * @param phone
     * @param password
     * @return 正确返回uesr，失败返回null
     */
    ResultDto checkPassword(String phone,String password);

    /**
     * 查找所有User，并按年龄降序排序好
     *
     * @return
     */
    List<User> findAllUserDESC();
}

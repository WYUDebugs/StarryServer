package com.zhuolang.starryserver.service;

import com.zhuolang.starryserver.dto.ResultDto;
import com.zhuolang.starryserver.entity.User;
import org.apache.ibatis.annotations.Param;

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
     * 通过phone检验密码是否正确
     *
     * @param phone
     * @param password
     * @return 正确返回uesr，失败返回null
     */
    ResultDto checkPassword(String phone,String password);

}

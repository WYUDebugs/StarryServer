package com.zhuolang.starryserver.service;

import com.zhuolang.starryserver.dto.ResultDto;
import com.zhuolang.starryserver.entity.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserService {

    /**注册会用到
     * 通过 name、phone和password 来添加 User
     * @param phone
     * @param password
     * @param name
     * @return
     */
    ResultDto addUserByPhonePsw(String phone, String password ,String name);

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

    /**
     * 通过id和password更改phone
     *
     * @param id
     * @return 更改成功返回1，没有则0
     */
    int changePhoneById(int id , String phone);

    /**
     * 通过id修改headimage
     *
     * @param id
     * @param headiamge
     * @return 更改成功返回1，没有则0
     */
    int changeHeadimageById(int id , String headiamge);

    /**
     * 通过id修改用户基本信息
     *
     *
     * @param user
     * @return 更改成功返回1，没有则0
     */
    int changeUserById(User user);

    /**
     * 通过phone删除uesr
     *
     *
     * @param phone
     * @return 删除成功返回1，没有则0
     */
    int deleteUserByPhone(String phone);
}

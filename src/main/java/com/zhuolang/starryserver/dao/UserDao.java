package com.zhuolang.starryserver.dao;

import com.zhuolang.starryserver.entity.User;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.Date;
import java.util.List;

/**
 * Created by wunaifu on 2018/7/28.
 * 定义一些通过什么参数进行增删改查的方法，只需要定义，实现在对应的*Dao.xml里实现，这就是mybatis做的工作
 */
public interface UserDao {//添加UserDao的test时，选中类名UserDao，右键go to->test-创建->选择Junit4,选择添加的测试方法，finish

    /**注册会用到
     * 通过 name、phone和password 来添加 User
     *
     * @param phone
     * @param password
     * @param name
     * @return 插入的行数
     */
    int addUserByPhonePsw(@Param("phone") String phone, @Param("password") String password, @Param("name") String name, @Param("registerTime") Date registerTime);

    /**
     * 通过phone查询User信息
     * @param phone
     * @return 查找成功返回User，没有则null
     */
    User findUserByPhone(String phone);

    /**
     * 通过id查询User信息
     * @param id
     * @return 查找成功返回User，没有则null
     */
    User findUserById(int id);

    /**
     * 通过phone检验密码是否正确
     * @param phone
     * @param password
     * @return 正确返回user，失败返回null
     */
    User checkPassword(@Param("phone") String phone,@Param("password") String password);

    /**
     * 查找所有User，并按年龄降序排序好
     * @return
     */
    List<User> findAllUserDESC();

    /**
     * 通过id和password更改phone
     *
     * @param id
     * @param password
     * @return 更改成功返回1，没有则0
     */
    int changePhoneById(@Param("id") int id ,@Param("password") String password, @Param("phone") String phone);

    /**
     * 通过id修改headimage
     *
     * @param id
     * @param headiamge
     * @return 更改成功返回1，没有则0
     */
    int changeHeadimageById(@Param("id") int id , @Param("headimage") String headiamge);

    /**
     * 通过id修改用户基本信息
     *
     *
     * @param user
     * @return 更改成功返回1，没有则0
     */
    int changeUserById(@Param("user") User user);

    /**
     * 通过phone删除uesr
     *
     *
     * @param phone
     * @return 删除成功返回1，没有则0
     */
    int deleteUserByPhone(@Param("phone") String phone);

    /**
     * crete by HMP
     * 通过id获取username
     * 获取点赞人的名字需要用到
     * @param id
     * @return
     */
    String findUserNameById(@Param("id")int id);
}

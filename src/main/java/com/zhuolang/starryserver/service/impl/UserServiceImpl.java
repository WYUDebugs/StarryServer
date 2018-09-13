package com.zhuolang.starryserver.service.impl;

import com.zhuolang.starryserver.dao.UserDao;
import com.zhuolang.starryserver.dto.ResultDto;
import com.zhuolang.starryserver.entity.User;
import com.zhuolang.starryserver.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service  //添加UserService的test时，选中类名UserServiceImpl，右键go to->test-创建->选择Junit4,选择添加的测试方法，finish
public class UserServiceImpl implements UserService {

    //注入Dao实现类依赖
    //    @Resource
    @Autowired
    private UserDao userDao;

    //也可以注入别的dao,例如对应post_tab表的PostDao，这样就可以根据需求整合多表的增删改查


    /**注册会用到
     * 通过 name、phone和password 来添加 User
     * @param phone
     * @param password
     * @param name
     * @return
     */
    public ResultDto addUserByPhonePsw(String phone, String password, String name) {
        //先判断手机号是否已被注册
        User user = userDao.findUserByPhone(phone);
        if (user != null) {
            return  new ResultDto(200, "phone_exist", null);
        }
        //手机号未被注册，进行注册，且记录注册时间
        if (userDao.addUserByPhonePsw(phone, password, name ,new Date()) == 1) {
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
     * 通过id查询User信息
     * @param id
     * @return 查找成功返回User，没有则null
     */
    public User findUserById(int id) {
        return userDao.findUserById(id);
    }

    /**
     * 通过phone检验密码是否正确
     * @param phone
     * @param password
     * @return
     */
    public ResultDto checkPassword(String phone, String password) {
        User user = userDao.checkPassword(phone,password);
        if (user == null) {
            return new ResultDto(200, "login_failure", null);
        }else {
            return new ResultDto(200, "login_success", user);
        }
    }

    /**
     * 查找所有User，并按年龄降序排序好
     * @return
     */
    public List<User> findAllUserDESC() {
        return userDao.findAllUserDESC();
    }

    /**
     * 通过id和password更改phone
     *
     * @param id
     * @return 更改成功返回1，没有则0
     */
    public int changePhoneById(int id , String phone){
        return userDao.changePhoneById(id,phone);
    }

    /**
     * 通过id修改headimage
     *
     * @param id
     * @param headimage
     * @return 更改成功返回1，没有则0
     */
    public int changeHeadimageById(int id , String headimage){
        return userDao.changeHeadimageById(id ,headimage);
    }

    /**
     * 通过id修改用户基本信息
     *
     *
     * @param user
     * @return 更改成功返回1，没有则0
     */
    public int changeUserById(User user){
        return userDao.changeUserById(user);
    }

    /**
     * 通过phone删除uesr
     *
     *
     * @param phone
     * @return 删除成功返回1，没有则0
     */
    public int deleteUserByPhone(String phone){
        return userDao.deleteUserByPhone(phone);
    }
}

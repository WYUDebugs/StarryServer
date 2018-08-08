package com.zhuolang.starryserver.dao;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

//配置spring和junit整合，这样junit在启动时就会加载spring容器
@RunWith(SpringJUnit4ClassRunner.class)
//告诉junit spring的配置文件
@ContextConfiguration({"classpath:spring/spring-dao.xml"})
public class UserDaoTest {

    //@Autowired
    //private UserDao userDao;

    @Test
    public void addUserByPhonePsw() {

    }

    @Test
    public void addUserByPhonePsw1() {
    }

    @Test
    public void deleteUserByPhone() {
    }

    @Test
    public void updatePswByPhonePsw() {
    }

    @Test
    public void checkPassword() {
    }

    @Test
    public void findUserByPhone() {
    }

    @Test
    public void findAllUserDESC() {
    }

    @Test
    public void updateUser() {
    }

    @Test
    public void updateUserByIdSelective() {
//        User user = new User();
//        user.setId(1);
//        user.setAddress("更新地址啦");
//        user.setAge(23);
//        user.setHeight("sdfdfsa");
//        System.out.println(userDao.findUserByPhone("1"));
//        //System.out.println(userDao.updateUserByIdSelective(user));
    }
}
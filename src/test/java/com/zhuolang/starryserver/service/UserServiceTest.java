package com.zhuolang.starryserver.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
//告诉junit spring的配置文件
@ContextConfiguration({"classpath:spring/spring-dao.xml",
        "classpath:spring/spring-service.xml"})
public class UserServiceTest {

    @Test
    public void addUserByPhonePsw() {
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
    public void updataUser() {
    }

    @Test
    public void updateUserByIdSelective() {
    }

    @Test
    public void insertUserSelective() {
    }
}
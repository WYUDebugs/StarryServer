package com.zhuolang.starryserver.dao;

import com.zhuolang.starryserver.entity.Publish;
import com.zhuolang.starryserver.entity.PublishDto;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

//配置spring和junit整合，这样junit在启动时就会加载spring容器
@RunWith(SpringJUnit4ClassRunner.class)
//告诉junit spring的配置文件
@ContextConfiguration({"classpath:spring/spring-dao.xml"})
public class UserDaoTest {

    @Autowired
    PublishDao publishDao;

    @Test
    public void publishList() {
        List<PublishDto> publishList = publishDao.findPublishListByUserId(4);
        System.out.println(publishList);
    }

}
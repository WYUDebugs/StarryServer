package com.zhuolang.starryserver.service.impl;

import com.zhuolang.starryserver.dao.PublishDao;
import com.zhuolang.starryserver.dto.ResultDto;
import com.zhuolang.starryserver.entity.Publish;
import com.zhuolang.starryserver.entity.User;
import com.zhuolang.starryserver.service.PublishService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class PublishServiceImpl implements PublishService {
    @Autowired
    private PublishDao publishDao;

    @Override
    public ResultDto publish(int publisher,String content,String address,int type) {

        int result=publishDao.publish(publisher,new Date(),content,address,type);

        if (result==1) {
            return new ResultDto(200, "publish_success", null);
        } else {
            return new ResultDto(200, "publish_failure", null);
        }

    }

    @Override
    public List<Publish> showPostDESC() {
        return publishDao.showPostDESC();
    }


}

package com.zhuolang.starryserver.service.impl;

import com.zhuolang.starryserver.dao.PublishGoodDao;
import com.zhuolang.starryserver.dao.PublishDao;
import com.zhuolang.starryserver.dao.UserDao;
import com.zhuolang.starryserver.entity.PublishGood;
import com.zhuolang.starryserver.entity.Publish;
import com.zhuolang.starryserver.entity.User;
import com.zhuolang.starryserver.service.PublishGoodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
/**
 * Created by HuangMingPan on 2018/9/03.
 */

@Service
public class PublishGoodServiceImpl implements PublishGoodService {
    @Autowired
    private PublishGoodDao goodDao;
    @Autowired
    private PublishDao publishDao;
    @Autowired
    private UserDao userDao;

    @Override
    public List<String> praise(int pId,int uId) {
        //通过pId和uId判断此用户点赞该帖子的状态,是否已经点过赞
        List<PublishGood> publishGoods =goodDao.checkManByAIdPId(pId,uId);
        if (publishGoods != null && publishGoods.size() > 0) {
            //当已经点赞过
            PublishGood publishGood = publishGoods.get(0);
            //通过点赞id删除点赞的信息,也就是客户端的取消赞功能
            goodDao.deleteById(publishGood.getId());
            //通过帖子的id找到该帖子
            Publish publish = publishDao.findPublishById(pId);
            //点赞数-1
            publish.setTimes_of_praise(publish.getTimes_of_praise() - 1);
            //获取更新后点赞数
            int praiseNum=publish.getTimes_of_praise();
            //更新帖子的信息
            publishDao.updatePublishById(praiseNum,pId);
        } else {
            //当还没有点赞过,插入点赞信息,帖子表点赞数+1
            goodDao.addPraiseMan(pId,uId,new Date());
            Publish publish=publishDao.findPublishById(pId);
            publish.setTimes_of_praise(publish.getTimes_of_praise()+1);
            int praiseNum=publish.getTimes_of_praise();
            publishDao.updatePublishById(praiseNum,pId);
        }
        //通过帖子id获取点赞该帖子的所有人的id
        List<Integer> mansId=goodDao.findMansIdByPublishId(pId);
        //通过userId获取点赞人的名字
        List<String> namesList=new ArrayList<>();
        for (int manId : mansId) {
            String  userName=userDao.findUserNameById(manId);
            namesList.add(userName);
        }
        return namesList;

    }
}

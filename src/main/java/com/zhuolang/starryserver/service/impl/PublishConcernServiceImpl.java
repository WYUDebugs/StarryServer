package com.zhuolang.starryserver.service.impl;

import com.zhuolang.starryserver.dao.PublishConcernDao;
import com.zhuolang.starryserver.dao.PublishDao;
import com.zhuolang.starryserver.dao.UserDao;
import com.zhuolang.starryserver.entity.Publish;
import com.zhuolang.starryserver.entity.PublishConcern;
import com.zhuolang.starryserver.entity.User;
import com.zhuolang.starryserver.service.PublishConcernService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class PublishConcernServiceImpl implements PublishConcernService {
    @Autowired
    private PublishConcernDao concernDao;
    @Autowired
    private PublishDao publishDao;
    @Autowired
    private UserDao userDao;

    @Override
    public List<String> concern(int pId, int mId) {
        //查询该用户对此该帖子的关注状态
        List<PublishConcern> concernList=concernDao.checkByAIdPId(pId,mId);
        //如果已经关注过,再次点击关注按钮,则取消关注,帖子表的关注人数-1
        if (concernList != null & concernList.size() > 0) {
            PublishConcern concern = concernList.get(0);
            //取消关注
            concernDao.deleteById(concern.getId());
            //通过帖子的id获取该帖子
            Publish publish = publishDao.findPublishById(pId);
            publish.setCollection_number(publish.getCollection_number()-1);
            //获取更新后关注数数
            int concernNum=publish.getCollection_number();
            //更新帖子的信息
            publishDao.updateConcernNumById(concernNum,pId);
        } else {
            //添加关注
            concernDao.addConcernMan(mId,pId,new Date());
            Publish publish=publishDao.findPublishById(pId);
           publish.setCollection_number(publish.getCollection_number()+1);
           int concernNum=publish.getCollection_number();
           publishDao.updateConcernNumById(concernNum,pId);
        }
        //通过帖子id获取关注该帖子的所有人的id
        List<Integer> mansId=concernDao.findMansIdByPublishId(pId);
        //通过userId获取关注人的名字
        List<String> namesList=new ArrayList<>();
        for (int manId : mansId) {
            String  userName=userDao.findUserNameById(manId);
            namesList.add(userName);
        }
        return namesList;
    }
}

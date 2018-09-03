package com.zhuolang.starryserver.service.impl;

import com.zhuolang.starryserver.dao.*;
import com.zhuolang.starryserver.dto.ResultDto;
import com.zhuolang.starryserver.entity.Publish;
import com.zhuolang.starryserver.service.PublishService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class PublishServiceImpl implements PublishService {
    @Autowired
    private PublishDao publishDao;
    @Autowired
    private PublishImageDao publishImageDao;
    @Autowired
    private FriendDao friendDao;
    @Autowired
    private PublishGoodDao publishGoodDao;
    @Autowired
    private PublishConcernDao publishConcernDao;

    /**
     * 记录帖子的信息
     * 发布或分享帖子时会用到
     * @param publisher
     * @param content
     * @param address
     * @param type
     * @return
     */
    @Override
    public ResultDto publish(int publisher,String content,String address,int type) {

        int result=publishDao.publish(publisher,new Date(),content,address,type);

        if (result==1) {
            return new ResultDto(200, "publish_success", null);
        } else {
            return new ResultDto(200, "publish_failure", null);
        }

    }

    /**
     * 获取user的帖子
     * 浏览朋友圈时会用到
     * 将帖子以时间降序的形式展示在朋友圈中
     * @return
     */
    @Override
    public List<List<Publish>> showPostDESC(int publisher) {
        List<Integer> friendId= friendDao.findFriendIdByUseId(publisher);//获取user朋友的id
        List<Publish> publisherPost=publishDao.showPostDESC(publisher); //获取user的帖子
        List<List<Publish>> post=new ArrayList();
        post.add(publisherPost);
        for (Integer FId:friendId) {
            List<Publish> friendPost=publishDao.showPostDESC(FId);      //利用foreach获取朋友的帖子
            post.add(friendPost);
        }
        return post;
    }

    /**
     * 通过帖子id 删除某条帖子
     * @param id
     * @return
     */
    @Override
    public ResultDto deletePublisher(int id) {
        //先删除关联的外表
        publishImageDao.deleteImageByPublishId(id);  //删除图片的信息
        publishGoodDao.deleteByPublishId(id); //删除点赞的信息
        publishConcernDao.deleteByPublishId(id); //删除关注的信息
        int result=publishDao.deletePublish(id); //再删除主表
        if (result==1) {
            return new ResultDto(200, "delete_success");
        } else {
            return new ResultDto(200, "delete_success");
        }
    }

    /**
     * 通过发布人id (publisher)展示个人的帖子
     * @param publisher
     * @return
     */
    @Override
    public List<Publish> showSomeonePublish(int publisher) {
        return publishDao.showPostDESC(publisher);
    }


}

package com.zhuolang.starryserver.service.impl;

import com.zhuolang.starryserver.dao.*;
import com.zhuolang.starryserver.dto.ResultDto;
import com.zhuolang.starryserver.entity.Publish;
import com.zhuolang.starryserver.entity.PublishDto;
import com.zhuolang.starryserver.exception.MyThrowException;
import com.zhuolang.starryserver.service.PublishService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


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
     * 发布帖子
     *
     * @param publish
     * @param imageList
     * @return
     */
    @Transactional //事务注解标签
    @Override
    public int sendPublic(Publish publish, List<String> imageList) {
        try {
            if (publishDao.installPublish(publish) == 1) {
                int publistId = publish.getId();
                for (int i = 0; i < imageList.size(); i++) {
                    if (publishImageDao.addPublishImage(publistId, imageList.get(i)) == 1) {

                    } else {
                        throw new MyThrowException("install_image_failure");
                    }
                }
            } else {
                throw new MyThrowException("install_publish_failure");
            }
        } catch (MyThrowException e) {
            //System.out.println("e========================" + e.getMessage());
            throw e;
        }
        return 1;
    }

    @Override
    public int sendPublicNoImage(Publish publish) {
        return publishDao.installPublish(publish);
    }

    /**
     * 通过用户id，获取用户及好友的帖子信息，同时带出图片、发布人的名字、头像
     * @param userId
     * @return
     */
    @Override
    public List<PublishDto> findPublishListByUserId(int userId) {
        List<Integer> list = new ArrayList<>();
        list.add(userId);
        List<Integer> friendIdList = friendDao.findFriendIdByUseId(userId);//获取user朋友的id
        if (friendIdList != null && friendIdList.size() > 0) {
            list.addAll(friendIdList);
        }
        return publishDao.findPublishListByUserIdList(userId,list);
    }

    /**
     * 记录帖子的信息
     * 发布或分享帖子时会用到
     *
     * @param publisher
     * @param content
     * @param address
     * @param type
     * @return
     */
    @Override
    public ResultDto publish(int publisher, String content, String address, int type) {

        int result = publishDao.publish(publisher, new Date(), content, address, type);

        if (result == 1) {
            return new ResultDto(200, "publish_success", null);
        } else {
            return new ResultDto(200, "publish_failure", null);
        }

    }

    /**
     * 获取user的帖子
     * 浏览朋友圈时会用到
     * 将帖子以时间降序的形式展示在朋友圈中
     *
     * @return
     */
    @Override
    public List<List<Publish>> showPostDESC(int publisher) {
        List<Integer> friendId = friendDao.findFriendIdByUseId(publisher);//获取user朋友的id
        List<Publish> publisherPost = publishDao.showPostDESC(publisher); //获取user的帖子
        List<List<Publish>> post = new ArrayList();
        post.add(publisherPost);
        for (Integer FId : friendId) {
            List<Publish> friendPost = publishDao.showPostDESC(FId);      //利用foreach获取朋友的帖子
            post.add(friendPost);
        }
        return post;
    }

    /**
     * 通过帖子id 删除某条帖子
     *
     * @param id
     * @return
     */
    @Override
    public ResultDto deletePublisher(int id) {
        //先删除关联的外表
        publishImageDao.deleteImageByPublishId(id);  //删除图片的信息
        publishGoodDao.deleteByPublishId(id); //删除点赞的信息
        publishConcernDao.deleteByPublishId(id); //删除关注的信息
        int result = publishDao.deletePublish(id); //再删除主表
        if (result == 1) {
            return new ResultDto(200, "delete_success");
        } else {
            return new ResultDto(200, "delete_success");
        }
    }

    /**
     * 通过发布人id (publisher)展示个人的帖子
     *
     * @param publisher
     * @return
     */
    @Override
    public List<Publish> showSomeonePublish(int publisher) {
        return publishDao.showPostDESC(publisher);
    }

    @Override
    public int publishNum(int uId) {
        return publishDao.publishNum(uId);
    }


}

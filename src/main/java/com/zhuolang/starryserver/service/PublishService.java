package com.zhuolang.starryserver.service;

import com.zhuolang.starryserver.dto.ResultDto;
import com.zhuolang.starryserver.entity.Publish;
import com.zhuolang.starryserver.entity.PublishDto;

import java.util.List;


public interface PublishService {

    int sendPublic(Publish publish,List<String> imageList);

    List<PublishDto> findPublishListByUserId(int userId);

    /**
     * 记录帖子的信息
     * 发布或分享帖子时会用到
     * @param publisher
     * @param content
     * @param address
     * @param type
     * @return
     */
    ResultDto publish(int publisher,String content,String address,int type);

    /**
     * 浏览朋友圈时会用到
     * 将帖子以时间降序的形式展示在朋友圈中
     * @return
     */
    List<List<Publish>> showPostDESC(int publisher);


    ResultDto deletePublisher(int id);

    /**
     * 访问个人主页时会用到
     * @param publisher
     * @return
     */
    List<Publish> showSomeonePublish(int publisher);

}

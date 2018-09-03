package com.zhuolang.starryserver.dao;

import com.zhuolang.starryserver.entity.Publish;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;


public interface PublishDao {

    int installPublish(@Param("publish") Publish publish);
    /**
     * 记录帖子信息
     * @param publisher
     * @param time
     * @param content
     * @param address
     * @param type
     * @return
     */
    int publish(@Param("publisher")int publisher, @Param("time") Date time,
                @Param("content")String content,@Param("address")String address,@Param("type")int type);

    /**
     * 将帖子以时间降序的形式排序好
     * @return
     */
    List<Publish> showPostDESC(@Param("publisher")int publisher);

    /**
     * 删除某条记录时用到
     * 通过帖子id删除对应的帖子
     * @param id
     * @return
     */
    int deletePublish(@Param("id")int id);

    /**
     * 通过帖子的id找到该帖子
     * @param id
     * @return
     */
    Publish findPublishById(@Param("id")int id);

    /**
     * 通过帖子id更新点赞数
     * @param praiseNum
     * @param id
     * @return
     */
    int updatePublishById(@Param("praiseNum")int praiseNum,@Param("id")int id);

    /**
     * 通过帖子的id更新关注收藏数
     * @param concernNum
     * @param id
     * @return
     */
    int updateConcernNumById(@Param("concernNum")int concernNum,@Param("id")int id);
}

package com.zhuolang.starryserver.dao;

import com.zhuolang.starryserver.entity.Publish;
import com.zhuolang.starryserver.entity.PublishDto;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;


public interface PublishDao {

    /**
     * 插入帖子
     * @param publish
     * @return
     */
    int installPublish(@Param("publish") Publish publish);

    /**
     * 通过用户id查找他的所有帖子信息，包括用户信息、帖子图片
     * @param userId
     * @return
     */
    List<PublishDto> findPublishListByUserId(@Param("userId") int userId);

    List<PublishDto> findPublishListByUserIdList(@Param("userId") int userId,@Param("ids") List<Integer> ids);

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
     * 通过帖子的id得到点赞数
     * @param id
     * @return
     */

    Integer findPublishById(@Param("id")int id);

    /**
     * 通过帖子的id得到关注收藏数
     * @param id
     * @return
     */
    Integer findConcernNum(@Param("id")int id);

    /**
     * 通过帖子的id得到评论数
     * @param id
     * @return
     */
    Integer findCommentNum(@Param("id")int id);

    /**
     * 通过帖子id更新点赞数
     * 点赞，点赞数+1
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

    /**
     * 通过帖子id更新评论数
     * @param cNum
     * @param id
     * @return
     */
    int updateCommentNum(@Param("cNum")int cNum,@Param("id")int id);
}

package com.zhuolang.starryserver.dao;

import com.zhuolang.starryserver.entity.PublishConcern;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

/**
 * Created by HuangMingPan on 2018/9/03.
 */
public interface PublishConcernDao {
    /**
     * 添加关注会用到
     * @param concernPeopleId
     * @param concernPublishId
     * @param concernTime
     * @return
     */
    int addConcernMan(@Param("concernPeople")int concernPeopleId,
                      @Param("concernPublish")int concernPublishId, @Param("concernTime")Date concernTime);

    /**
     * 查询某人对此贴的关注状态
     * @param peopleId
     * @param publishId
     * @return
     */
    List<PublishConcern> checkByAIdPId(@Param("peopleId")int peopleId,@Param("publishId")int publishId);

    /**
     * 取消关注会用到
     * @return
     */
    int deleteById(@Param("id")int id);

    /**
     * 通过关注贴子的id获取所有关注此帖子的人的id
     * @param publishId
     * @return
     */
    List<Integer> findMansIdByPublishId(@Param("publishId")int publishId);

    /**
     * 通过帖子id 删除关注信息,删除帖子时会用到
     * @param publishId
     * @return
     */
    int deleteByPublishId(@Param("publishId")int publishId);

}

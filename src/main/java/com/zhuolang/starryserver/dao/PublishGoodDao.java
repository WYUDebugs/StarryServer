package com.zhuolang.starryserver.dao;

import com.zhuolang.starryserver.entity.PublishGood;
import com.zhuolang.starryserver.entity.PublishGoodDto;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

/**
 * Created by HuangMingPan on 2018/9/03.
 */

public interface PublishGoodDao {
    /**
     * 添加点赞信息
     * 点赞功能
     * @param publishId
     * @param manOfPraise
     * @param time
     * @return
     */
    int addPraiseMan(@Param("publishId")int publishId, @Param("manOfPraise")int manOfPraise, @Param("time")Date time);

    /**
     * 查询点赞状态
     * @param publishId
     * @param manId
     * @return
     */
    List<PublishGood> checkManByAIdPId(@Param("pId")int publishId, @Param("mId")int manId);

    /**
     * 删除点赞信息,取消点赞功能
     * @param id
     * @return
     */
    int deleteById(@Param("id")int id);

    /**
     * 通过帖子id查询所有点赞该帖子的人的id
     * @param publishId
     * @return
     */
    List<Integer> findMansIdByPublishId(@Param("publishId")int publishId);

    /**
     * 通过帖子表id删除点赞信息,删除帖子时会用到
     * @param publishId
     * @return
     */
    int deleteByPublishId(@Param("publishId")int publishId);

    int updatePraiseState(@Param("gdIds")List<Integer> gdIds);

     List<PublishGoodDto> showUnReadPraise(@Param("uId")int uId,@Param("pIds")List<Integer> pIds);
}

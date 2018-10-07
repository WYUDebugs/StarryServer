package com.zhuolang.starryserver.dao;

import com.zhuolang.starryserver.entity.Moment;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface MomentDao {

    int installMoment(@Param("moment")Moment moment);

    int deleteByMomentId(@Param("mId")int mId);

    List<Moment> findMomentListBybId( @Param("bId") int bId);

    Moment findMomentBymId(@Param("mId")int mId);

    List<Integer> findMomentIdBybId(@Param("bId")int bId);

    int findBookIdBymId(@Param("mId")int mId);
}

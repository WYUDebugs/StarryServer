package com.zhuolang.starryserver.dao;

import com.zhuolang.starryserver.entity.MomentComment;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface MomentCommentDao {

    int addMomentComment(@Param("mComment")MomentComment mComment);

    List<MomentComment> showComments(@Param("mId")int mId);

    int deleteCommentByMid(@Param("mId")int mId);
}

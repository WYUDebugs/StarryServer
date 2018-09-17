package com.zhuolang.starryserver.dao;

import com.zhuolang.starryserver.entity.PublicComment;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface PublicCommentDao {

    int addComment(@Param("comment")PublicComment comment);

    int deleteComment(@Param("id")int id);

    List<PublicComment> showAllComment(@Param("pId")int pId);
}

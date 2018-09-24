package com.zhuolang.starryserver.service;

import com.zhuolang.starryserver.dto.ResultDto;
import com.zhuolang.starryserver.entity.PublicComment;


public interface PublicCommentService {
    int addComment(int pId,PublicComment comment);
    int deleteComment(int pId,int id);
    ResultDto showAllComment(int pId);
}

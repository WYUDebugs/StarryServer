package com.zhuolang.starryserver.service;

import com.zhuolang.starryserver.dto.ResultDto;
import com.zhuolang.starryserver.entity.PublicComment;

import java.util.List;


public interface PublicCommentService {
    int addComment(int pId,PublicComment comment);
    int deleteComment(int pId,int id);
    ResultDto showAllComment(int pId);
    ResultDto updateCommentState(List<Integer> cmIds);
    ResultDto showUnReadComment(int uId);
}

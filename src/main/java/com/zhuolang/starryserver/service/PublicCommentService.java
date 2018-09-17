package com.zhuolang.starryserver.service;

import com.zhuolang.starryserver.dto.ResultDto;
import com.zhuolang.starryserver.entity.PublicComment;


public interface PublicCommentService {
    ResultDto addComment(PublicComment comment);
    ResultDto deleteComment(int id);
    ResultDto showAllComment(int pId);
}

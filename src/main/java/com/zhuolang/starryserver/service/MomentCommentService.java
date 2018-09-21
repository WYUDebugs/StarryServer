package com.zhuolang.starryserver.service;

import com.zhuolang.starryserver.dto.ResultDto;
import com.zhuolang.starryserver.entity.MomentComment;

public interface MomentCommentService {
    ResultDto addmComment(MomentComment mComment);

    ResultDto showComments(int mId);
}

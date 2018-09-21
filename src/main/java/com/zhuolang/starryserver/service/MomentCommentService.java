package com.zhuolang.starryserver.service;

import com.zhuolang.starryserver.dto.ResultDto;
import com.zhuolang.starryserver.entity.MomentComment;

public interface MomentCommentService {
    ResultDto addmCommen(MomentComment mComment);

    ResultDto showComments(int mId);
}

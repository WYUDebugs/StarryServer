package com.zhuolang.starryserver.service.impl;

import com.zhuolang.starryserver.dao.MomentCommentDao;
import com.zhuolang.starryserver.dto.ResultDto;
import com.zhuolang.starryserver.entity.MomentComment;
import com.zhuolang.starryserver.service.MomentCommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MommentCommentServiceImpl implements MomentCommentService {
    @Autowired
    private MomentCommentDao momentCommentDao;

    @Override
    public ResultDto addmCommen(MomentComment mComment) {
        int result=momentCommentDao.addMomentComment(mComment);
        if (result == 1) {
          return  new ResultDto(200, "success");
        } else {
            return new ResultDto(200,"failure");
        }

    }

    @Override
    public ResultDto showComments(int mId) {
        List<MomentComment> mComments=momentCommentDao.showComments(mId);
        if (mComments != null & mComments.size() > 0) {
            return new ResultDto(200, "success", mComments);
        } else {
            return new ResultDto(200,"该片段还没有人评论",null);
        }

    }
}

package com.zhuolang.starryserver.service.impl;

import com.zhuolang.starryserver.dao.PublicCommentDao;
import com.zhuolang.starryserver.dao.PublishDao;
import com.zhuolang.starryserver.dto.ResultDto;
import com.zhuolang.starryserver.entity.PublicComment;
import com.zhuolang.starryserver.entity.PublicCommentDto;
import com.zhuolang.starryserver.service.PublicCommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PublicCommentServiceImpl implements PublicCommentService {

    @Autowired
    private PublicCommentDao publicCommentDao;
    @Autowired
    private PublishDao publishDao;
    @Override
    public ResultDto addComment(PublicComment comment) {
        int result=publicCommentDao.addComment(comment);
        Integer cNum=publishDao.findCommentNum(comment.getPublicId());
        int cNum2=0;
        if (cNum!=null) {
            cNum2=cNum.intValue();
        }else{
            new ResultDto(200,"failure",null);
        }
        if (result == 1) {
           int result2= publishDao.updateCommentNum(cNum2+1,comment.getPublicId());
            if (result2 == 1) {
               return new ResultDto(200, "success", null);
            } else {
               return new ResultDto(200,"failure",null);
            }
        } else {
            return new ResultDto(200,"failure",null);
        }
    }
    @Override
    public ResultDto deleteComment(int id) {
        int result=publicCommentDao.deleteComment(id);
        if (result == 1) {
            return new ResultDto(200, "success", null);
        } else {
            return new ResultDto(200,"failure",null);
        }
    }

    @Override
    public ResultDto showAllComment(int pId) {

      List<PublicCommentDto> comments=publicCommentDao.showCommentIdList(pId);
       if (comments != null & comments.size() > 0) {
            return new ResultDto(200, "success", comments);
        } else {
            return new ResultDto(200,"failure",null);
        }
    }

}

package com.zhuolang.starryserver.service.impl;

import com.zhuolang.starryserver.dao.PublicCommentDao;
import com.zhuolang.starryserver.dao.PublishDao;
import com.zhuolang.starryserver.dto.ResultDto;
import com.zhuolang.starryserver.entity.PublicComment;
import com.zhuolang.starryserver.entity.PublicCommentDto;
import com.zhuolang.starryserver.exception.MyThrowException;
import com.zhuolang.starryserver.service.PublicCommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class PublicCommentServiceImpl implements PublicCommentService {

    @Autowired
    private PublicCommentDao publicCommentDao;
    @Autowired
    private PublishDao publishDao;

    @Transactional
    @Override
    public int addComment(int pId,PublicComment comment) {
        try {
            if (publicCommentDao.addComment(comment) == 1) {
                if (publishDao.updateCommentNum(pId) == 1) {
                } else {
                    throw new MyThrowException("add_failure");
                }
            } else {
                throw new MyThrowException("add_failure");
            }

        } catch (MyThrowException e) {
            throw e;
        }
        return 1;
    }
    @Transactional
    @Override
    public int deleteComment(int pId,int id) {
        try {
            if (publicCommentDao.deleteComment(id)==1) {
                int result = publishDao.updateCommentNum2(pId);
                if (result == 1) {

                } else {
                    throw new MyThrowException("delete_failure");
                }
            } else {
                throw new MyThrowException("delete_failure");
            }
        } catch (MyThrowException e) {
            throw e;
        }
        return 1;
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

    @Override
    public ResultDto updateCommentState(List<Integer> cmIds) {
        int result=publicCommentDao.updateCommentState(cmIds);
        if (result > 0) {
            return new ResultDto(200, "success", null);
        } else {
            return new ResultDto(200,"failure",null);
        }
    }

    @Override
    public ResultDto showUnReadComment(int uId) {
        List<Integer> pIds=publishDao.getPublishId(uId);
        List<PublicCommentDto> unReadComment1=publicCommentDao.showUnReadComment(uId,pIds);
        List<PublicCommentDto> unReadComment2=publicCommentDao.showUnReadComment2(uId,pIds);
        List<PublicCommentDto> list=new ArrayList<>();
        list.addAll(unReadComment1);
        list.addAll(unReadComment2);
        if (list != null && list.size() > 0) {
            return new ResultDto(200, "success", list);
        } else {
            return new ResultDto(200,"failure",null);
        }
    }

}

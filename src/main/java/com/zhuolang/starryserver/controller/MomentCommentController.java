package com.zhuolang.starryserver.controller;

import com.zhuolang.starryserver.dto.ResultDto;
import com.zhuolang.starryserver.entity.MomentComment;
import com.zhuolang.starryserver.entity.PublicComment;
import com.zhuolang.starryserver.service.MomentCommentService;
import com.zhuolang.starryserver.utils.TimeUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

@Controller
@RequestMapping("/mComment")
public class MomentCommentController {
    @Autowired
    private MomentCommentService momentCommentService;

    @ResponseBody//将返回的数据处理为json
    @RequestMapping(value = "/addComment")
    public ResultDto addComment(HttpServletRequest request) {
        MomentComment mComment=new MomentComment();
        int cId=Integer.parseInt(request.getParameter("cId")); //获取评论者的id
        mComment.setCommentator(cId);
        int uId=Integer.parseInt(request.getParameter("uId"));//获取userId
        //如果uId等于cId，自己的评论，state插入为1，已读
        if (uId == cId) {
            mComment.setState(1);
        } else {
            mComment.setState(0);
        }
        int mId=Integer.parseInt(request.getParameter("mId")); //获取评论片段的id
        mComment.setMomentId(mId);
        mComment.setCommentTime(TimeUtil.dateToString(new Date()));
        mComment.setCommentContent(request.getParameter("cContent")); //获取评论的内容
        ResultDto resultDto=momentCommentService.addmComment(mComment);
        return resultDto;
    }
    @ResponseBody//将返回的数据处理为json
    @RequestMapping(value = "/showMComments")
    public ResultDto showMComments( HttpServletRequest request) {

            int mId=Integer.parseInt(request.getParameter("mId")); //获取片段的id
        ResultDto resultDto=momentCommentService.showComments(mId);
        return resultDto;
    }
}

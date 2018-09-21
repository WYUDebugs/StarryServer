package com.zhuolang.starryserver.controller;

import com.zhuolang.starryserver.dto.ResultDto;
import com.zhuolang.starryserver.entity.PublicComment;
import com.zhuolang.starryserver.service.PublicCommentService;
import com.zhuolang.starryserver.utils.TimeUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import javax.servlet.http.HttpServletRequest;
import java.util.Date;


@Controller
@RequestMapping("/comment")
public class PublicCommentController {

    @Autowired
    private PublicCommentService publicCommentService;

    /**
     * 添加评论
     * @param request
     * @return
     */
    @ResponseBody//将返回的数据处理为json
    @RequestMapping(value = "/add")
    public ResultDto addComment( HttpServletRequest request) {

        PublicComment comment=new PublicComment();
        int cId=Integer.parseInt(request.getParameter("cId")); //获取评论者的id
        comment.setCommentator(cId);
        comment.setCommentTime(TimeUtil.dateToString(new Date()));
        if (request.getParameter("rId") != null) {
            int rId = Integer.parseInt(request.getParameter("rId")); //获取被评论者的id
            comment.setReceiveId(rId);
        }
        int pId=Integer.parseInt(request.getParameter("pId")); //获取评论帖子的id
        comment.setPublicId(pId);
        String cContent=request.getParameter("cContent"); //获取评论的内容
        comment.setCommentContent(cContent);
        ResultDto resultDto=publicCommentService.addComment(comment);
        return resultDto;

    }

    /**
     * 删除评论
     * @param request
     * @return
     */
    @ResponseBody//将返回的数据处理为json
    @RequestMapping(value = "/delete")
    public ResultDto deleteComment( HttpServletRequest request) {

        int id=Integer.parseInt(request.getParameter("id"));//获取评论的id
        ResultDto resultDto=publicCommentService.deleteComment(id);
        return resultDto;
    }

    @ResponseBody//将返回的数据处理为json
    @RequestMapping(value = "/show")
    public ResultDto showAllComment( HttpServletRequest request) {

        int pId=Integer.parseInt(request.getParameter("pId")); //获取帖子的id
        ResultDto resultDto=publicCommentService.showAllComment(pId);
        return resultDto;
    }


}

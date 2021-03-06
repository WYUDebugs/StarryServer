package com.zhuolang.starryserver.controller;

import com.zhuolang.starryserver.dto.ResultDto;
import com.zhuolang.starryserver.entity.PublicComment;
import com.zhuolang.starryserver.exception.MyThrowException;
import com.zhuolang.starryserver.service.PublicCommentService;
import com.zhuolang.starryserver.utils.TimeUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;


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
        if (comment.getReceiveId() == 0) {
            comment.setState(1);
        } else {
            comment.setState(0);
        }
        int pId=Integer.parseInt(request.getParameter("pId")); //获取评论帖子的id
        comment.setPublicId(pId);
        String cContent=request.getParameter("cContent"); //获取评论的内容
        comment.setCommentContent(cContent);
        int result=0;
        try {
            result=publicCommentService.addComment(pId,comment);
        }catch (MyThrowException e) {
            System.out.println("异常捕捉：MyThrowException：" + e.getMessage());
            e.printStackTrace();
        } catch (Exception e) {
            System.out.println("异常捕捉：Exception：" + e.getMessage());
            e.printStackTrace();
        }
        if (result == 1) {
            return new ResultDto(200, "success", null);
        } else {
            return new ResultDto(200,"failure",null);
        }
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
        int pId=Integer.parseInt(request.getParameter("pId")); //获取帖子id，用以更新帖子评论数
        int result=0;
        try {
            result=publicCommentService.deleteComment(pId,id);
        }catch (MyThrowException e) {
            System.out.println("异常捕捉：MyThrowException：" + e.getMessage());
            e.printStackTrace();
        } catch (Exception e) {
            System.out.println("异常捕捉：Exception：" + e.getMessage());
            e.printStackTrace();
        }
        if (result == 1) {
            return new ResultDto(200, "delete_success", null);
        } else {
            return new ResultDto(200,"delete_failure",null);
        }
    }

    @ResponseBody//将返回的数据处理为json
    @RequestMapping(value = "/show")
    public ResultDto showAllComment( HttpServletRequest request) {

        int pId=Integer.parseInt(request.getParameter("pId")); //获取帖子的id
        ResultDto resultDto=publicCommentService.showAllComment(pId);
        return resultDto;
    }

    /**
     * 将未读评论标记为已读
     * @param ids
     * @return
     */
    @ResponseBody//将返回的数据处理为json
    @RequestMapping(value = "/updateCommentState")
    public ResultDto updateCommentState(@RequestParam(value = "ids") List<Integer> ids) {
        return publicCommentService.updateCommentState(ids);
    }

    /**
     * 展示未读的评论列表
     * @param request
     * @return
     */
    @ResponseBody//将返回的数据处理为json
    @RequestMapping(value = "/showUnReadComment")
    public ResultDto showUnReadComment(HttpServletRequest request) {
        int uId=Integer.parseInt(request.getParameter("uId"));//获取用户的id
        return publicCommentService.showUnReadComment(uId);
    }


}

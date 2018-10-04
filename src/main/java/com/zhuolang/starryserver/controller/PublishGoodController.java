package com.zhuolang.starryserver.controller;

import com.zhuolang.starryserver.dto.ResultDto;
import com.zhuolang.starryserver.entity.User;
import com.zhuolang.starryserver.exception.BaseExceptionHandleAction;
import com.zhuolang.starryserver.service.PublishGoodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

import java.util.List;

import static java.lang.Integer.parseInt;


/**
 * Created by HuangMingPan on 2018/9/03.
 */
@Controller
@RequestMapping("/good")
public class PublishGoodController extends BaseExceptionHandleAction {

    @Autowired
    private PublishGoodService publishGoodService;

    @ResponseBody//将返回的数据处理为json
    @RequestMapping(value = "/showPraiseMans")
    public ResultDto  showPraiseMans( HttpServletRequest request) {
        int publishId=parseInt(request.getParameter("pId")); //获取点赞的帖子的id
        List<User> users= publishGoodService.showPraise(publishId);
        if (users != null & users.size() > 0) {
            return new ResultDto(200, "success", users);
        } else {
            return new ResultDto(200,"该帖子暂时还没有人点赞",null);
        }
    }

    @ResponseBody//将返回的数据处理为json
    @RequestMapping(value = "/praise")
    public ResultDto  praise( HttpServletRequest request) {
        int uId=parseInt(request.getParameter("mId")); //获取点赞人的id
        int pId=parseInt(request.getParameter("pId")); //获取点赞的帖子的id
        ResultDto resultDto=publishGoodService.praise(pId,uId);
        return resultDto;
    }

    /**
     * 将未读点赞信息标记为已读状态
     * @param ids
     * @return
     */
    @ResponseBody//将返回的数据处理为json
    @RequestMapping(value = "/updatePraiseState")
    public ResultDto updatePraiseState(@RequestParam(value = "ids") List<Integer> ids) {
        return publishGoodService.updatePraiseState(ids);
    }

    /**
     * 展示未读点赞信息列表
     * @param request
     * @return
     */
    @ResponseBody//将返回的数据处理为json
    @RequestMapping(value = "/showUnReadComment")
    public ResultDto showUnReadComment(HttpServletRequest request) {
        int uId=Integer.parseInt(request.getParameter("uId"));//获取用户的id
        return publishGoodService.showUnReadPraise(uId);
    }
}

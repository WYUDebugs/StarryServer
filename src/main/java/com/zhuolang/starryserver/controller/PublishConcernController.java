package com.zhuolang.starryserver.controller;

import com.zhuolang.starryserver.dto.ResultDto;
import com.zhuolang.starryserver.entity.User;
import com.zhuolang.starryserver.exception.BaseExceptionHandleAction;
import com.zhuolang.starryserver.service.PublishConcernService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

import java.util.List;

import static java.lang.Integer.parseInt;


/**
 * Created by HuangMingPan on 2018/9/03.
 */
@Controller
@RequestMapping("concern")
public class PublishConcernController extends BaseExceptionHandleAction {

    @Autowired
    private PublishConcernService publishConcernService;

    @ResponseBody//将返回的数据处理为json
    @RequestMapping(value = "/showConcernMans")
    public ResultDto  showConcernMans( HttpServletRequest request) {
        int manId=parseInt(request.getParameter("mId")); //获取关注人的id
        int publishId=parseInt(request.getParameter("pId")); //获取关注的帖子的id
        List<String> names=publishConcernService.concern(publishId,manId);
        if (names != null & names.size() > 0) {
            return new ResultDto(200, "success", names);
        } else {
            return new ResultDto(200,"该帖子暂时还没有人关注",null);
        }
    }
}

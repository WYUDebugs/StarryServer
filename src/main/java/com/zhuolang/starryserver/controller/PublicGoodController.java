package com.zhuolang.starryserver.controller;

import com.zhuolang.starryserver.dto.ResultDto;
import com.zhuolang.starryserver.entity.Good;
import com.zhuolang.starryserver.exception.BaseExceptionHandleAction;
import com.zhuolang.starryserver.service.GoodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

import static java.lang.Integer.parseInt;

/**
 * Created by HuangMingPan on 2018/8/29.
 */
@Controller
@RequestMapping("/praise")
public class PublicGoodController extends BaseExceptionHandleAction {
    @Autowired
    private GoodService goodService;

    @ResponseBody//将返回的数据处理为json
    @RequestMapping(value = "/praiseMans")
    public ResultDto selectMansById(HttpServletRequest request) {
        int manId=parseInt(request.getParameter("manId")); //发布人的id
        Good good=goodService.checkMansById(manId);
        if (good!= null ) {
            return new ResultDto(200, "success", good);
        } else {
            return new ResultDto(200, "NoData", null);
        }
    }
}

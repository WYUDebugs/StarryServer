package com.zhuolang.starryserver.controller;

import com.zhuolang.starryserver.dto.ResultDto;
import com.zhuolang.starryserver.entity.Publish;
import com.zhuolang.starryserver.entity.User;
import com.zhuolang.starryserver.exception.BaseExceptionHandleAction;
import com.zhuolang.starryserver.service.PublishService;
import com.zhuolang.starryserver.service.UserService;
import com.zhuolang.starryserver.utils.FileUploadUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

import static java.lang.Integer.parseInt;


/**
 * Created by HuangMingPan on 2018/8/29.
 */
@Controller
@RequestMapping("/publish")
public class PublishController extends BaseExceptionHandleAction {


    @Autowired
    private PublishService publishService;

    /**
     * 记录帖子的信息
     * @param request
     * @return
     */
    @ResponseBody//将返回的数据处理为json
    @RequestMapping(value = "/record")
    public ResultDto  recordPost(HttpServletRequest request) {
            int publisher=parseInt(request.getParameter("publisher")); //发布人的id
            String content=request.getParameter("content");  //发布的内容
            String address=request.getParameter("address");  //定位的地址
            int type=parseInt(request.getParameter("type"));
            ResultDto resultDto=publishService.publish(publisher,content,address,type);
            return resultDto;
    }

    @ResponseBody
    @RequestMapping(value = "/show")
    public ResultDto showPost() {

        List<Publish> publishList=publishService.showPostDESC();
        if (publishList!= null && publishList.size() > 0) {
            return new ResultDto(200, "success", publishList);
        } else {
            return new ResultDto(200, "NoData", null);
        }
    }

}

package com.zhuolang.starryserver.controller;

import com.zhuolang.starryserver.dto.ResultDto;
import com.zhuolang.starryserver.entity.Publish;
import com.zhuolang.starryserver.exception.BaseExceptionHandleAction;
import com.zhuolang.starryserver.service.PublishService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
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
     * 将帖子的信息插入数据库
     * 发布帖子时用到
     * @param request
     * @return
     */
    @ResponseBody//将返回的数据处理为json
    @RequestMapping(value = "/record")
    public ResultDto recordPost(HttpServletRequest request) {
        int publisher = parseInt(request.getParameter("publisher")); //发布人的id
        String content = request.getParameter("content");  //发布的文字内容
        String address = request.getParameter("address");  //定位的地址
        int type = parseInt(request.getParameter("type"));
        ResultDto resultDto = publishService.publish(publisher, content, address, type);
        return resultDto;
    }

    /**
     * 展示帖子时会用到
     * 将帖子以时间降序的形式排序
     * 展示帖子时需要把publisher发布人id传过来
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/show")
    public ResultDto showPost(HttpServletRequest request) {
        int publisher=Integer.parseInt(request.getParameter("publisher"));
        List<List<Publish>> publishList = publishService.showPostDESC(publisher);
        if (publishList != null && publishList.size() > 0) {
            return new ResultDto(200, "success", publishList);
        } else {
            return new ResultDto(200, "NoData", null);
        }
    }

    /**
     * 访问个人主页时会用到
     * 以时间降序的排序展示个人的帖子
     * 使用该功能需要把publisher发布人的id传进来
     * @param request
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/showSomeonePost")
    public ResultDto showSomeonePost(HttpServletRequest request) {
        int publisher=Integer.parseInt(request.getParameter("publisher"));
        List<Publish> publishList=publishService.showSomeonePublish(publisher);
        if (publishList != null && publishList.size() > 0) {
            return new ResultDto(200, "success", publishList);
        } else {
            return new ResultDto(200, "NoData", null);
        }
    }
    /**
     * 个人主页删除一条贴子时会用到
     * 通过帖子的id 删除对应的帖子同时删除与之关联的表的数据
     * 使用该功能需要把帖子id传过来
     * @param request
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/deleteOne")
    public ResultDto deletePublish(HttpServletRequest request) {
        int id = Integer.parseInt(request.getParameter("id"));
        ResultDto resultDto = publishService.deletePublisher(id);
        return resultDto;
    }
}
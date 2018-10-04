package com.zhuolang.starryserver.controller;

import com.zhuolang.starryserver.dto.ResultDto;
import com.zhuolang.starryserver.entity.Publish;
import com.zhuolang.starryserver.entity.PublishDto;
import com.zhuolang.starryserver.exception.BaseExceptionHandleAction;
import com.zhuolang.starryserver.exception.MyThrowException;
import com.zhuolang.starryserver.service.PublishImageService;
import com.zhuolang.starryserver.service.PublishService;
import com.zhuolang.starryserver.utils.FileUploadUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
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

    @Autowired
    private PublishImageService publishImageService;

    /**
     * 发布帖子，插入帖子内容、图片
     * @param request
     * @return
     */
    @ResponseBody//将返回的数据处理为json
    @RequestMapping(value = "/sendPublic")
    public ResultDto sendPublic(@RequestParam(value = "file")MultipartFile[] file, HttpServletRequest request) {
        Publish publish = new Publish();
        publish.setPublisher(parseInt(request.getParameter("publisher"))); //发布人的id
        publish.setContent(request.getParameter("content"));  //发布的文字内容
        publish.setAddress(request.getParameter("address"));  //定位的地址
        publish.setType(parseInt(request.getParameter("type")));
        publish.setTime(new Date());
        int result = 0;
        if (file.length > 0) {
            //保存图片到服务器
            List<String> fileNameList = FileUploadUtil.uploadFileList(file, request);
            if (fileNameList != null && fileNameList.size() > 0) {
                try {//将帖子内容、图片信息插入数据库
                    result = publishService.sendPublic(publish, fileNameList);
                    //捕捉事务控制抛出的异常，防止程序异常崩溃
                } catch (MyThrowException e) {
                    System.out.println("异常捕捉：MyThrowException：" + e.getMessage());
                    e.printStackTrace();
                } catch (Exception e) {
                    System.out.println("异常捕捉：Exception：" + e.getMessage());
                    e.printStackTrace();
                }
            } else {
                return ResultDto.error();
            }
        } else {
            result = publishService.sendPublicNoImage(publish);
        }
        if (result == 1) {
            return new ResultDto(200, "publish_success", null);
        } else {
            return new ResultDto(200, "publish_failure", null);
        }

    }

    /**
     * 获取用户及其好友的帖子列表
     * 通过用户id，获取用户及好友的帖子信息，同时带出图片、发布人的名字、头像
     * @param request
     * @return
     */
    @ResponseBody//将返回的数据处理为json
    @RequestMapping(value = "/publishList")
    public ResultDto publishList(HttpServletRequest request) {
        int userId = Integer.parseInt(request.getParameter("userId"));
        List<PublishDto> publishDtoList = publishService.findPublishListByUserId(userId);
        if (publishDtoList != null&&publishDtoList.size()>0) {
            return new ResultDto(200, "success", publishDtoList);
        }
        return new ResultDto(200, "nodata");
    }

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
        List<PublishDto> publishList=publishService.showSomeonePublish(publisher);
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

    /**
     * 个人帖子的数量
     * @param request
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/publishNum")
    public ResultDto publisherNum(HttpServletRequest request) {
        int uId = Integer.parseInt(request.getParameter("uId"));
        int numResult= publishService.publishNum(uId);
        return new ResultDto(200,"success",numResult);
    }
}
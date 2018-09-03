package com.zhuolang.starryserver.controller;

import com.zhuolang.starryserver.dto.ResultDto;
import com.zhuolang.starryserver.entity.PublishImage;
import com.zhuolang.starryserver.exception.BaseExceptionHandleAction;
import com.zhuolang.starryserver.service.PublishImageService;
import com.zhuolang.starryserver.utils.FileUploadUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;

import java.util.List;

import static java.lang.Integer.parseInt;


/**
 * Created by HuangMingPan on 2018/8/29.
 */
@Controller
@RequestMapping("/image")
public class PublishImageController extends BaseExceptionHandleAction {


    @Autowired
    private PublishImageService publishImageService;

    /**
     * 上传帖子的图片
     *
     * @param file
     * @param request
     * @return
     */
    @ResponseBody//将返回的数据处理为json
    @RequestMapping(value = "/add")
    public ResultDto  uploadImage(MultipartFile[] file, HttpServletRequest request) {

        List<String> fileName=FileUploadUtil.uploadFileList(file,request);
        int publishId=parseInt(request.getParameter("publishId"));
        ResultDto resultDto= publishImageService.addPublishImage(publishId,fileName);
        return resultDto;
    }

    /**
     * 通过public_id删除图片表对应的数据
     * @param request
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/delete")
    public ResultDto deleteImageById(HttpServletRequest request) {

        int publishId=parseInt(request.getParameter("publishId"));
        ResultDto resultDto= publishImageService.deleteImageByPublishId(publishId);
        return resultDto;
    }

    @ResponseBody
    @RequestMapping(value = "/find")
    public ResultDto findImageById(HttpServletRequest request) {
        int publishID=parseInt(request.getParameter("publishId"));
        PublishImage publishImage = publishImageService.findImageByPublicId(publishID);
        return new ResultDto(200,"find_success", publishImage);
    }




}

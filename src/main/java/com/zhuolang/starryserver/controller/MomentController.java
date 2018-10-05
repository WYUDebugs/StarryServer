package com.zhuolang.starryserver.controller;

import com.zhuolang.starryserver.dto.ResultDto;
import com.zhuolang.starryserver.entity.Moment;
import com.zhuolang.starryserver.exception.MyThrowException;
import com.zhuolang.starryserver.service.MomentService;
import com.zhuolang.starryserver.utils.FileUploadUtil;
import com.zhuolang.starryserver.utils.TimeUtil;
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

@Controller
@RequestMapping("/moment")
public class MomentController {
    @Autowired
    private MomentService momentService;


    /**
     * 发布片段
     * @param file
     * @param request
     * @return
     */
    @ResponseBody//将返回的数据处理为json
    @RequestMapping(value = "/sendMoment")
    public ResultDto sendPublic(@RequestParam(value = "file") MultipartFile[] file, HttpServletRequest request) {
        Moment moment=new Moment();
        moment.setSender(parseInt(request.getParameter("sender"))); //发布片段者的id
        moment.setContent(request.getParameter("content"));           //发布片段的文字内容
        moment.setLocation_time(request.getParameter("locationTime")); //时间轴的时间
        moment.setSend_time(TimeUtil.dateToString(new Date())); //发布片段的时间
        moment.setBookId(parseInt(request.getParameter("bookId")));
        int result = 0;
        if (file.length > 0) {
            //保存图片到服务器
            List<String> fileNameList = FileUploadUtil.uploadFileList(file, request);
            if (fileNameList != null && fileNameList.size() > 0) {
                try {//将帖子内容、图片信息插入数据库
                    result =momentService.sendMoment(moment,fileNameList);
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
            result = momentService.sendMomentNoImage(moment);
        }
        if (result == 1) {
            return new ResultDto(200, "publish_success", null);
        } else {
            return new ResultDto(200, "publish_failure", null);
        }

    }

    /**
     * 获取片段列表
     * @param request
     * @return
     */
    @ResponseBody//将返回的数据处理为json
    @RequestMapping(value = "/momentList")
    public ResultDto momentList(HttpServletRequest request) {
        int bId = Integer.parseInt(request.getParameter("bId"));
       List<Moment> momentList=momentService.findMomentListBybId(bId);
       if (momentList != null && momentList.size() > 0) {
            return new ResultDto(200, "success", momentList);
        } else {
            return new ResultDto(200,"noDate",null);
        }

    }

    @ResponseBody//将返回的数据处理为json
    @RequestMapping(value = "/deleteMoment")
    public ResultDto deleteMoment(HttpServletRequest request) {
        int bId = Integer.parseInt(request.getParameter("bId")); //获取纪念册id，用以 更新纪念册的count
        int mId=Integer.parseInt(request.getParameter("mId"));//获取片段的id，用以删除片段
        int result=0;
        try {
            result=momentService.deleteByMomentId(mId,bId);

        }  catch (MyThrowException e) {
            System.out.println("异常捕捉：MyThrowException：" + e.getMessage());
            e.printStackTrace();
        } catch (Exception e) {
            System.out.println("异常捕捉：Exception：" + e.getMessage());
            e.printStackTrace();
        }
        if (result == 1) {
            return new ResultDto(200, "delete_success", null);
        } else {
            return new ResultDto(200, "delete_failure", null);
        }

    }

    /**
     * 获取moment详细信息
     * @param request
     * @return
     */
    @ResponseBody//将返回的数据处理为json
        @RequestMapping(value = "/ShowOneMoment")
    public ResultDto ShowOneMoment(HttpServletRequest request) {
       int mId=Integer.parseInt(request.getParameter("mId"));//获取片段的id
        return momentService.findMomentBymId(mId);
    }


}

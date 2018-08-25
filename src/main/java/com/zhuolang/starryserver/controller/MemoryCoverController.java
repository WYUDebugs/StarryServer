package com.zhuolang.starryserver.controller;

import com.zhuolang.starryserver.dto.ResultDto;
import com.zhuolang.starryserver.entity.MemoryCover;
import com.zhuolang.starryserver.exception.BaseExceptionHandleAction;
import com.zhuolang.starryserver.service.MemoryCoverService;
import com.zhuolang.starryserver.utils.FileUploadUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.util.List;

import static java.lang.Integer.parseInt;


/**
 * Created by heziqiang on 2018/8/24
 */

@Controller
@RequestMapping("/memoryCover")
public class MemoryCoverController extends BaseExceptionHandleAction {//Controller类继承BaseExceptionHandleAction这个类即可在产生异常时返回数据获取失败的异常类信息

    // controller一般只处理获取数据，将数据传到service业务层，不做复杂的数据处理，复杂的数据处理交给service业务层

    @Autowired
    private MemoryCoverService memoryCoverService;

    /**
     * 为 memoryBook 添加一张 cover
     *
     * @param file
     * @param request
     * @return
     */
    @ResponseBody//将返回的数据处理为 json
    @RequestMapping(value = "/uploadMemoryCover")
    public ResultDto uploadCover(MultipartFile file, HttpServletRequest request) {
        String fileName = FileUploadUtil.uploadFile(file, request);//上传文件

        if (fileName == null) {
            return ResultDto.error();//如果文件上传失败，报错
        } else {
            if (memoryCoverService.addMemoryCover(fileName) == 1) {
                return new ResultDto(200, "success", null);
            } else {
                return new ResultDto(200, "fail", null);
            }
        }
    }

    /**
     * 通过 id 删除 cover
     *
     * @param request
     * @param response
     * @return
     */
    @ResponseBody//将返回的数据处理为 json
    @RequestMapping(value = "/deleteMemoryCover")
    public ResultDto deleteMemoryCover(HttpServletRequest request, HttpServletResponse response) {
        int id = parseInt(request.getParameter("id"));

        if (memoryCoverService.deleteMemoryCover(id) == 1) {
            return new ResultDto(200, "success", null);
        } else {
            return new ResultDto(200, "fail", null);
        }
    }

    /**
     * 查找所有 memoryCover
     *
     * @param request
     * @param response
     * @return
     */
    @ResponseBody//将返回的数据处理为 json
    @RequestMapping(value = "/findAllMemoryCover")
    public ResultDto findAllMemoryCover(HttpServletRequest request, HttpServletResponse response) {
        List<MemoryCover> memoryCoverList = memoryCoverService.findAllMemoryCoverDESC();

        if (memoryCoverList != null && memoryCoverList.size() > 0) {
            return new ResultDto(200, "success", memoryCoverList);
        } else {
            return new ResultDto(200, "nodata", null);
        }
    }
}

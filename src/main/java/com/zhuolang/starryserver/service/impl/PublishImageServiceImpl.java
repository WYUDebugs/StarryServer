package com.zhuolang.starryserver.service.impl;

import com.zhuolang.starryserver.dao.PublishImageDao;
import com.zhuolang.starryserver.dto.ResultDto;
import com.zhuolang.starryserver.entity.PublishImage;
import com.zhuolang.starryserver.service.PublishImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;

@Service
public class PublishImageServiceImpl implements PublishImageService {
    @Autowired
    private PublishImageDao publishImageDao;

    /**
     * 上传帖子图片
     * @param publicId
     * @param path
     * @return
     */
    @Override
    public ResultDto addPublishImage(int publicId, List<String> path) {
        if (!path.isEmpty()) {
            for (int i = 0; i < path.size(); i++) {
                int result = publishImageDao.addPublishImage(publicId, path.get(i));
                if (result == 1) {
                    return new ResultDto(200, "第" + path.get(i) + "张图片上传成功");
                } else {
                    return new ResultDto(200, "第" + path.get(i) + "张图片上传失败");
                }
            }
            return new ResultDto(200,"上传成功");
        }
        return new ResultDto(200,"上传失败");
    }

    /**
     * 删除帖子的图片
     * @param publicId
     * @return
     */
    @Override
    public ResultDto deleteImageByPublishId(int publicId) {
        int result= publishImageDao.deleteImageByPublishId(publicId);
        if (result >0) {
            return new ResultDto(200, "delete_success");
        } else {
            return new ResultDto(200,"delete_failure");
        }
    }
    /**
     * 查询帖子的图片
     * @param publicId
     * @return
     */
    @Override
    public PublishImage findImageByPublicId(int publicId) {
       return publishImageDao.findImageByPublicId(publicId);
    }



}

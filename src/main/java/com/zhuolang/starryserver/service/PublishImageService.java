package com.zhuolang.starryserver.service;

import com.zhuolang.starryserver.dto.ResultDto;
import com.zhuolang.starryserver.entity.PublishImage;

import java.util.List;

public interface PublishImageService {
    /**
     * 添加图片的public_id 和 path
     * @param publicId
     * @param path
     * @return
     */
    ResultDto addPublishImage(int publicId, List<String> path);

    /**
     * 通过public_id删除图片表对应的数据
     * @param publicId
     * @return
     */
    ResultDto deleteImageByPublishId(int publicId);

    /**
     * 通过public_id 查询对应的图片
     * @param publicId
     * @return
     */
    PublishImage findImageByPublicId(int publicId);
}

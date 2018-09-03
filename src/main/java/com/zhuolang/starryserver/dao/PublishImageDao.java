package com.zhuolang.starryserver.dao;

import com.zhuolang.starryserver.entity.PublishImage;
import org.apache.ibatis.annotations.Param;

/**
 * Created by HuangMingPan on 2018/8/29.
 */
public interface PublishImageDao {
    /**
     * 添加图片信息
     * @param publicId
     * @param path
     * @return
     */
    int addPublishImage(@Param("public_id")int publicId,@Param("path")String path);

    /**
     * 通过public_id 删除图片表对应的数据
     * @param publicId
     * @return
     */

    int deleteImageByPublishId(@Param("public_id")int publicId);

    /**
     * 通过public_id 查询对应的图片
     * @param publicId
     * @return
     */
    PublishImage findImageByPublicId(@Param("public_id")int publicId);
}

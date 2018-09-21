package com.zhuolang.starryserver.dao;

import com.zhuolang.starryserver.entity.MomentImage;
import org.apache.ibatis.annotations.Param;

public interface MomentImageDao {
    int addMomentImage(@Param("momentId")int momentId, @Param("path")String path);


    int deleteImageByMId(@Param("momentId")int momentId);


    MomentImage findImageByMId(@Param("momentId")int momentId);
}

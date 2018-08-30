package com.zhuolang.starryserver.dao;

import com.zhuolang.starryserver.entity.Publish;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;


public interface PublishDao {
    int publish(@Param("publisher")int publisher, @Param("time") Date time,
                @Param("content")String content,@Param("address")String address,@Param("type")int type);

    List<Publish> showPostDESC();
}

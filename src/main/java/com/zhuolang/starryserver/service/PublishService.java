package com.zhuolang.starryserver.service;

import com.zhuolang.starryserver.dto.ResultDto;
import com.zhuolang.starryserver.entity.Publish;

import java.util.List;


public interface PublishService {
    ResultDto publish(int publisher,String content,String address,int type);

    List<Publish> showPostDESC();
}

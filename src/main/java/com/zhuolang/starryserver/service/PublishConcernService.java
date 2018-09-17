package com.zhuolang.starryserver.service;

import com.zhuolang.starryserver.dto.ResultDto;
import com.zhuolang.starryserver.entity.User;

import java.util.List;

public interface PublishConcernService {
    List<User> showConcernMans(int pId);
    ResultDto collect(int pId, int mId);
}

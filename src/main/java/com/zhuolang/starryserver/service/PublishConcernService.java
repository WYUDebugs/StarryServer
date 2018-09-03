package com.zhuolang.starryserver.service;

import com.zhuolang.starryserver.entity.User;

import java.util.List;

public interface PublishConcernService {
    List<String> concern(int pId, int mId);
}

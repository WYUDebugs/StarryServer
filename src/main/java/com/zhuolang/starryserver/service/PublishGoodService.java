package com.zhuolang.starryserver.service;

import com.zhuolang.starryserver.entity.User;

import java.util.List;
/**
 * Created by HuangMingPan on 2018/9/03.
 */

public interface PublishGoodService {
    List<String> praise(int pId, int uId);
}

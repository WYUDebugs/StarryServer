package com.zhuolang.starryserver.service;

import com.zhuolang.starryserver.dto.ResultDto;
import com.zhuolang.starryserver.entity.User;

import java.util.List;
/**
 * Created by HuangMingPan on 2018/9/03.
 */

public interface PublishGoodService {
    List<User> showPraise(int pId);
    ResultDto praise(int pId, int uId);
    ResultDto updatePraiseState(List<Integer> gdIds);
    ResultDto showUnReadPraise(int uId);
}

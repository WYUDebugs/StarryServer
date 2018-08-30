package com.zhuolang.starryserver.service;

import com.zhuolang.starryserver.dto.ResultDto;
import com.zhuolang.starryserver.entity.Good;

public interface GoodService {
    Good checkMansById(int manOfPraise);
}

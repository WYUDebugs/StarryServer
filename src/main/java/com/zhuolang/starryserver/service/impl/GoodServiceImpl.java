package com.zhuolang.starryserver.service.impl;

import com.zhuolang.starryserver.dao.GoodDao;
import com.zhuolang.starryserver.dto.ResultDto;
import com.zhuolang.starryserver.entity.Good;
import com.zhuolang.starryserver.service.GoodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class GoodServiceImpl implements GoodService {
    @Autowired
    private GoodDao goodDao;


    @Override
    public Good checkMansById(int manOfPraise) {
        return goodDao.checkManById(manOfPraise);
    }
}

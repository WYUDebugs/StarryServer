package com.zhuolang.starryserver.dao;

import com.zhuolang.starryserver.entity.Good;
import org.apache.ibatis.annotations.Param;


public interface GoodDao {
    Good checkManById(@Param("man_of_praise")int manOfPraise);
}

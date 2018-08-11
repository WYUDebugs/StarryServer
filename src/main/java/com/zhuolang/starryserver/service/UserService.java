package com.zhuolang.starryserver.service;

import com.zhuolang.starryserver.dto.ResultDto;

public interface UserService {

    ResultDto addUserByPhonePsw(String phone, String password);

}

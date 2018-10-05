package com.zhuolang.starryserver.service;

import com.zhuolang.starryserver.dto.ResultDto;
import com.zhuolang.starryserver.entity.Moment;

import java.util.List;

public interface MomentService {
    int sendMoment(Moment moment,List<String> imageList);
    int sendMomentNoImage(Moment moment);
    int deleteByMomentId(int mId,int bId);
    List<Moment> findMomentListBybId(int bId);
    ResultDto findMomentBymId(int mId);

}

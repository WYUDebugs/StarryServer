package com.zhuolang.starryserver.service;

import com.zhuolang.starryserver.entity.Moment;

import java.util.List;

public interface MomentService {
    int sendMoment(Moment moment,List<String> imageList);
    int sendMomentNoImage(Moment moment);
    List<Moment> findMomentListByOwners(int bId);
}

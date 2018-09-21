package com.zhuolang.starryserver.service.impl;

import com.zhuolang.starryserver.dao.MemoryBookDao;
import com.zhuolang.starryserver.dao.MomentDao;
import com.zhuolang.starryserver.dao.MomentImageDao;
import com.zhuolang.starryserver.dto.ResultDto;
import com.zhuolang.starryserver.entity.Moment;
import com.zhuolang.starryserver.exception.MyThrowException;
import com.zhuolang.starryserver.service.MomentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class MomentServiceImpl implements MomentService{
    @Autowired
    private MemoryBookDao memoryBookDao;
    @Autowired
    private MomentDao momentDao;
    @Autowired
    private MomentImageDao momentImageDao;



    @Transactional
    @Override
    public int sendMoment(Moment moment, List<String> imageList) {
        try {
            if (momentDao.installMoment(moment)==1) {
                int momentId = moment.getId();
                for (int i = 0; i < imageList.size(); i++) {
                    if (momentImageDao.addMomentImage(momentId,imageList.get(i))==1){

                    } else {
                        throw new MyThrowException("install_image_failure");
                    }
                }
            } else {
                throw new MyThrowException("install_moment_failure");
            }
        } catch (MyThrowException e) {
            throw e;
        }
        return 1;
    }

    @Override
    public int sendMomentNoImage(Moment moment) {
        return momentDao.installMoment(moment);
    }


    @Override
    public List<Moment> findMomentListByOwners(int bId) {
         List<Integer> ownerList =memoryBookDao.findOwners(bId);
        if (ownerList != null && ownerList.size() > 0) {
            return momentDao.findMomentListByOwners(ownerList);
        } else {
            return null;
        }
    }
}

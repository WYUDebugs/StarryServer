package com.zhuolang.starryserver.service.impl;

import com.zhuolang.starryserver.dao.MemoryBookDao;
import com.zhuolang.starryserver.dao.MomentDao;
import com.zhuolang.starryserver.dao.MomentImageDao;
import com.zhuolang.starryserver.dto.ResultDto;
import com.zhuolang.starryserver.entity.Moment;
import com.zhuolang.starryserver.exception.MyThrowException;
import com.zhuolang.starryserver.service.MomentService;
import org.apache.ibatis.annotations.Param;
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
                int bookId=moment.getBookId();
                for (int i = 0; i < imageList.size(); i++) {
                    if (momentImageDao.addMomentImage(momentId,imageList.get(i))==1){
                        if (memoryBookDao.updateMomentCount(bookId) == 1) {

                        } else {
                            throw new MyThrowException("update_momentCount_failure");
                        }
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

    @Transactional
    @Override
    public int sendMomentNoImage(Moment moment) {
        int bookId=moment.getBookId();
        try {
            if (momentDao.installMoment(moment) == 1) {
                if (memoryBookDao.updateMomentCount(bookId) == 1) {
                } else {
                    throw new MyThrowException("update_momentCount_failure");
                }
            } else {
                throw new MyThrowException("install_moment_failure");
            }
        } catch (MyThrowException e) {
            throw e;
        }
        return 1;
    }

    @Transactional
    @Override
    public int deleteByMomentId(int mId,int bId) {
       int result=0;
        try {
            if (momentDao.deleteByMomentId(mId) == 1) {
                if (memoryBookDao.updateMomentCount2(bId) == 1) {
                    result = 1;
                } else {
                    throw new MyThrowException("update_momentCount_failure");
                }
            } else {
                throw new MyThrowException("delete_comment_failure");
            }
        } catch (MyThrowException e) {
            throw e;
        }
        return result;
    }


    @Override
    public List<Moment> findMomentListBybId(int bId) {
       List<Moment> momentList=momentDao.findMomentListBybId(bId);
        if (momentList != null && momentList.size() > 0) {
            return momentList;
        } else {
            return null;
        }
    }

    @Override
    public ResultDto findMomentBymId(int mId) {
        Moment moment=momentDao.findMomentBymId(mId);
        if (moment != null) {
            return new ResultDto(200, "success", moment);
        } else {
            return new ResultDto(200,"noDate",null);
        }

    }
}

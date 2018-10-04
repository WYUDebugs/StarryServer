package com.zhuolang.starryserver.service.impl;

import com.zhuolang.starryserver.dao.PublishGoodDao;
import com.zhuolang.starryserver.dao.PublishDao;
import com.zhuolang.starryserver.dao.UserDao;
import com.zhuolang.starryserver.dto.ResultDto;
import com.zhuolang.starryserver.entity.PublishGood;
import com.zhuolang.starryserver.entity.Publish;
import com.zhuolang.starryserver.entity.PublishGoodDto;
import com.zhuolang.starryserver.entity.User;
import com.zhuolang.starryserver.service.PublishGoodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
/**
 * Created by HuangMingPan on 2018/9/03.
 */

@Service
public class PublishGoodServiceImpl implements PublishGoodService {
    @Autowired
    private PublishGoodDao goodDao;
    @Autowired
    private PublishDao publishDao;
    @Autowired
    private UserDao userDao;

    @Override
    public List<User> showPraise(int pId) {
        //通过帖子id获取点赞该帖子的所有人的id
        List<Integer> mansId=goodDao.findMansIdByPublishId(pId);
        //通过userId获取点赞人的所有信息
        List<User> usersList=new ArrayList<>();
        for (int manId : mansId) {
            User user=userDao.findUserById(manId);
            usersList.add(user);
        }
        return usersList;

    }

    @Override
    public ResultDto praise(int pId, int uId) {
        //通过pId和uId判断此用户点赞该帖子的状态,是否已经点过赞
        List<PublishGood> publishGoods =goodDao.checkManByAIdPId(pId,uId);
        //通过帖子id获取帖子的信息
         Integer num=publishDao.findPublishById(pId);
         int praiseNum = 0;
        if (num != null) {
            praiseNum= num.intValue();
        } else {
             new ResultDto(200,"failure",null);
        }
        if (publishGoods != null && publishGoods.size() > 0) {
            //当已经点赞过
            PublishGood publishGood = publishGoods.get(0);
            //通过点赞id删除点赞的信息,也就是客户端的取消赞功能
            goodDao.deleteById(publishGood.getGdId());
            //更新帖子表，点赞数-1
            int result=publishDao.updatePublishById(praiseNum-1,pId);
            if (result == 1) {
                return new ResultDto(200, "success", null);
            } else {
                return new ResultDto(200, "failure", null);
            }
        } else {
            //当还没有点赞过,插入点赞信息,帖子表点赞数+1
            goodDao.addPraiseMan(pId,uId,new Date());
            int result2=publishDao.updatePublishById(praiseNum+1,pId);
            if (result2 == 1) {
                return new ResultDto(200, "success", null);
            } else {
                return new ResultDto(200, "failure", null);
            }
        }

    }

    /**
     * 将未读点赞信息标记为已读
     * @param gdIds
     * @return
     */
    @Override
    public ResultDto updatePraiseState(List<Integer> gdIds) {
        int result=goodDao.updatePraiseState(gdIds);
        if (result > 0) {
            return new ResultDto(200, "success", null);
        } else {
            return new ResultDto(200,"failure",null);
        }
    }

    @Override
    public ResultDto showUnReadPraise(int uId) {
        List<Integer> pIds=publishDao.getPublishId(uId);
        List<PublishGoodDto> unReadPraise=goodDao.showUnReadPraise(uId,pIds);
        if (unReadPraise != null && unReadPraise.size() > 0) {
            return new ResultDto(200, "success", unReadPraise);
        } else {
            return new ResultDto(20,"没有新的点赞",null);
        }

    }
}

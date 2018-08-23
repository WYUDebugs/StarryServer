package com.zhuolang.starryserver.service.impl;

import com.zhuolang.starryserver.dao.FriendDao;
import com.zhuolang.starryserver.dao.UserDao;
import com.zhuolang.starryserver.dto.ResultDto;
import com.zhuolang.starryserver.entity.Friend;
import com.zhuolang.starryserver.entity.User;
import com.zhuolang.starryserver.service.FriendService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service  //添加UserService的test时，选中类名UserServiceImpl，右键go to->test-创建->选择Junit4,选择添加的测试方法，finish
public class FriendServiceImpl implements FriendService {

    //注入Dao实现类依赖
    //    @Resource
    @Autowired
    private FriendDao friendDao;

    @Autowired
    private UserDao userDao;

    //也可以注入别的dao,例如对应post_tab表的PostDao，这样就可以根据需求整合多表的增删改查
    /**
     * 通过id添加好友
     *
     *
     * @param user_id
     * @param friend_id
     * @param
     * @return
     */
    @Override
    public ResultDto addFriendById(int user_id, int friend_id) {
        // 通过id获取用户昵称
        User toFriendUser = userDao.findUserById(friend_id);
        User fromFriendUser = userDao.findUserById(user_id);
        String toFriendName = toFriendUser.getName();
        String fromFriendName = fromFriendUser.getName();

        if (friendDao.addFriendById(user_id, friend_id, toFriendName ,new Date()) == 1
        && friendDao.addFriendById(friend_id, user_id,fromFriendName,new Date()) == 1) {
            return new ResultDto(200, "add_success", null);
        }else {
            return new ResultDto(200, "add_failure", null);
        }
    }

    /**
     * 通过friend_id查找friend(好友)
     *
     * @param user_id
     * @param friend_id
     * @return
     */
    @Override
    public Friend findFriendById(int user_id, int friend_id) {
        return friendDao.findFriendById(user_id,friend_id);
    }

    /**
     * 通过friend_id修改friend_name(好友昵称)
     *
     * @param user_id
     * @param friend_id
     * @return 成功则返回1，否则返回0
     */
    @Override
    public int changeFriendnameById(int user_id, int friend_id,String friend_name) {
        return friendDao.changeFriendnameById(user_id,friend_id,friend_name);
    }

    /**
     * 通过friend_id删除好友
     *
     * @param user_id
     * @param friend_id
     * @return 成功则返回1，否则返回0
     */
    @Override
    public int deleteFriendById(int user_id, int friend_id) {
       if (friendDao.deleteFriendById(user_id,friend_id) == 1
       && friendDao.deleteFriendById(friend_id,user_id) == 1){
           return 1;
       }else {
           return 0;
       }

    }

    /**
     * 通过user_id列表展示好友
     *
     * @param user_id
     */
    @Override
    public List<Friend> findAllFriendASC(int user_id) {
        return friendDao.findAllFriendASC(user_id);
    }
}

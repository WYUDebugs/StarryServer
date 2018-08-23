package com.zhuolang.starryserver.service;

import com.zhuolang.starryserver.dto.ResultDto;
import com.zhuolang.starryserver.entity.Friend;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface FriendService {
    /**
     * 通过id添加好友
     *
     * @param user_id
     * @param friend_id
     * @param
     * @return 成功返回1，否则返回0
     */
    int addFriendById(int user_id, int friend_id);

    /**
     * 通过friend_id查找friend(好友)
     *
     * @param user_id
     * @param friend_id
     * @return
     */
    Friend findFriendById(int user_id,int friend_id);

    /**
     * 通过friend_id修改friend_name(好友昵称)
     *
     * @param user_id
     * @param friend_id
     * @return 成功则返回1，否则返回0
     */
    int changeFriendnameById(int user_id ,int friend_id,String friend_name);

    /**
     * 通过friend_id删除好友
     *
     * @param user_id
     * @param friend_id
     * @return 成功则返回1，否则返回0
     */
    int deleteFriendById(int user_id, int friend_id);

    /**
     * 通过user_id列表展示好友
     *
     * @param user_id
     */
    List<Friend> findAllFriendASC(int user_id);
}

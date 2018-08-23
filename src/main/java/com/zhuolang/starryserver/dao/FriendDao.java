package com.zhuolang.starryserver.dao;

import com.zhuolang.starryserver.entity.Friend;
import com.zhuolang.starryserver.entity.User;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

public interface FriendDao {

    /**
     * 通过friend_id添加好友（双向）
     *
     * @param user_id
     * @param friend_id
     * @param friend_name
     * @return 成功返回1，否则返回0
     */
    int addFriendById(@Param("user_id")int friend_id ,@Param("friend_id") int user_id
            ,@Param("friend_name")String friend_name ,@Param("add_time") Date add_time);



    /**
     * 通过friend_id查找friend(好友)
     *
     * @param user_id
     * @param friend_id
     * @return
     */
    Friend findFriendById(@Param("user_id")int user_id,@Param("friend_id")int friend_id);

    /**
     * 通过friend_id修改friend_name(好友昵称)
     *
     * @param user_id
     * @param friend_id
     * @return 成功则返回1，否则返回0
     */
    int changeFriendnameById(@Param("user_id") int user_id , @Param("friend_id") int friend_id
            , @Param("friend_name")String friend_name);

    /**
     * 通过friend_id删除好友
     *
     * @param user_id
     * @param friend_id
     * @return 成功则返回1，否则返回0
     */
    int deleteFriendById(@Param("user_id")int user_id, @Param("friend_id")int friend_id);


    /**
     * 通过user_id列表展示好友
     *
     * @param user_id
     */
    List<Friend> findAllFriendASC(@Param("user_id") int user_id);
}

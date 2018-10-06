package com.zhuolang.starryserver.dao;

import com.zhuolang.starryserver.entity.MemoryFriend;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

public interface MemoryFriendDao {

    /**
     * 通过friendId添加纪念册好友
     *
     * @param friend_id
     * @param memory_book_id
     * @param add_time
     * @return 添加成功返回1，否则返回0
     */
    int addMemoryFriend(@Param("friend_id")int friend_id,@Param("memory_book_id")int memory_book_id
    ,@Param("add_time") Date add_time);

    /**
     * 通过id删除纪念册好友
     *
     * @param
     * @return 删除成功返回1，否则返回0
     */
    int deleteMemoryFriend(@Param("fId")int fId,@Param("bId")int bId);

    /**
     * 通过memory_book_id展示该纪念册好友
     *
     * @param memory_book_id
     */
    List<MemoryFriend> findMemoryFriend(@Param("memory_book_id")int memory_book_id);

    MemoryFriend checkFriend(@Param("fId")int fId,@Param("bId")int bId);

    int findBookId(@Param("fId")int fId);

}

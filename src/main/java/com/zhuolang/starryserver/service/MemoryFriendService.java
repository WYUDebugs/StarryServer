package com.zhuolang.starryserver.service;

import com.zhuolang.starryserver.entity.MemoryFriend;

import java.util.List;

public interface MemoryFriendService {

    /**
     * 通过friendId添加纪念册好友
     *
     * @param friend_id
     * @param memory_book_id
     * @return 添加成功返回1，否则返回0
     */
    int addMemoryFriend(int friend_id,int memory_book_id);

    /**
     * 通过id删除纪念册好友
     *
     * @param id
     * @return 删除成功返回1，否则返回0
     */
    int deleteMemoryFriend(int id);

    /**
     * 通过memory_book_id展示该纪念册好友
     *
     * @param memory_book_id
     */
    List<MemoryFriend> findMemoryFriend(int memory_book_id);
}

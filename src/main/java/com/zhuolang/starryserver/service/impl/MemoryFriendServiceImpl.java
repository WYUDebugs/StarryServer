package com.zhuolang.starryserver.service.impl;

import com.zhuolang.starryserver.dao.MemoryFriendDao;
import com.zhuolang.starryserver.entity.MemoryFriend;
import com.zhuolang.starryserver.service.MemoryFriendService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service  //添加UserService的test时，选中类名UserServiceImpl，右键go to->test-创建->选择Junit4,选择添加的测试方法，finish
public class MemoryFriendServiceImpl implements MemoryFriendService {
    @Autowired
    private MemoryFriendDao memoryFriendDao;

    /**
     * 通过friendId添加纪念册好友
     *
     * @param friend_id
     * @param memory_book_id
     * @return 添加成功返回1，否则返回0
     */
    @Override
    public int addMemoryFriend(int friend_id, int memory_book_id) {
        return memoryFriendDao.addMemoryFriend(friend_id,memory_book_id,new Date());
    }

    /**
     * 通过id删除纪念册好友
     *
     * @param id
     * @return 删除成功返回1，否则返回0
     */
    @Override
    public int deleteMemoryFriend(int id) {
        return memoryFriendDao.deleteMemoryFriend(id);
    }

    /**
     * 通过memory_book_id展示该纪念册好友
     *
     * @param memory_book_id
     */
    @Override
    public List<MemoryFriend> findMemoryFriend(int memory_book_id) {
        return memoryFriendDao.findMemoryFriend(memory_book_id);
    }
}
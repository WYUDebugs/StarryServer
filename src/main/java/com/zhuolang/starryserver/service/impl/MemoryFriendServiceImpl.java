package com.zhuolang.starryserver.service.impl;

import com.zhuolang.starryserver.dao.MemoryBookDao;
import com.zhuolang.starryserver.dao.MemoryFriendDao;
import com.zhuolang.starryserver.entity.MemoryFriend;
import com.zhuolang.starryserver.exception.MyThrowException;
import com.zhuolang.starryserver.service.MemoryFriendService;
import com.zhuolang.starryserver.utils.TimeUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service  //添加UserService的test时，选中类名UserServiceImpl，右键go to->test-创建->选择Junit4,选择添加的测试方法，finish
public class MemoryFriendServiceImpl implements MemoryFriendService {
    @Autowired
    private MemoryFriendDao memoryFriendDao;
    @Autowired
    private MemoryBookDao memoryBookDao;

    /**
     * 通过friendId添加纪念册好友
     * 事务更新成员的数量
     * @param friend_id
     * @param memory_book_id
     * @return 添加成功返回1，否则返回0
     */
    @Transactional
    @Override
    public int addMemoryFriend(int friend_id, int memory_book_id) {
//        return memoryFriendDao.addMemoryFriend(friend_id,memory_book_id,new Date());
        try {
            MemoryFriend friend=memoryFriendDao.checkFriend(friend_id,memory_book_id);
            if (friend == null) {
                if (memoryFriendDao.addMemoryFriend(friend_id, memory_book_id, new Date()) == 1) {
                    if (memoryBookDao.updateFriendCount(memory_book_id) == 1) {

                    } else {
                        throw new MyThrowException("update_friendCount_fail");
                    }
                } else {
                    throw new MyThrowException("add_friend_fail");
                }
            } else {
                throw new MyThrowException("friend_exit");
            }
        }catch (MyThrowException e) {
            throw e;
        }
        return 1;
    }

    /**
     * 通过id删除纪念册好友
     * 事务更新book表friend Count
     * @param
     * @return 删除成功返回1，否则返回0
     */
    @Transactional
    @Override
    public int deleteMemoryFriend(int fId,int bId) {
//        return memoryFriendDao.deleteMemoryFriend(id);
        try {
            if (memoryFriendDao.deleteMemoryFriend(fId, bId) == 1) {
                if (memoryBookDao.updateFriendCount2(bId) == 1) {
                } else {
                    throw new MyThrowException("update_count_fail");
                }
            } else {
                throw new MyThrowException("delete_failure");
            }
        } catch (MyThrowException e) {
            throw e;
        }
        return 1;
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

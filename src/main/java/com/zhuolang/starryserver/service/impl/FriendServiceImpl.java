package com.zhuolang.starryserver.service.impl;

import com.zhuolang.starryserver.dao.FriendDao;
import com.zhuolang.starryserver.dao.UserDao;
import com.zhuolang.starryserver.dto.ResultDto;
import com.zhuolang.starryserver.entity.Friend;
import com.zhuolang.starryserver.entity.User;
import com.zhuolang.starryserver.exception.MyThrowException;
import com.zhuolang.starryserver.service.FriendService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
     * @param user_id
     * @param friend_id
     * @param
     * @return
     */
    @Transactional //事务注解标签
    @Override
    public int addFriendById(int user_id, int friend_id) {
        // 通过id获取用户昵称
        User toFriendUser = userDao.findUserById(friend_id);
        User fromFriendUser = userDao.findUserById(user_id);
        String toFriendName = toFriendUser.getName();
        String fromFriendName = fromFriendUser.getName();

        try {
            if (friendDao.addFriendById(user_id, friend_id, toFriendName ,new Date()) == 1) {
                if (friendDao.addFriendById(friend_id, user_id,fromFriendName,new Date()) == 1){
                    return 1;
                }else {
                    // 手动判断哪一步的增删改操作失败，则手动抛出异常，事务回滚，
                    // 所有的增删改操作回退到未操作之前，保证数据一致性
                    // 如果未有异常，则增删改操作正常执行
                    throw new MyThrowException("add_failure");
                }
            }else {
                throw new MyThrowException("add_failure");
            }
        }catch (MyThrowException e) {
            //System.out.println("e========================" + e.getMessage());
            throw e;
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
        return friendDao.findFriendById(user_id, friend_id);
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
     *   使用注解控制事务方法的优点:
     *   * 1.开发团队达成一致约定，明确标注事务方法的编程风格
     *   * 2.保证事务方法的执行时间尽可能短，不要穿插其他网络操作RPC/HTTP请求或者剥离到事务方法外部
     *   * 3.不是所有的方法都需要事务，如只有一条修改操作、只读操作不要事务控制
     *   * 4.多条数据增删改操作时使用事务控制，保证数据一致性
     */
    @Transactional //事务注解标签
    @Override
    public int deleteFriendById(int user_id, int friend_id) {
//       if (friendDao.deleteFriendById(user_id,friend_id) == 1
//       && friendDao.deleteFriendById(friend_id,user_id) == 1){
//           return new ResultDto(200,"delete_success",null);
//       }else {
//           return new ResultDto(200,"delete_fail",null);
//       }
        try {
            if (friendDao.deleteFriendById(user_id, friend_id) == 1) {
                if (friendDao.deleteFriendById(friend_id, user_id) == 1) {
                    return 1;
                } else {
                    // 手动判断哪一步的增删改操作失败，则手动抛出异常，事务回滚，
                    // 所有的增删改操作回退到未操作之前，保证数据一致性
                    // 如果未有异常，则增删改操作正常执行
                    throw new MyThrowException("delete_failure");
                }
            } else {
                throw new MyThrowException("delete_failure");
            }
        } catch (MyThrowException e) {
            //System.out.println("e========================" + e.getMessage());
            throw e;
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

    /**
     * create by HuangMingPan
     * 通过user_id获取friend_id
     * 展示朋友圈帖子时会用到
     * @param user_id
     * @return
     */
    @Override
    public List<Integer> findFriendIdByUseId(int user_id) {
        return  friendDao.findFriendIdByUseId(user_id);
    }
}

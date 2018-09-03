package com.zhuolang.starryserver.controller;


import com.zhuolang.starryserver.dto.ResultDto;
import com.zhuolang.starryserver.entity.Friend;
import com.zhuolang.starryserver.exception.BaseExceptionHandleAction;
import com.zhuolang.starryserver.exception.MyThrowException;
import com.zhuolang.starryserver.service.FriendService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

@Controller
@RequestMapping("/friend")//Controller类继承BaseExceptionHandleAction这个类即可在产生异常时返回数据获取失败的异常类信息

public class FriendController extends BaseExceptionHandleAction {
    @Autowired
    private FriendService friendService;

    /**
     * 通过friend_id添加好友
     *
     * @param request
     * @return
     */
    @ResponseBody//将返回的数据处理为json
    @RequestMapping(value = "/addFriend")
    public ResultDto addFriend(HttpServletRequest request){
        int userId = Integer.parseInt(request.getParameter("userId"));
        int friendId = Integer.parseInt(request.getParameter("friendId"));  //传入参数，并将字符参数转为整型

        int result = 0;
        try{ //捕捉事务控制抛出的异常，防止程序异常崩溃
            if (friendService.findFriendById(userId,friendId) == null) {
                result = friendService.addFriendById(userId, friendId);
            }else {
                result = 2;
            }
        }catch (MyThrowException e){
            System.out.println("异常捕捉：MyThrowException："+e.getMessage());
            e.printStackTrace();
        }catch (Exception e){
            System.out.println("异常捕捉：Exception："+e.getMessage());
            e.printStackTrace();
        }

        if (result == 1){
            return new ResultDto(200,"add_success",null);
        }else if (result == 0){
            return new ResultDto(200,"add_fail",null);
        }else {
            return new ResultDto(200,"friend exist",null);
        }

    }

    /**
     * 通过friend_id找到friend
     *
     * @param request
     * @return
     */
    @ResponseBody//将返回的数据处理为json
    @RequestMapping(value = "/findFriend")
    public ResultDto findFriend(HttpServletRequest request) {
        int userId = Integer.parseInt(request.getParameter("userId"));
        int friendId = Integer.parseInt(request.getParameter("friendId"));  //传入参数，并将字符参数转为整型

        Friend friend = friendService.findFriendById(userId,friendId);

        if(friend != null) {
            return new ResultDto(200, "find_success", friend);
        }else{
            return new ResultDto(200,"find_fail",null);
        }
    }


    /**
     * 通过friend_id修改friend_name(好友昵称)
     *
     * @param request
     * @return
     */
    @ResponseBody//将返回的数据处理为json
    @RequestMapping(value = "/changeFriendname")
    public ResultDto changeFriendname(HttpServletRequest request){
        int userId = Integer.parseInt(request.getParameter("userId"));
        int friendId = Integer.parseInt(request.getParameter("friendId"));  //传入参数，并将字符参数转为整型
        String friendName = request.getParameter("friendName");
        if (friendService.changeFriendnameById(userId,friendId,friendName) == 1){
            return new ResultDto(200,"change_success",null);
        }else {
            return new ResultDto(200,"change_fail",null);
        }
    }

    /**
     * 通过friend_id删除friend
     *
     * @param request
     * @return
     */
    @ResponseBody//将返回的数据处理为json
    @RequestMapping(value = "/deleteFriend")
    public ResultDto deleteFriend(HttpServletRequest request){
        int userId = Integer.parseInt(request.getParameter("userId"));
        int friendId = Integer.parseInt(request.getParameter("friendId"));  //传入参数，并将字符参数转为整型

        int result = 0;
        try {//普通用户注册接口
            result = friendService.deleteFriendById(userId, friendId);
        //捕捉事务控制抛出的异常，防止程序异常崩溃
        } catch (MyThrowException e) {
            System.out.println("异常捕捉：MyThrowException："+e.getMessage());
            e.printStackTrace();
        } catch (Exception e) {
            System.out.println("异常捕捉：Exception："+e.getMessage());
            e.printStackTrace();
        }
        if (result == 1){
            return new ResultDto(200,"delete_success",null);
        }else {
            return new ResultDto(200,"delete_fail",null);
        }
    }

    /**
     * 通过user_id列表展示好友
     *
     * @param request
     * @return
     */
    @ResponseBody//将返回的数据处理为json
    @RequestMapping(value = "/findAllFriend")
    public ResultDto findAllFriend(HttpServletRequest request){
        int userId = Integer.parseInt(request.getParameter("userId"));  //传入参数，并将字符参数转为整型

        List<Friend> friendList = friendService.findAllFriendASC(userId);

        if (friendList != null && friendList.size() > 0){
            return new ResultDto(200,"success",friendList);
        }else {
            return new ResultDto(200,"fail",null);
        }
    }

    /**
     * create by HuangMingPan
     * 通过userID获取friendId
     * 朋友圈展示帖子时会用到
     * @param request
     * @return
     */
    @ResponseBody//将返回的数据处理为json
    @RequestMapping(value = "/findFriendId")
    public ResultDto findFriendIdByUserID(HttpServletRequest request) {
        int userId=Integer.parseInt(request.getParameter("userId"));
        List<Integer> friendIdList=friendService.findFriendIdByUseId(userId);
        if (friendIdList != null && friendIdList.size() > 0){
            return new ResultDto(200,"success",friendIdList);
        }else {
            return new ResultDto(200,"fail",null);
        }
    }

}

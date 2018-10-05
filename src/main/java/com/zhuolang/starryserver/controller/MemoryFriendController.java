package com.zhuolang.starryserver.controller;


import com.zhuolang.starryserver.dao.MemoryFriendDao;
import com.zhuolang.starryserver.dto.ResultDto;
import com.zhuolang.starryserver.entity.MemoryFriend;
import com.zhuolang.starryserver.exception.BaseExceptionHandleAction;
import com.zhuolang.starryserver.exception.MyThrowException;
import com.zhuolang.starryserver.service.MemoryFriendService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequestMapping("/memoryFriend")//Controller类继承BaseExceptionHandleAction这个类即可在产生异常时返回数据获取失败的异常类信息

public class MemoryFriendController extends BaseExceptionHandleAction {
    //这里写的每个方法都要注释好是干什么的，有复杂的逻辑处理关系的也要注释好，便于别人读懂你的代码
    //PS:controller一般只处理获取数据，将数据传到service业务层，不做复杂的数据处理，复杂的数据处理交给service业务层

    //注入Service实现类依赖，可注入多个Service依赖
    @Autowired
    private MemoryFriendService memoryFriendService;
    @Autowired
    private MemoryFriendDao memoryFriendDao;


    /**
     * 通过friendId添加纪念册好友
     *
     * @param request
     * @return
     */
    @ResponseBody//将数据返回为json
    @RequestMapping(value = "/addMemoryFriend")

    public ResultDto addMemoryFriend(HttpServletRequest request){
        int friendId = Integer.parseInt(request.getParameter("friendId"));
        int memoryBookId = Integer.parseInt(request.getParameter("memoryBookId"));
        int result=0;
        try {
            result=memoryFriendService.addMemoryFriend(friendId,memoryBookId);
        }catch (MyThrowException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (result == 1) {
            return new ResultDto(200, "add_success", null);
        } else {
            return new ResultDto(200, "add_failure", null);
        }
  /*      MemoryFriend friend=memoryFriendDao.checkFriend(friendId,memoryBookId);
        if (friend != null) {
            return new ResultDto(200, "addMemoryFriend_fail", null);
        } else {
            int result=memoryFriendService.addMemoryFriend(friendId,memoryBookId);
            if (result == 1){
                return new ResultDto(200,"addMemoryFriend_success",null);
            }else {
                return new ResultDto(200,"addMemoryFriend_fail",null);
            }
        }*/

    }

    /**
     * 通过id删除纪念册好友
     *
     * @param request
     * @return
     */
    @ResponseBody//将数据返回为json
    @RequestMapping(value = "/deleteMemoryFriend")
    public ResultDto deleteMemoryFriend(HttpServletRequest request){
        int id = Integer.parseInt(request.getParameter("id"));

        if (memoryFriendService.deleteMemoryFriend(id) == 1){
            return new ResultDto(200,"deleteMemoryFriend_success",null);
        }else {
            return new ResultDto(200,"deleteMemoryFriend_fail",null);
        }
    }

    /**
     * 通过memory_book_id展示该纪念册好友
     *
     * @param request
     */
    @ResponseBody//将数据返回为json
    @RequestMapping(value = "/findMemoryFriend")
    public ResultDto findMemoryFriend(HttpServletRequest request){
        int memoryBookId = Integer.parseInt(request.getParameter("memoryBookId"));
        List<MemoryFriend> memoryFriendList = memoryFriendService.findMemoryFriend(memoryBookId);

        if (memoryFriendList != null && memoryFriendList.size() > 0){
            return new ResultDto(200,"find_success",memoryFriendList);
        }else {
            return new ResultDto(200,"find_fail",null);
        }
    }

}

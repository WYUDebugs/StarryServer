package com.zhuolang.starryserver.controller;

import com.zhuolang.starryserver.dto.ResultDto;
import com.zhuolang.starryserver.exception.BaseExceptionHandleAction;
import com.zhuolang.starryserver.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by wunaifu on 2018/7/28.
 */
//tomcat配置好的项目地址是 + controller配好的RequestMapping + controller里面的接口方法配置好的RequestMapping
//    就是网络请求时的地址，即访问controller中某方法的网络地址URL
//    例如：http://localhost:8080/user/allUser --》就访问了findAllUserDESC（）方法
@Controller
@RequestMapping("/user")//Controller类继承BaseExceptionHandleAction这个类即可在产生异常时返回数据获取失败的异常类信息
public class UserController extends BaseExceptionHandleAction {

    //这里写的每个方法都要注释好是干什么的，有复杂的逻辑处理关系的也要注释好，便于别人读懂你的代码
    //PS:controller一般只处理获取数据，将数据传到service业务层，不做复杂的数据处理，复杂的数据处理交给service业务层

    //注入Service实现类依赖，可注入多个Service依赖
    @Autowired
    private UserService userService;

    /**
     * 用户注册接口
     *
     * @param request
     * @param response
     * @return
     */
    @ResponseBody//将返回的数据处理为json
    @RequestMapping(value = "/register")
    //@RequestMapping(value = "/findUserByPhone",method = RequestMethod.POST)//可以指定请求方式,如果不指定，默认post，get都可以
    public ResultDto registerUser(HttpServletRequest request, HttpServletResponse response) {

        //request.getParameter("phone")就是APP端传过来的请求参数
        String phone = request.getParameter("phone");
        String psw = request.getParameter("password");

        //获取到phone和psw后直接传数据给service层处理，controller一般只处理简单的数据传输操作
        ResultDto resultDto = userService.addUserByPhonePsw(phone, psw);
        return resultDto;
    }

}

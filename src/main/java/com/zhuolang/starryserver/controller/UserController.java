package com.zhuolang.starryserver.controller;

import com.zhuolang.starryserver.dto.ResultDto;
import com.zhuolang.starryserver.entity.User;
import com.zhuolang.starryserver.exception.BaseExceptionHandleAction;
import com.zhuolang.starryserver.service.UserService;
import com.zhuolang.starryserver.utils.FileUploadUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

import static java.lang.Integer.parseInt;

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
        String psw = "123456";//如果不传密码参数过来，则密码默认为123456
        if (request.getParameter("password") != null) {
            psw = request.getParameter("password");
        }

        //获取到phone和psw后直接传数据给service层处理，controller一般只处理简单的数据传输操作
        ResultDto resultDto = userService.addUserByPhonePsw(phone, psw);
        return resultDto;
    }
    /**
     * 用户登陆接口
     *
     * @param request
     * @return
     */
    @ResponseBody//将返回的数据处理为json
    @RequestMapping(value = "/Login")
    public ResultDto checkPassword(HttpServletRequest request) {

        //request.getParameter("phone")就是APP端传过来的请求参数
        String phone = request.getParameter("phone");
        String psw = request.getParameter("password");

        ResultDto resultDto = userService.checkPassword(phone,psw);

        return resultDto;
    }


    /**
     * 通过phone查找用户的信息
     *
     * @param request
     * @param response
     * @return
     * @throws IOException 这些参数就是APP那边请求的参数  HttpServletResponse是用来返回数据的，不是APP那边请求的参数，这里暂时用不到
     */
    @ResponseBody//将返回的数据处理为json
    @RequestMapping(value = "/findUserByPhone")
    //@RequestMapping(value = "/findUserByPhone",method = RequestMethod.POST)//可以指定请求方式,如果不指定，默认post，get都可以
    public ResultDto findUserByPhone(HttpServletRequest request, HttpServletResponse response) {
        String phone = request.getParameter("phone");
        User user = userService.findUserByPhone(phone);

        if (user == null) {
            //ResultDto返回数据的封装类，参数使用规则可自定义
            //例：
            // stauts:状态返回码，200：URL访问请求成功，并成功返回数据；500：URL访问请求成功但内部程序出错
            // msg：信息提示
            // data：需要的数据
            return new ResultDto(200, "nodata", null);
        } else {
            return new ResultDto(200, "success", user);
        }
    }

    /**
     * 通过id查找用户的信息
     *
     * @param request
     * @param response
     * @return
     * @throws IOException 这些参数就是APP那边请求的参数  HttpServletResponse是用来返回数据的，不是APP那边请求的参数，这里暂时用不到
     */
    @ResponseBody//将返回的数据处理为json
    @RequestMapping(value = "/findUserById")
    //@RequestMapping(value = "/findUserById",method = RequestMethod.POST)//可以指定请求方式,如果不指定，默认post，get都可以
    public ResultDto findUserById(HttpServletRequest request, HttpServletResponse response){
        int id = parseInt(request.getParameter("id"));   //用parseInt()方法将字符串转换成int数据(！！小心！！)
        User user = userService.findUserById(id);

        if (user == null) {
            //ResultDto返回数据的封装类，参数使用规则可自定义
            //例：
            // stauts:状态返回码，200：URL访问请求成功，并成功返回数据；500：URL访问请求成功但内部程序出错
            // msg：信息提示
            // data：需要的数据
            return new ResultDto(200, "nodata", null);
        } else {
            return new ResultDto(200, "success", user);
        }
    }

    /**
     *
     * 查找显示所有用户数据
     *
     * @param request
     * @param response
     * @return
     * @throws IOException 这些参数就是APP那边请求的参数  HttpServletResponse是用来返回数据的，不是APP那边请求的参数，这里暂时用不到
     */
    @ResponseBody//将返回的数据处理为json
    @RequestMapping(value = "/findAllUser")
    public ResultDto findAllUser(HttpServletRequest request, HttpServletResponse response) {

        List<User> userList = userService.findAllUserDESC();

        if (userList != null && userList.size() > 0) {
            //ResultDto返回数据的封装类，参数使用规则可自定义
            //例：
            // stauts:状态返回码，200：URL访问请求成功，并成功返回数据；500：URL访问请求成功但内部程序出错
            // msg：信息提示
            // data：需要的数据
            return new ResultDto(200, "success", userList);
        } else {
            return new ResultDto(200, "nodata", null);
        }
    }

    /**
     * 通过id和password更改phone
     *
     * @param request
     * @param response
     * @return
     * @throws IOException 这些参数就是APP那边请求的参数  HttpServletResponse是用来返回数据的，不是APP那边请求的参数，这里暂时用不到
     */
    @ResponseBody//将返回的数据处理为json
    @RequestMapping(value = "/changePhoneById")
    public ResultDto changePhoneById(HttpServletRequest request, HttpServletResponse response){
        //request.getParameter("xxx")就是APP端传过来的请求参数
        int id = parseInt(request.getParameter("id"));   //用parseInt()方法将字符串转换成int数据(！！小心！！)
        String password = request.getParameter("password");
        String phone = request.getParameter("phone");
        if(userService.changePhoneById(id , password , phone) == 1){
            return new ResultDto(200,"success",null);
        }else {
            return new ResultDto(200,"fail",null);
        }
    }

    /**
     * 通过id修改headimage
     *
     * @param file
     * @param request
     */
    @ResponseBody
    @RequestMapping(value = "/updateHeadImage")
    public ResultDto uploadFile(@RequestParam(value = "headimage") MultipartFile file, HttpServletRequest request) {
        //单个文件上传，返回文件上传后的名字
        String resultStr = FileUploadUtil.uploadFile(file,request);
        //匹配User的id
        int id = parseInt(request.getParameter("id"));   //用parseInt()方法将字符串转换成int数据(！！小心！！)
        //判断头像文件是否上传成功
        if (resultStr == null) {
            return ResultDto.error();
        } else {
            int num = userService.changeHeadimageById(id,resultStr);  //传入参数，通过id更改headimage
            //判断更改是否成功
            if(num == 1) {
                return new ResultDto(200, "success", null);
            }
            else {
                return new ResultDto(200,"fail",null);
            }
        }
    }

    /**
     * 通过id修改用户基本信息
     *
     *
     * @param request
     */
    @ResponseBody//将返回的数据处理为json
    @RequestMapping(value = "/changeUserById")
    public ResultDto changeUserById(HttpServletRequest request){
        int id = Integer.parseInt(request.getParameter("id"));  //用parseInt()方法将字符串转换成int数据(！！小心！！)
        User user = userService.findUserById(id);

        //传入各项参数
        user.setName(request.getParameter("name"));
        user.setGender(Integer.parseInt(request.getParameter("gender")));
        user.setAge(Integer.parseInt(request.getParameter("age")));
        user.setBirthday(request.getParameter("birthday"));
        user.setAddress(request.getParameter("address"));
        user.setTypelabel(request.getParameter("typelabel"));
        user.setSignature(request.getParameter("signature"));
        //修改并判断修改是否成功
        if(userService.changeUserById(user) == 1){
            return new ResultDto(200,"success",user);
        }else {
            return new ResultDto(200,"fail",null);
        }
    }

    /**
     * 通过phone来删除user
     *
     *
     * @param request
     */
    @ResponseBody//将返回的数据处理为json
    @RequestMapping(value = "/deleteUserByPhone")
    public ResultDto deleteUserByPhone(HttpServletRequest request){
        String phone = request.getParameter("phone");
        if (userService.deleteUserByPhone(phone) == 1){
            return new ResultDto(200,"success",null);
        }else {
            return new ResultDto(200,"fail",null);
        }
    }

}

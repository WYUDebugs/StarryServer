package com.zhuolang.starryserver.controller;

import com.zhuolang.starryserver.dto.ResultDto;
import com.zhuolang.starryserver.entity.MemoryBook;
import com.zhuolang.starryserver.entity.MemoryBookDto;
import com.zhuolang.starryserver.exception.BaseExceptionHandleAction;
import com.zhuolang.starryserver.exception.MyThrowException;
import com.zhuolang.starryserver.service.MemoryBookService;
import com.zhuolang.starryserver.utils.FileUploadUtil;
import com.zhuolang.starryserver.utils.TimeUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.util.Date;
import java.util.List;

import static java.lang.Integer.parseInt;
import com.zhuolang.starryserver.service.UserService;

/**
 * Created by heziqiang on 2018/8/20
 */

@Controller
@RequestMapping("/memoryBook")
public class MemoryBookController  extends BaseExceptionHandleAction {

    //PS:controller一般只处理获取数据，将数据传到service业务层，不做复杂的数据处理，复杂的数据处理交给service业务层

    @Autowired
    private MemoryBookService memoryBookService;
    @Autowired
    private UserService userService;

    /**
     * 1、通过title添加memoryBook
     *  添加纪念册时，获取默认封面，将第一个封面的文件名字存进纪念册
     *  即 获取MemoryCover的列表的第一条信息的path作为纪念册cover字段
     * @param request
     * @param response
     * @return
     */
    @ResponseBody//将返回数据处理为json
    @RequestMapping(value = "/addMemoryBook")
    public ResultDto addMemoryBook(HttpServletRequest request, HttpServletResponse response) {

        int owner = parseInt(request.getParameter("owner"));
        String title = request.getParameter("title");

        //如果找不到该 owner
        if ((userService.findUserById(owner)) == null) {
            return new ResultDto(200, "owner_is_not_exist", null);
        }

        ResultDto resultDto = memoryBookService.addMemoryBook(owner, title);
        return resultDto;
    }

    /**
     * 2、通过 id 更改 memoryBook 的 title
     *
     * @param request
     * @param response
     * @return
     */
    @ResponseBody//将返回数据处理为 json
    @RequestMapping(value = "/changeMemoryBookTitle")
    public ResultDto changMemoryBookTitle(HttpServletRequest request, HttpServletResponse response) {
        int id = parseInt(request.getParameter("id"));
        String title = request.getParameter("title");

        if ((memoryBookService.changeMemoryBookTitle(id, title)) == 1) {
            return new ResultDto(200, "success", null);
        } else {
            return new ResultDto(200, "fail", null);
        }
    }

    /**
     * 3、通过 id 更改 memoryBook 的 cover，上传自己的封面
     *
     * @param file
     * @param request
     * @return
     */
    @ResponseBody//将返回数据处理为 json
    @RequestMapping(value = "/changeMemoryBookCover")
    public ResultDto changeMemoryBookCover(MultipartFile file, HttpServletRequest request) {

        String uploadResult = FileUploadUtil.uploadFile(file, request);//uploadResult记录文件上传结果，上传失败为null，成功为文件名
        if (uploadResult == null) {
            return ResultDto.error();
        } else {
            //文件上传成功时
            int id = parseInt(request.getParameter("id"));
            int  changeResult = memoryBookService.changeMemoryBookCover(id, uploadResult);

            if (changeResult == 1) {
                return new ResultDto(200, "success", null);
            } else {
                return new ResultDto(200, "fail", null);
            }
        }
    }

    /**
     * 4、通过 id 更改 memoryBook 的 cover，选择系统默认的封面
     *
     * @param request
     * @return
     */
    @ResponseBody//将返回数据处理为 json
    @RequestMapping(value = "/selectUpdateMemoryBookCover")
    public ResultDto selectUpdateMemoryBookCover(HttpServletRequest request) {

        int id = parseInt(request.getParameter("id"));
        String cover = request.getParameter("cover");
        int  changeResult = memoryBookService.changeMemoryBookCover(id, cover);

        if (changeResult == 1) {
            return new ResultDto(200, "success", null);
        } else {
            return new ResultDto(200, "fail", null);
        }

    }

    /**
     * 5、通过 id 查找 memoryBook
     * @param request
     * @param response
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/findMemoryBookById")
    public ResultDto findMemoryBookById(HttpServletRequest request, HttpServletResponse response) {
        int id = parseInt(request.getParameter("id"));

        MemoryBook memoryBook = memoryBookService.findMemoryBookById(id);
        if (memoryBook != null) {
            return new ResultDto(200, "success", memoryBook);
        } else {
            return new ResultDto(200, "memory_book_id_not_exist", null);
        }
    }

    /**
     * 6、通过 id 删除 memoryBook
     * 删除纪念册步骤
     *  1、删除纪念册中每个片段的评
     *  2、删除纪念册的每个片段
     *  3、删除纪念册中的好友
     *  4、删除纪念册
     *
     * @param request
     * @return
     */
    @ResponseBody//将返回数据处理为json
    @RequestMapping(value = "/deleteMemoryBook")
    public ResultDto deleteMemoryBook(HttpServletRequest request) {
        int id = parseInt(request.getParameter("id"));//获取纪念册的id

        //找不到该memoryBook
        if ((memoryBookService.findMemoryBookById(id)) == null) {
            return new ResultDto(200, "memory_book_is_not_exist");
        }
        int result=0;
        try {
            result=memoryBookService.deleteMemoryBook(id);
        }catch (MyThrowException e) {
            System.out.println("异常捕捉：MyThrowException：" + e.getMessage());
            e.printStackTrace();
        } catch (Exception e) {
            System.out.println("异常捕捉：Exception：" + e.getMessage());
            e.printStackTrace();
        }
        if (result == 1) {
            return new ResultDto(200, "delete_success", null);
        } else {
            return new ResultDto(200, "delete_failure", null);
        }
    }

    /**
     * 7、通过用户id获取他的所有有关的纪念册列表
     * @param request
     * @return
     */
    @ResponseBody//将返回数据处理为json
    @RequestMapping(value = "/findMemoryListByUserId")
    public ResultDto findMemoryListByUserId(HttpServletRequest request){
        int uId=Integer.parseInt(request.getParameter("uId"));
        List<MemoryBook> bookList=memoryBookService.showBookList(uId);
        if (bookList != null && bookList.size() > 0) {
            return new ResultDto(200, "success", bookList);
        } else {
            return new ResultDto(200,"noDate",null);
        }

    }

    /**
     * 8、通过 title 的关键字模糊搜索 memoryBook
     * @param request
     * @param response
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/findMemoryBookByTitle")
    public ResultDto findMemoryBookByTitle(HttpServletRequest request, HttpServletResponse response) {
        String title = request.getParameter("title");
        title = "%" + title + "%";
        List<MemoryBook> memoryBook = memoryBookService.findMemoryBookListByTitle(title);

        if (memoryBook != null) {
            return new ResultDto(200, "success", memoryBook);
        } else {
            return new ResultDto(200, "nodata", null);
        }
    }

    /**
     * 删除所有 memoryBook
     * @param request
     * @param response
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/deleteAllMemoryBook")
    public ResultDto deleteAllMemoryBook(HttpServletRequest request, HttpServletResponse response) {
        List<MemoryBook> memoryBookList = memoryBookService.findAllMemoryBookDESC();

        if (memoryBookList != null && memoryBookList.size() > 0) {//如果存在memoryBook
            //如果删除的行数等于表中数据条数
            if (memoryBookService.deleteAllMemoryBook() == memoryBookList.size()) {
                return new ResultDto(200, "success", null);
            } else {
                return new ResultDto(200, "fail", null);
            }
        } else {
            return new ResultDto(200, "memory_book_list_is_empty", null);
        }
    }

    /**
     * 通过 id 更改 memoryBook 的 owner
     *
     * @param request
     * @param response
     * @return
     */
    @ResponseBody//将返回数据处理为 json
    @RequestMapping(value = "/changeMemoryBookOwner")
    public ResultDto changeMemoryBookOwner(HttpServletRequest request, HttpServletResponse response) {
        int id = parseInt(request.getParameter("id"));
        int owner = parseInt(request.getParameter("owner"));

        if ((memoryBookService.changeMemoryBookOwner(id, owner)) == 1) {
            return new ResultDto(200, "success", null);
        } else {
            return new ResultDto(200, "fail", null);
        }
    }

    /**
     * 查找所有 memoryBook
     * @param request
     * @param response
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/findAllMemoryBook")
    public ResultDto findAllMemoryBook(HttpServletRequest request, HttpServletResponse response) {
        List<MemoryBook> memoryBookList = memoryBookService.findAllMemoryBookDESC();

        if (memoryBookList != null && memoryBookList.size() > 0) {
            return new ResultDto(200, "success", memoryBookList);
        } else {
            return new ResultDto(200, "nodata", null);
        }
    }

    /**
     * 个人相册的数量
     * @param request
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/bookNum")
    public ResultDto publisherNum(HttpServletRequest request) {
        int uId = Integer.parseInt(request.getParameter("uId"));
        int numResult= memoryBookService.showBookNum(uId);
        return new ResultDto(200,"success",numResult);
    }

    @ResponseBody
    @RequestMapping(value = "/findBookDetailed")
    public ResultDto findBookDetailed(HttpServletRequest request) {
        int bId = Integer.parseInt(request.getParameter("bId"));
        return memoryBookService.findBookById(bId);
    }

    /**
     * 添加book，返回刚添加的book的详细信息
     * @param request
     * @return
     */
    @ResponseBody//将返回数据处理为json
    @RequestMapping(value = "/addBook")
    public ResultDto addBook(HttpServletRequest request) {

        int owner = parseInt(request.getParameter("owner"));
        String title = request.getParameter("title");
        MemoryBookDto  bookDto=new MemoryBookDto();
        bookDto.setOwner(owner);
        bookDto.setTitle(title);
        bookDto.setCreatTime(TimeUtil.dateToString(new Date()));
        ResultDto resultDto = memoryBookService.addBook(bookDto);
        return resultDto;
    }




}

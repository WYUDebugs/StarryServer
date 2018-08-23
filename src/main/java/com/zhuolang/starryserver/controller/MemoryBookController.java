package com.zhuolang.starryserver.controller;

import com.zhuolang.starryserver.dto.ResultDto;
import com.zhuolang.starryserver.entity.MemoryBook;
import com.zhuolang.starryserver.exception.BaseExceptionHandleAction;
import com.zhuolang.starryserver.service.MemoryBookService;
import com.zhuolang.starryserver.utils.FileUploadUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
     * 通过title添加memoryBook
     *
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
     * 通过 id 删除 memoryBook
     *
     * @param request
     * @return
     */
    @ResponseBody//将返回数据处理为json
    @RequestMapping(value = "/deleteMemoryBook")
    public ResultDto deleteMemoryBook(HttpServletRequest request) {
        int id = parseInt(request.getParameter("id"));

        //找不到该memoryBook
        if ((memoryBookService.findMemoryBookById(id)) == null) {
            return new ResultDto(200, "memory_book_is_not_exist");
        }

        if ((memoryBookService.deleteMemoryBook(id)) == 1) {
            return new ResultDto(200, "success", null);
        } else {
            return new ResultDto(200, "fail", null);
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
     * 通过 id 更改 memoryBook 的 title
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
     * 通过 id 更改 memoryBook 的 cover
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
     * 通过 title 搜索 memoryBook
     * @param request
     * @param response
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/findMemoryBookByTitle")
    public ResultDto findMemoryBookByTitle(HttpServletRequest request, HttpServletResponse response) {
        String title = request.getParameter("title");

        MemoryBook memoryBook = memoryBookService.findMemoryBookByTitle(title);
        
        if (memoryBook != null) {
            return new ResultDto(200, "success", memoryBook);
        } else {
            return new ResultDto(200, "nodata", null);
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
     * 通过 id 查找 memoryBook
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

}

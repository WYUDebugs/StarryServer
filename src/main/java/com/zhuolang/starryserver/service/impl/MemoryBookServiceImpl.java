package com.zhuolang.starryserver.service.impl;

import com.zhuolang.starryserver.dao.MemoryBookDao;
import com.zhuolang.starryserver.dto.ResultDto;
import com.zhuolang.starryserver.entity.MemoryBook;
import com.zhuolang.starryserver.entity.MemoryBookDto;
import com.zhuolang.starryserver.service.MemoryBookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.xml.crypto.Data;
import java.util.Date;
import java.util.List;

@Service//添加MemoryBookService的test时，选中类名MemoryBookServiceImpl，右键go to->test-创建->选择Junit4,选择添加的测试方法，finish
public class MemoryBookServiceImpl implements MemoryBookService {

    @Autowired
    MemoryBookDao memoryBookDao;

    /**
     * 通过title添加memoryBook
     * @param owner
     * @param title
     * @return
     */
    public ResultDto addMemoryBook(int owner, String title) {
        //如果已存在相同 title 的 memoryBook
        // 标题可以相同
//        if (memoryBookDao.findMemoryBookByTitle(title) != null) {
//            return new ResultDto(200, "memory_book_exist", null);
//        }
        //如果不存在相同 title 的 memoryBook，则进行创建
        if (memoryBookDao.addMemoryBook(owner, title, new Date()) == 1) {
            return new ResultDto(200, "success", null);
        } else {
            return new ResultDto(200, "fail", null);
        }
    }

    /**
     * 通过 id 删除 memoryBook
     * @param id
     * @return
     */
    public int deleteMemoryBook(int id) {
        //删除纪念册步骤
        // 1、删除纪念册中每个片段的评论
        // 2、删除纪念册的每个片段
        // 3、删除纪念册中的好友
        // 4、删除纪念册
        return memoryBookDao.deleteMemoryBook(id);
    }

    /**
     * 通过 id 更改 memoryBook 的 owner
     *
     * @param id
     * @param owner
     * @return 删除成功返回 1， 删除失败返回 0
     */
    public int changeMemoryBookOwner(int id, int owner) {
        return memoryBookDao.changeMemoryBookOwner(id, owner);
    }

    /**
     * 通过 id 更改 memoryBook 的 title
     *
     * @param id
     * @param title
     * @return 更改成功返回 1， 更改失败返回 0
     */
    public int changeMemoryBookTitle(int id, String title) {
        return memoryBookDao.changeMemoryBookTitle(id, title);
    }

    /**
     * 通过 id 更改 memoryBook 的 cover
     *
     * @param id
     * @param fileName
     * @return 更改成功返回 1， 更改失败返回 0
     */
    public int changeMemoryBookCover(int id, String fileName) {
        return memoryBookDao.changeMemoryBookCover(id, fileName);
    }

    /**
     * 通过 title 查找 memoryBook
     * @param title
     * @return
     */
    public List<MemoryBook> findMemoryBookListByTitle(String title) {
        return memoryBookDao.findMemoryBookListByTitle(title);
    }

    /**
     * 查找所有 memoryBook
     * @return
     */
    public List<MemoryBook> findAllMemoryBookDESC() {
        return memoryBookDao.findAllMemoryBookDESC();
    }

    /**
     * 删除所有 memoryBook
     * @return 删除成功返回影响memory_book_tab的行数，删除失败返回 0
     */
    public int deleteAllMemoryBook(){
        return memoryBookDao.deleteAllMemoryBook();
    }

    /**
     * 通过 id 查找 memoryBook
     * @param id
     * @return
     */
    public MemoryBook findMemoryBookById(int id) {
        return memoryBookDao.findMemoryBookById(id);
    }

    @Override
    public List<MemoryBook> showBookList(int uId) {
        List<MemoryBook> books=memoryBookDao.memoryBookListResult(uId);
        if (books != null && books.size() > 0) {
            return books;
        } else {
            return null;
        }
    }

    @Override
    public int showBookNum(int uId) {
        int result1=memoryBookDao.showBookNum1(uId);
        int result2=memoryBookDao.showBookNum2(uId);
        return result1+result2;
    }

    @Override
    public ResultDto findBookById(int bId) {
        MemoryBookDto memoryBookDto=memoryBookDao.findBookById(bId);
        if (memoryBookDto != null) {
            return new ResultDto(200, "success", memoryBookDto);
        } else {
            return new ResultDto(200,"failure",null);
        }

    }

    @Override
    public ResultDto addBook(MemoryBookDto bookDto) {
        int result=memoryBookDao.addBook(bookDto);
        if (result==1) {
            int bId=bookDto.getId();
            MemoryBookDto memoryBookDto=memoryBookDao.findBookById(bId);
            if (memoryBookDto != null) {
                return new ResultDto(200, "add_success", memoryBookDto);
            } else {
                return new ResultDto(200,"find_failure",null);
            }
        }
        return new ResultDto(200,"add_failure",null);
    }
}

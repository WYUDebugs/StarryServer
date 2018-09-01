package com.zhuolang.starryserver.service;

import com.zhuolang.starryserver.dto.ResultDto;
import com.zhuolang.starryserver.entity.MemoryBook;

import java.util.List;

/*
 * Created by heziqiang on 2018/8/20
 */

public interface MemoryBookService {

    /**
     * 通过 title 添加 memoryBook
     *
     * @param owner
     * @param title
     * @return
     */
    ResultDto addMemoryBook(int owner, String title);

    /**
     * 通过 id 删除 memoryBook
     *
     * @param id
     * @return
     */
    int deleteMemoryBook(int id);

    /**
     * 通过 id 更改 memoryBook 的 owner
     *
     * @param id
     * @param owner
     * @return
     */
    int changeMemoryBookOwner(int id, int owner);

    /**
     * 通过 id 更改 memoryBook 的 title
     *
     * @param id
     * @param title
     * @return
     */
    int changeMemoryBookTitle(int id, String title);

    /**
     * 通过 id 更改 memoryBook 的 cover
     *
     * @param id
     * @param fileName
     * @return
     */
    int changeMemoryBookCover(int id, String fileName);

    /**
     * 通过 title 查找 memoryBook
     * @param title
     * @return
     */
    List<MemoryBook> findMemoryBookListByTitle(String title);

    /**
     * 查找所有 memoryBook
     * @return
     */
    List<MemoryBook> findAllMemoryBookDESC();

    /**
     * 删除所有 memoryBook
     * @return 删除成功返回影响memory_book_tab的行数，删除失败返回 0
     */
    int deleteAllMemoryBook();

    /**
     * 通过 id 查找 memoryBook
     * @param id
     * @return
     */
    MemoryBook findMemoryBookById(int id);
}

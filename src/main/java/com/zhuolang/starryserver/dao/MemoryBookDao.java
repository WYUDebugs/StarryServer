package com.zhuolang.starryserver.dao;

import com.zhuolang.starryserver.entity.MemoryBook;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

public interface MemoryBookDao {//添加Dao的test时，选中类名，右键go to->test-创建->选择Junit4,选择添加的测试方法，finish

    /**
     * 通过title添加memoryBook
     *
     * @param owner
     * @param title
     * @param creatTime
     * @return
     */
    int addMemoryBook(@Param("owner") int owner, @Param("title") String title, @Param("creatTime") Date creatTime);

    /**
     * 通过 id 删除 memoryBook
     *
     * @param id
     * @return 删除成功返回 1， 删除失败返回 0
     */
    int deleteMemoryBook(@Param("id") int id);

    /**
     * 通过 id 更改 memoryBook 的 owner
     *
     * @param id
     * @param owner
     * @return 更改成功返回 1， 更改失败返回 0
     */
    int changeMemoryBookOwner(@Param("id") int id, @Param("owner") int owner);

    /**
     * 通过 id 更改 memoryBook 的title
     *
     * @param id
     * @param title
     * @retuen 更改成功返回 1， 更改失败返回 0
     */
    int changeMemoryBookTitle(@Param("id") int id, @Param("title") String title);

    /**
     * 通过 id 更改 memoryBook 的 cover
     *
     * @param id
     * @param fileName
     * @return 更改成功返回 1， 更改失败返回 0
     */
    int changeMemoryBookCover(@Param("id") int id, @Param("fileName") String fileName);

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

    /**
     * 通过纪念册id获取纪念册的共同拥有者
     * @param bId
     * @return
     */
    List<Integer> findOwners(@Param("bId")int bId);

    List<MemoryBook> memoryBookListResult(@Param("uId")int uId);

    int showBookNum1(@Param("uId")int uId);
    int showBookNum2(@Param("uId")int uId);
    int updateFriendCount(@Param("bId")int bId);
    int updateFriendCount2(@Param("bId")int bId);
    int updateMomentCount(@Param("bId")int bId);
    int updateMomentCount2(@Param("bId")int bId);
}

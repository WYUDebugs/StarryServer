package com.zhuolang.starryserver.dao;

import com.zhuolang.starryserver.entity.MemoryCover;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by heziqiang on 2018/8/24
 */

public interface MemoryCoverDao {

    /**
     * 为 memoryBook 添加一张 cover
     *
     * @param fileName
     * @return
     */
    int addMemoryCover(@Param("fileName") String fileName);

    /**
     * 通过 id 删除 cover
     *
     * @param id
     * @return
     */
    int deleteMemoryCover(@Param("id") int id);

    /**
     * 查找所有 memoryCover
     *
     * @return
     */
    List<MemoryCover> findAllMemoryCoverDESC();
}

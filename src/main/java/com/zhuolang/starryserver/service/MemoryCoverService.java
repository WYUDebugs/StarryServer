package com.zhuolang.starryserver.service;

import com.zhuolang.starryserver.entity.MemoryCover;

import java.util.List;

/**
 * Created by heziqiang on 2018/8/24
 */

public interface MemoryCoverService {

    /**
     * 为 memoryBook 添加一张 cover
     *
     * @param fileName
     * @return
     */
    int addMemoryCover(String fileName);

    /**
     * 通过 id 删除 cover
     *
     * @param id
     * @return
     */
    int deleteMemoryCover(int id);

    /**
     * 查找所有 memoryCover
     *
     * @return
     */
    List<MemoryCover> findAllMemoryCoverDESC();
}

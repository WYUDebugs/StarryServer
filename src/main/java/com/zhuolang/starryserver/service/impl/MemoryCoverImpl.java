package com.zhuolang.starryserver.service.impl;

import com.zhuolang.starryserver.dao.MemoryCoverDao;
import com.zhuolang.starryserver.entity.MemoryCover;
import com.zhuolang.starryserver.service.MemoryCoverService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by heziqiang on 2018/8/24
 */

@Service
public class MemoryCoverImpl implements MemoryCoverService {

    @Autowired
    private MemoryCoverDao memoryCoverDao;

    /**
     * 为 memoryBook 添加一张 cover
     *
     * @param fileName
     * @return
     */
    public int addMemoryCover(String fileName) {
        return memoryCoverDao.addMemoryCover(fileName);
    }

    /**
     * 通过 id 删除 cover
     *
     * @param id
     * @return
     */
    public int deleteMemoryCover(int id) {
        return memoryCoverDao.deleteMemoryCover(id);
    }

    /**
     * 查找所有 memoryCover
     *
     * @return
     */
    public List<MemoryCover> findAllMemoryCoverDESC() {
        return memoryCoverDao.findAllMemoryCoverDESC();
    }
}

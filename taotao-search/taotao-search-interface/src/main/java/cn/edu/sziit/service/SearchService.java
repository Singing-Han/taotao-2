package cn.edu.sziit.service;

import cn.edu.sziit.pojo.Item;
import cn.edu.sziit.pojo.TaoResult;

public interface SearchService {
    /**
     * 根据关键词搜索
     *
     * @param query 搜索的关键字
     * @param page 查询第几页
     * @param rows 每页返回多少条
     * @return
     */
    TaoResult<Item> search(String query, Integer page, Integer rows);

}

package cn.edu.sziit.service.impl;

import cn.edu.sziit.mapper.ItemMapper;
import cn.edu.sziit.pojo.Item;
import cn.edu.sziit.pojo.TaoResult;
import cn.edu.sziit.service.ItemService;

public class ItemServiceImpl implements ItemService {

    private ItemMapper itemMapper;

    @Override
    public TaoResult<Item> findByPage(Integer page, Integer rows) {

        return null;

    }
}

package cn.edu.sziit.service.impl;

import cn.edu.sziit.mapper.ItemCatMapper;
import cn.edu.sziit.pojo.ItemCat;
import cn.edu.sziit.service.ItemCatService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class ItemCatMapperImpl implements ItemCatService {


    @Autowired
    private ItemCatMapper mapper;

    @Override
    public List<ItemCat> queryItemCatByParentId(Long parentId) {


        ItemCat cat = new ItemCat();

        cat.setParentId(parentId);

        return mapper.selectByExample(cat);
    }
}

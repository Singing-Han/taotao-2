package cn.edu.sziit.service.impl;

import cn.edu.sziit.mapper.ItemCatMapper;
import cn.edu.sziit.pojo.ItemCat;
import cn.edu.sziit.service.ItemCatService;
import org.apache.dubbo.config.annotation.Service;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
@Service
public class ItemCatMapperImpl implements ItemCatService {


    @Autowired
    private ItemCatMapper mapper;

    @Override
    public List<ItemCat> queryItemCatByParentId(Long parentId) {


        ItemCat cat = new ItemCat();

        cat.setParentId(parentId);

        // return mapper.selectByExmaple(cat); // 这个方法会多一个distinct属性的问题
        return mapper.select(cat);
    }
}

package cn.edu.sziit.service.impl;

import cn.edu.sziit.mapper.ItemDescMapper;
import cn.edu.sziit.pojo.ItemDesc;
import cn.edu.sziit.service.ItemDescService;
import org.springframework.beans.factory.annotation.Autowired;
import org.apache.dubbo.config.annotation.Service;



// 由于这个类的方法只会在商品服务(ItemServiceImpl)里面调用 ,所以此处用普通的
@Service
public class ItemDescServiceImpl implements ItemDescService {

    @Autowired
    private ItemDescMapper itemDescMapper;

    @Override
    public void saveItemDesc(ItemDesc itemDesc) {
        itemDescMapper.insert(itemDesc);
    }

    @Override
    public ItemDesc queryById(Long itemId) {

        ItemDesc itemDesc = new ItemDesc();
        itemDesc.setItemId(itemId);

        return itemDescMapper.selectOne(itemDesc);

    }
}

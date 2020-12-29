package cn.edu.sziit.service.impl;

import cn.edu.sziit.mapper.ItemMapper;
import cn.edu.sziit.pojo.Item;
import cn.edu.sziit.pojo.ItemDesc;
import cn.edu.sziit.pojo.TaoResult;
import cn.edu.sziit.service.ItemDescService;
import cn.edu.sziit.service.ItemService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.apache.dubbo.config.annotation.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsMessagingTemplate;
import java.util.Date;

@Service
public class ItemServiceImpl implements ItemService {

    @Autowired
    private ItemMapper itemMapper;

    @Autowired
    private ItemDescService itemDescService;

    @Autowired
    private JmsMessagingTemplate jmsMessagingTemplate;

    /**
     *
     * @param page 查询第几页
     * @param rows 每页显示多少条
     * @return
     */
    @Override
    public TaoResult<Item> findByPage(Integer page, Integer rows) {

        // 设置分页数据 只是设置查询第几页 , 每页查询多少条数据
        PageHelper.startPage(page,rows);

        // 2.查询
        Page<Item> pageData = (Page<Item>)itemMapper.selectByExample(null);

        // 3.创建TaoResult对象
        TaoResult <Item> result = new TaoResult<> (pageData.getTotal(),pageData.getResult());

        return result;
    }

    @Override
    public void saveItem(Item item,String desc) {

        // 由于页面没有提交过来 status | created | updated
        item.setStatus(1); // 1.正常 2. 下架 3.删除
        item.setCreated(new Date()); // 创建时间
        item.setUpdated(item.getCreated()); // 更新时间

        //保存商品
        itemMapper.insert(item);

        // --------------------------------------

        ItemDesc itemDesc = new ItemDesc();
        itemDesc.setCreated(item.getCreated()); // 描述的创建时间
        itemDesc.setUpdated(item.getCreated()); // 描述的更新时间
        itemDesc.setItemDesc(desc);
        itemDesc.setItemId(item.getId());
        // 保存商品描述
        itemDescService.saveItemDesc(itemDesc);

        //----------------新增好商品之后，发送一个消息通知，告诉搜索系统，要更新索引----------

        //只需要发送商品的id过去即可，
        //搜索系统得到商品的id之后，---> 根据id去查询mysql 得到商品数据------>构建索引，存到索引库中。
        jmsMessagingTemplate.convertAndSend("item", item.getId()+"");

    }

    /**
     * 下架商品
     * @param idArr
     */
    @Override
    public void shelveItem(String [] idArr) {

        for (String id : idArr) {

            Item item = itemMapper.selectByPrimaryKey(Long.valueOf(id));
            // 设置状态为下架
            item.setStatus(2); // 1.正常 2. 下架 3.删除
            item.setUpdated(new Date()); // 更新时间

            // 保存商品状态 和更新时间
            itemMapper.updateByPrimaryKey(item);

            jmsMessagingTemplate.convertAndSend("shelveItem", item.getId()+"");

        }
    }

    /**
     * 上架商品
     * @param idArr
     */
    @Override
    public void reshelfItem(String[] idArr) {

        for (String id : idArr) {

            Item item = itemMapper.selectByPrimaryKey(Long.valueOf(id));
            // 设置状态为正常
            item.setStatus(1); // 1.正常 2. 下架 3.删除
            item.setUpdated(new Date()); // 更新时间

            // 保存商品状态 和更新时间
            itemMapper.updateByPrimaryKey(item);

            jmsMessagingTemplate.convertAndSend("item", item.getId()+"");

        }
    }

    @Override
    public Item queryById(Long id) {

        return  itemMapper.selectByPrimaryKey(id);

    }
}

package cn.edu.sziit.service.impl;

import cn.edu.sziit.mapper.ItemMapper;
import cn.edu.sziit.pojo.Item;
import cn.edu.sziit.pojo.TaoResult;
import cn.edu.sziit.service.ItemService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.apache.dubbo.config.annotation.Service;
import org.springframework.beans.factory.annotation.Autowired;

@Service
public class ItemServiceImpl implements ItemService {

    @Autowired
    private ItemMapper itemMapper;

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
}

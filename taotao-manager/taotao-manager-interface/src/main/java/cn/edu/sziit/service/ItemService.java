package cn.edu.sziit.service;

import cn.edu.sziit.pojo.Item;
import cn.edu.sziit.pojo.TaoResult;

import java.util.List;


public interface ItemService {

    // 商品表的数据太多了,所以需要分页查询

    /**
     *  分页查询商品
     * @param page 查询第几页
     * @param rows 每页显示多少条
     * @return 这一页的数据
     */
    TaoResult<Item> findByPage(Integer page, Integer rows);


    /**
     *
     * @param item 商品对象
     * @param desc 商品描述
     */
    void saveItem(Item item,String desc);


    /**
     * 修改商品状态
     */
    int updateItemStatus(String [] idArr,int status);

//    /**
//     * 上商品
//     */
//    int reshelfItem(String [] idArr,int status);

    /**
     * 根据商品id查询商品
     * @param id
     * @return
     */
    Item queryById(Long id);

}

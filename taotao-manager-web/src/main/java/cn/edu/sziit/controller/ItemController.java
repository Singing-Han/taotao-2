package cn.edu.sziit.controller;

import cn.edu.sziit.pojo.Item;
import cn.edu.sziit.pojo.TaoResult;
import cn.edu.sziit.service.ItemService;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class ItemController {

    @Reference
    private ItemService itemService;

    @RequestMapping("/rest/item")
    public TaoResult<Item> findByPage(int page,int rows){

        TaoResult<Item> itemTaoResult = itemService.findByPage(page, rows);
        return itemTaoResult;
    }

}

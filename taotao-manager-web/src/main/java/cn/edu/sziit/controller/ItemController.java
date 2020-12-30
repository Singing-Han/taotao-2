package cn.edu.sziit.controller;

import cn.edu.sziit.pojo.Item;
import cn.edu.sziit.pojo.RespStatus;
import cn.edu.sziit.pojo.TaoResult;
import cn.edu.sziit.service.ItemService;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


@RestController
public class ItemController {

    @Reference
    private ItemService itemService;

    @RequestMapping("/rest/item")
    public TaoResult<Item> findByPage(int page, int rows) {

        TaoResult<Item> itemTaoResult = itemService.findByPage(page, rows);
        return itemTaoResult;
    }

    @RequestMapping("/rest/addItem")
    public void addItem(Item item, String desc) {

        itemService.saveItem(item, desc);

        System.out.println("新增商品成功:" + item);
        System.out.println(desc);
    }


    /**下架商品
     * @param ids
     */
    @RequestMapping(value = "/rest/item/instock", method = RequestMethod.POST)
    public int shelveItem(String ids) {

        String[] idArr = ids.split(",");

        RespStatus respStatus = new RespStatus();

        respStatus.setStatus(2);

        int status = itemService.updateItemStatus(idArr, respStatus.getStatus());

        return status;

    }


    /**
     *  上架商品
     * @param ids
     */
    //http://manager.taotao.com/rest/item/reshelf
    @RequestMapping(value = "/rest/item/reshelf", method = RequestMethod.POST)
    public int reshelfItem(String ids) {

        String[] idArr = ids.split(",");

        RespStatus respStatus = new RespStatus();

        respStatus.setStatus(1);

        int status = itemService.updateItemStatus(idArr, respStatus.getStatus());

        return status;

    }

}

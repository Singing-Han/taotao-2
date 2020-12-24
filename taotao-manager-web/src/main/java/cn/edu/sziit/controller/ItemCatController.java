package cn.edu.sziit.controller;

import cn.edu.sziit.pojo.ItemCat;
import cn.edu.sziit.service.ItemCatService;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ItemCatController {

    @Reference
    private ItemCatService itemCatService;

    @RequestMapping("/rest/item/cat")
    public List<ItemCat> queryItemCatByParentId(
            @RequestParam(value = "id", defaultValue = "0") Long parentId) {

        List<ItemCat> itemCats = itemCatService.queryItemCatByParentId(parentId);

        System.out.println(itemCats);

        return itemCats;
    }



}

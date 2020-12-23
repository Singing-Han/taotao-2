package cn.edu.sziit.service;

import cn.edu.sziit.pojo.ItemCat;

import java.util.List;

public interface ItemCatService {

    /**
     *
     * @param parentId
     * @return
     */
    List<ItemCat> queryItemCatByParentId(Long parentId);
}

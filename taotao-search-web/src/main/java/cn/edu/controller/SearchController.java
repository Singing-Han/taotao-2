package cn.edu.controller;


import cn.edu.sziit.service.SearchService;
import cn.edu.sziit.pojo.Item;
import cn.edu.sziit.pojo.TaoResult;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
public class SearchController {

    @Reference
    private SearchService searchService;

    private int rows = 16;


    // http://search.taotao.com/search?q=apple
    @RequestMapping("/search")
    public String search(Model model, @RequestParam("q") String query,
                         @RequestParam(value = "page", defaultValue = "1") Integer page){


        //直接调用service得到搜索的结果
        TaoResult<Item> taoResult = searchService.search(query, page, rows);

        // 把数据放到模型中，页面展示
        // 搜索关键词
        model.addAttribute("query", query);

        // 搜索结果集list
        model.addAttribute("itemList", taoResult.getRows());

        // 当前页码数
        model.addAttribute("page", page);

        // 总页数,查询到的数据总数和每页显示条数进行计算
        long total = taoResult.getTotal();
        // long pages = total % 16 == 0 ? total / 16 : (total / 16) + 1;
        long pages = (total + this.rows - 1) / this.rows;
        model.addAttribute("totalPages", pages);

        return "search";
    }
}
